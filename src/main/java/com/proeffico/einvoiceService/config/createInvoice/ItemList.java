package com.proeffico.einvoiceService.config.createInvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemList implements Serializable {
    private String SlNo;
    private String PrdDesc;
    private String IsServc;
    private String HsnCd;
    private String Barcde;
    private double Qty;
    private int FreeQty;
    private String Unit;
    private double UnitPrice;
    private double TotAmt;
    private double Discount;
    private double PreTaxVal;
    private double AssAmt;
    private double GstRt;
    private double IgstAmt;
    private double CgstAmt;
    private double SgstAmt;
    private double CesRt;
    private double CesAmt;
    private double CesNonAdvlAmt;
    private double StateCesRt;
    private double StateCesAmt;
    private double StateCesNonAdvlAmt;
    private double OthChrg;
    private double TotItemVal;
    private String OrdLineRef;
    private String OrgCntry;
    private String PrdSlNo;
    private BchDtls BchDtls;
    private List<AttribDtls> AttribDtls;
}
