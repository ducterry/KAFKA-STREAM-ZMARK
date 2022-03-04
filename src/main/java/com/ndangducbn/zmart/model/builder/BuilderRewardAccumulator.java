package com.ndangducbn.zmart.model.builder;

import com.ndangducbn.zmart.model.origin.Purchase;
import com.ndangducbn.zmart.model.origin.RewardAccumulator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuilderRewardAccumulator {
    private String customerId;
    private double purchaseTotal;
    private int daysFromLastPurchase;
    private int rewardPoints;

    public RewardAccumulator toRewardAccumulator(Purchase purchase) {
        return RewardAccumulator.builder()
                .customerId(purchase.getCustomerId())
                .purchaseTotal(purchase.getPrice() * purchase.getQuantity())
                .totalRewardPoints((int) purchaseTotal)
                .build();
    }
}
