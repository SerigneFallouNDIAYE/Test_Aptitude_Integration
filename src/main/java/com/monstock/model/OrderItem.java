package com.monstock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItem {
    @JsonProperty("id")
    private String id;
    @JsonProperty("idpurchaseorder")
    private int idPurchaseOrder;
    @JsonProperty("idproducts")
    private String idProducts;
    @JsonProperty("quantityorder")
    private String quantityOrder;
    @JsonProperty("branchs_id")
    private int branchsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdPurchaseOrder() {
        return idPurchaseOrder;
    }

    public void setIdPurchaseOrder(int idPurchaseOrder) {
        this.idPurchaseOrder = idPurchaseOrder;
    }

    public String getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(String idProducts) {
        this.idProducts = idProducts;
    }

    public String getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(String quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public int getBranchsId() {
        return branchsId;
    }

    public void setBranchsId(int branchsId) {
        this.branchsId = branchsId;
    }
}
