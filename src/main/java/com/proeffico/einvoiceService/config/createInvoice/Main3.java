package com.proeffico.einvoiceService.config.createInvoice;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Main3 {

    public static void main(String[] args) {
        try {
            // Create a map to represent the JSON data
            Map<String, Object> jsonData = new HashMap<>();
//
//            // Add simple key-value pairs (string to string)
//            jsonData.put("name", "John");
//            jsonData.put("age", "30");
//
//            // Create a nested JSON object
//            Map<String, Object> addressData = new HashMap<>();
//            addressData.put("street", "123 Main St");
//            addressData.put("city", "New York");
//            addressData.put("zip", "10001");
//
//            // Add the nested JSON object to the main JSON data
//            jsonData.put("address", addressData);
//            AuthTokenResponse authTokenResponse = new AuthTokenResponse();
//           jsonData.put("Data",authTokenResponse);


          jsonData.put("version","1.1");
            Map<String, Object>  TranDtls= new HashMap<>();





            // Convert the map to a JSON string using Jackson library
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(jsonData);

            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
