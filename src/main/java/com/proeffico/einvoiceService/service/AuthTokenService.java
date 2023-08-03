package com.proeffico.einvoiceService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proeffico.einvoiceService.config.PublicKeyEncryptionExample;
import com.proeffico.einvoiceService.config.SignAuthToken;
import com.proeffico.einvoiceService.config.TextVerifier;
import com.proeffico.einvoiceService.config.TokenConfig;
import com.proeffico.einvoiceService.dao.X_ASP_Auth_Token_Request;
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
import java.lang.reflect.Field;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Map;

@Service
public class AuthTokenService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    AuthTokenRepository authTokenRepository;

    public static String AuthToken(X_ASP_Auth_Token_Request xAspAuthTokenRequest) {
        String authToken = "v2.0:" + xAspAuthTokenRequest.getAspId() + "::" + TokenConfig.generateTxnCode() + ":" + TokenConfig.generateTimeStampForAuthString() + ":" +
                xAspAuthTokenRequest.getGstin() + ":" + xAspAuthTokenRequest.getApi_action();
        return authToken;
    }


    public static String signedAuthToken(String authToken) throws CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        SignAuthToken signAuthToken = new SignAuthToken();
        return signAuthToken.signToken(authToken);
    }

    public String[] getAllTokens(String Gstin,String action) throws CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
          X_ASP_Auth_Token_Request xAspAuthTokenRequest = new X_ASP_Auth_Token_Request();
        xAspAuthTokenRequest.setAspId(GSTConstants.ASPID);
        xAspAuthTokenRequest.setGstin(Gstin);
        xAspAuthTokenRequest.setApi_action(action);
        String authToken = AuthToken(xAspAuthTokenRequest);
        String signedAuthToken = signedAuthToken(authToken);

        String[] tokenObject = {authToken, signedAuthToken};
        return tokenObject;
    }

    // Call the gst Service for Auth token


    public Object getAuthToken(String Gstin,String action) throws Exception {
        //Tokens for Header
        String[] tokens = getAllTokens(Gstin,action);

        TextVerifier.textVerify(tokens[0], tokens[1]);
        // Create Header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Gstin", Gstin);
        httpHeaders.set("X-Asp-Auth-Token", tokens[0]);
        httpHeaders.set("X-Asp-Auth-Signature", tokens[1]);

        //AppKey For payload

        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);
        String AppKey = Base64.getEncoder().encodeToString(randomBytes);


        //Generate Payload
//          Payload payload = new Payload(GSTConstants.USERNAME, GSTConstants.PASSWORD, AppKey, false);

        // New Way

        JSONObject jsonPayload = new JSONObject();
        jsonPayload.put("UserName", GSTConstants.USERNAME);
        jsonPayload.put("Password", GSTConstants.PASSWORD);
        jsonPayload.put("AppKey", AppKey);
        jsonPayload.put("ForceRefreshAccessToken", false);
        System.out.println("Appkey " + AppKey);
//          //convert above paylod to byte array and Encode   to Base64 String

        byte[] encodedPayload = jsonPayload.toString().getBytes("utf-8");
        byte[] encoded = Base64.getEncoder().encode(encodedPayload);

        String payloadData = PublicKeyEncryptionExample.encryptWithPublicKey(encoded);

        //Encrypt above payload using e-invoice private key


        JSONObject requestBody = new JSONObject();
        requestBody.put("Data", payloadData);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        JSONObject jsonObject = null;
        System.out.println(GSTConstants.AUTH_URL);
        try {
            jsonObject = restTemplate.exchange(GSTConstants.AUTH_URL, HttpMethod.POST,requestEntity, JSONObject.class).getBody();
        }catch (HttpServerErrorException hsee) {
        switch(hsee.getRawStatusCode()) {
            default:
                try {
                    hsee.printStackTrace();
                    Thread.sleep(1000);
                    return getAuthToken(Gstin,action);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
        assert jsonObject != null;
        System.out.println(jsonObject.get("Data").toString());
        ObjectMapper objectMapper = new ObjectMapper();
        Object Data = jsonObject.get("Data");
        Map<String, String> map = objectMapper.convertValue(Data, Map.class);
        AuthTokenResponse authTokenResponse = new AuthTokenResponse();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            try {
                Field field = AuthTokenResponse.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(authTokenResponse, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        {
            authTokenResponse.setAuthResponseData(jsonObject.toString());
            authTokenResponse.setAuthTokenReq(jsonPayload.toString() + " " + requestEntity.toString());
            authTokenResponse.setId(1L);
            authTokenResponse.setGSTIN(Gstin);
            authTokenResponse.setAppKey(AppKey);
        }


        authTokenRepository.save(authTokenResponse);
        return jsonObject;

    }
}

