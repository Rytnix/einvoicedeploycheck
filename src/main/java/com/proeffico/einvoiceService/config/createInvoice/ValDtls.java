package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValDtls implements Serializable {
    private double AssVal;
    private double CgstVal;
    private double SgstVal;
    private double IgstVal;
    private double CesVal;
    private double StCesVal;
    private double Discount;
    private double OthChrg;
    private double RndOffAmt;
    private double TotInvVal;
    private double TotInvValFc;
}
