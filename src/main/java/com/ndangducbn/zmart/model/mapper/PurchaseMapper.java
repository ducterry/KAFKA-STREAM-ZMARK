package com.ndangducbn.zmart.model.mapper;

import com.ndangducbn.zmart.model.builder.BuilderPurchase;
import com.ndangducbn.zmart.model.builder.BuilderPurchasePattern;
import com.ndangducbn.zmart.model.builder.BuilderRewardAccumulator;
import com.ndangducbn.zmart.model.origin.Purchase;
import com.ndangducbn.zmart.model.origin.RewardAccumulator;
import org.springframework.beans.BeanUtils;

public class PurchaseMapper {
    public static BuilderPurchase toPurchaseBuilder(Purchase purchase) {
        BuilderPurchase builderPurchase = new BuilderPurchase();
        BeanUtils.copyProperties(purchase, builderPurchase);
        return builderPurchase;
    }

    public static BuilderPurchasePattern toPurchasePatternBuilder(Purchase purchase) {
        BuilderPurchasePattern builderPurchasePattern = new BuilderPurchasePattern();
        builderPurchasePattern.setDate(purchase.getPurchaseDate());
        builderPurchasePattern.setItem(purchase.getItemPurchased());
        builderPurchasePattern.setAmount(purchase.getPrice()*purchase.getQuantity());
        builderPurchasePattern.setZipCode(purchase.getZipCode());

        return builderPurchasePattern;
    }

    public static RewardAccumulator toRewardAccumulatorBuilder(Purchase purchase) {
        BuilderRewardAccumulator builderPurchasePattern = new BuilderRewardAccumulator();
        return builderPurchasePattern.toRewardAccumulator(purchase);
    }
}
