package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BchDtls implements Serializable {
    private String Nm;
    private String ExpDt;
    private String WrDt;
}
