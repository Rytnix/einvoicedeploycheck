package com.proeffico.einvoiceService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenResponse {

    @Id
    private Long id;
    private String ClientId;
    private String UserName;
    private String AuthToken;
    private String Sek;
    private String TokenExpiry;
    private String InfoDtls;
    @Lob
    private String AuthResponseData;
    @Lob
    private String AuthTokenReq;
    private String GSTIN;
    private String appKey;


}
