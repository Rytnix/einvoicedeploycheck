package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EwbDtls implements Serializable {
    private String TransId;
    private String TransName;
    private int Distance;
    private String TransDocNo;
    private String TransDocDt;
    private String VehNo;
    private String VehType;
    private String TransMode;
}
