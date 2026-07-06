package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

public class SalesSummaryDTO {

    private String sellerName;
    private Double total;

    public SalesSummaryDTO(){
    }

    public SalesSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SalesSummaryDTO(Sale entity){
        this.sellerName = entity.getSeller().getName();
        this.total = entity.getAmount();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
