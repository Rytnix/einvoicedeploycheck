package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipDtls implements Serializable {
    private String Gstin;
    private String LglNm;
    private String TrdNm;
    private String Addr1;
    private String Addr2;
    private String Loc;
    private int Pin;
    private String Stcd;
}
