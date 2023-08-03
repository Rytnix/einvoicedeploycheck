package com.proeffico.einvoiceService.dao;


import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    private String UserName;
    private String Password;
    private String AppKey;
    private Boolean ForceRefreshAccessToken;
}

