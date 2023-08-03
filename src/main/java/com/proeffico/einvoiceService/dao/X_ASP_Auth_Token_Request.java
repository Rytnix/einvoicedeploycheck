package com.proeffico.einvoiceService.dao;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class X_ASP_Auth_Token_Request {


    private String aspId;
    private String gstin;
    private String api_action;
}
