package com.ndangducbn.zmart.model.origin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardAccumulator {
    private String customerId;
    private double purchaseTotal;
    private int totalRewardPoints;
    private int currentRewardPoints;
    private int daysFromLastPurchase;

    @Override
    public String toString() {
        return "RewardAccumulator[\n" +
                "\t\t\t- customerId='" + customerId +"'" +'\n' +
                "\t\t\t- purchaseTotal=" + purchaseTotal + '\n' +
                "\t\t\t- totalRewardPoints=" + totalRewardPoints + '\n' +
                "\t\t\t- currentRewardPoints=" + currentRewardPoints + '\n' +
                "\t\t\t- daysFromLastPurchase=" + daysFromLastPurchase + '\n' +
                ']';
    }
}
