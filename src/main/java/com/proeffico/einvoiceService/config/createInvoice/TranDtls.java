package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranDtls implements Serializable {
    private String TaxSch;
    private String SupTyp;
    private String RegRev;
    private String EcmGstin;
    private String IgstOnIntra;
}
