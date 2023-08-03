package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefDtls implements Serializable {
    private String InvRm;
    private DocPerdDtls DocPerdDtls;
    private List<PrecDocDtls> PrecDocDtls;
    private List<ContrDtls> ContrDtls;
}
