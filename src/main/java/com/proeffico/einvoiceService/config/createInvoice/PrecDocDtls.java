package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecDocDtls implements Serializable {
    private String InvNo;
    private String InvDt;
    private String OthRefNo;
}
