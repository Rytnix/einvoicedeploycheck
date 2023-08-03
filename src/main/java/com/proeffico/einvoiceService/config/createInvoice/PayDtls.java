package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDtls implements Serializable {
    private String Nm;
    private String AccDet;
    private String Mode;
    private String FinInsBr;
    private String PayTerm;
    private String PayInstr;
    private String CrTrn;
    private String DirDr;
    private int CrDay;
    private double PaidAmt;
    private double PaymtDue;
}
