package com.ndangducbn.zmart.constants;

import com.ndangducbn.zmart.model.origin.Purchase;
import com.ndangducbn.zmart.model.origin.PurchasePattern;
import com.ndangducbn.zmart.model.origin.RewardAccumulator;
import com.ndangducbn.zmart.utils.serdes.StreamsSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class SerdesConstants {
    public static final Serde<String> stringSerde = Serdes.String();
    public static final Serde<Long> longSerde = Serdes.Long();
    public static final Serde<Purchase> purchaseSerde = StreamsSerdes.PurchaseSerde();
    public static final Serde<PurchasePattern> purchasePatternSerde = StreamsSerdes.PurchasePatternSerde();
    public static final Serde<RewardAccumulator> rewardAccumulatorSerde = StreamsSerdes.RewardAccumulatorSerde();
}
