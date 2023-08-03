package com.proeffico.einvoiceService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proeffico.einvoiceService.config.AESEncryption;
import com.proeffico.einvoiceService.config.EncryptionUtil;
import com.proeffico.einvoiceService.config.SecretEncryption;
import com.proeffico.einvoiceService.config.TokenConfig;
import com.proeffico.einvoiceService.config.createInvoice.InvoiceData;
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

import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;

@Service
public class GenerateEInvoiceService {

    @Autowired
    AuthTokenRepository authTokenRepository;

    RestTemplate restTemplate = new RestTemplate();
    public Object generateEInvoice(String GSTIN, InvoiceData invoiceData) throws Exception {
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

        System.out.println(tokens[0]);
        System.out.println("sig "+tokens[1]);
// Decrypt Sek

        byte[]  decryptedSek = SecretEncryption.decrypt(authTokenResponse.get().getSek(), Base64.getDecoder().decode(authTokenResponse.get().getAppKey()));
        System.out.println(Base64.getEncoder().encodeToString(decryptedSek));
        String payloadString = null;
ObjectMapper objectMapper = new ObjectMapper();
        try {
             payloadString = objectMapper.writeValueAsString(invoiceData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert payloadString != null;
         byte [] con = payloadString.getBytes("utf-8");
         byte [] encode = Base64.getEncoder().encode(con);

        String Data = SecretEncryption.encryptEK(encode,decryptedSek);
        System.out.println(Data);
        System.out.println(Data.length());
         JSONObject requestBody = new JSONObject();
        requestBody.put("Data", Data);


         HttpEntity<JSONObject> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        JSONObject jsonObject = null;
        try {

            jsonObject = restTemplate.exchange(GSTConstants.GENERATE_EINVOICE_URL, HttpMethod.POST, requestEntity, JSONObject.class).getBody();
        } catch (HttpServerErrorException hsee) {
            switch(hsee.getRawStatusCode()) {
                default:
                    try {
                        hsee.printStackTrace();
                        Thread.sleep(1000);
                        return generateEInvoice(GSTIN,invoiceData);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                        return e.getLocalizedMessage();
                    }
            }
        }
        return jsonObject;
    }

}
