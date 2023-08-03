package com.proeffico.einvoiceService.controller;

import com.proeffico.einvoiceService.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KeyController {

    @Autowired
    AuthTokenService authTokenService;

    @PostMapping("/getkey")
    public Object getKey(@RequestHeader("GSTNO") String Gstin ,@RequestHeader(value = "ACTION",required = false) String action) throws Exception {

        return authTokenService.getAuthToken(Gstin,action);

    }
}
