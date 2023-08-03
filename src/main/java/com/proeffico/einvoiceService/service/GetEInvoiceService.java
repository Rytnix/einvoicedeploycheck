package com.proeffico.einvoiceService.service;

import com.proeffico.einvoiceService.config.TokenConfig;
import com.proeffico.einvoiceService.entity.AuthTokenResponse;
import com.proeffico.einvoiceService.enums.GSTConstants;
import com.proeffico.einvoiceService.repository.AuthTokenRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Optional;


@Service
public class GetEInvoiceService {

    @Autowired
    AuthTokenRepository authTokenRepository;

    RestTemplate restTemplate = new RestTemplate();
    
    public Object getEnvoiceDetails(String GSTIN,String IRN) throws CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        AuthTokenService xasp_authTokenService = new AuthTokenService();
        String[] tokens = xasp_authTokenService.getAllTokens(GSTIN, "");
        Optional<AuthTokenResponse> authTokenResponse  = authTokenRepository.findById(1L);

        String tokenExpiry = authTokenResponse.get().getTokenExpiry();
        String targetTime = tokenExpiry.substring(tokenExpiry.indexOf(' ')+1);
        HashMap<String,String > customErrorMap = new HashMap<>();
       if(authTokenResponse.isEmpty()) {
             customErrorMap.put("ErrDtls", "Please Generate AuthToken First");
             return customErrorMap;
       }
       if(!authTokenResponse.get().getGSTIN().equals(GSTIN)) {
            customErrorMap.put("ErrDtls", "You have not Generated the Auth Token with provided GSTIN, Please Generate the AuthToken with this " + GSTIN);
        return customErrorMap;
       }
       if(TokenConfig.isTimeDifferenceLessThan2Minutes(targetTime)){
           customErrorMap.put("ErrDtls","Auth Token Expired. Please Generate Again");
           return customErrorMap;

       }

       String authToken = authTokenResponse.get().getAuthToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Gstin", GSTIN);
        httpHeaders.set("authtoken", authToken);
        httpHeaders.set("user_name", GSTConstants.USERNAME);
        httpHeaders.set("X-Asp-Auth-Token", tokens[0]);
        httpHeaders.set("X-Asp-Auth-Signature", tokens[1]);

HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        JSONObject jsonObject = null;
        try {

            jsonObject = restTemplate.exchange(GSTConstants.GET_EINVOICE_URL+IRN, HttpMethod.GET, requestEntity, JSONObject.class).getBody();
        } catch (HttpServerErrorException hsee) {
        switch(hsee.getRawStatusCode()) {
            default:
                try {
                    hsee.printStackTrace();
                    Thread.sleep(1000);
                    return getEnvoiceDetails(GSTIN,IRN);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
        System.out.println(jsonObject);

        return jsonObject;
    }

}
