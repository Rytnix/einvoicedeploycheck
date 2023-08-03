package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocPerdDtls implements Serializable {
    private String InvStDt;
    private String InvEndDt;
}
