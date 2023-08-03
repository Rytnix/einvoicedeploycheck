package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContrDtls implements Serializable {
    private String RecAdvRefr;
    private String RecAdvDt;
    private String TendRefr;
    private String ContrRefr;
    private String ExtRefr;
    private String ProjRefr;
    private String PORefr;
    private String PORefDt;
}
