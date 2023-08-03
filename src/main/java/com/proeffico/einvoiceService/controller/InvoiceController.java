package com.proeffico.einvoiceService.controller;

import com.proeffico.einvoiceService.config.createInvoice.InvoiceData;
import com.proeffico.einvoiceService.service.GenerateEInvoiceService;
import com.proeffico.einvoiceService.service.GetEInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    GetEInvoiceService getEInvoiceService;

    @Autowired
    GenerateEInvoiceService generateEInvoiceService;

    @PostMapping("/getEInvoice")
    public Object getEInvoice(@RequestHeader("GSTNO") String Gstin ,@RequestHeader("IRN") String IRN) throws Exception {
        return getEInvoiceService.getEnvoiceDetails(Gstin,IRN);

    }
    @PostMapping("/generateEInvoice")
    public Object generateEInvoice(@RequestBody InvoiceData invoiceData, @RequestHeader("GSTIN") String GSTIN ) throws Exception {

        return generateEInvoiceService.generateEInvoice(GSTIN,invoiceData);
    }
}
