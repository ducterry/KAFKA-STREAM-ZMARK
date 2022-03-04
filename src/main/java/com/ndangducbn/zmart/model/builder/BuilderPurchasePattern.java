package com.ndangducbn.zmart.model.builder;

import com.ndangducbn.zmart.model.origin.PurchasePattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuilderPurchasePattern {
    private String zipCode;
    private String item;
    private Long date;
    private double amount;

    public PurchasePattern toPurchase() {
        PurchasePattern purchase = new PurchasePattern();
        BeanUtils.copyProperties(this, purchase);
        return purchase;
    }
}
