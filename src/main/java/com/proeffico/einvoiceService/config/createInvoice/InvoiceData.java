package com.proeffico.einvoiceService.config.createInvoice;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceData implements Serializable {
    private String Version;
    private TranDtls TranDtls;
    private DocDtls DocDtls;
    private SellerDtls SellerDtls;
    private BuyerDtls BuyerDtls;
    private DispDtls DispDtls;
    private ShipDtls ShipDtls;
    private List<ItemList> ItemList;
    private ValDtls ValDtls;
    private PayDtls PayDtls;
    private RefDtls RefDtls;
    private List<AddlDocDtls> AddlDocDtls;
    private ExpDtls ExpDtls;
    private EwbDtls EwbDtls;
}

