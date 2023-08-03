package com.proeffico.einvoiceService.config.createInvoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the OuterClass
        InvoiceData invoiceData = new InvoiceData();
        invoiceData.setVersion("1.1");

        TranDtls tranDtls = new TranDtls();
        tranDtls.setEcmGstin("jsdkfjs");

        invoiceData.setTranDtls(tranDtls);


        // Convert to JSON using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(invoiceData);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
