package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpDtls implements Serializable {
    private String ShipBNo;
    private String ShipBDt;
    private String Port;
    private String RefClm;
    private String ForCur;
    private String CntCode;
    private String ExpDuty;
}
