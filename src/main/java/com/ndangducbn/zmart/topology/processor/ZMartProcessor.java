package com.ndangducbn.zmart.topology.processor;

import com.ndangducbn.zmart.model.mapper.PurchaseMapper;
import com.ndangducbn.zmart.model.origin.Purchase;
import com.ndangducbn.zmart.model.origin.PurchasePattern;
import com.ndangducbn.zmart.model.origin.RewardAccumulator;
import org.apache.kafka.streams.kstream.KStream;

public class ZMartProcessor {
    public static KStream<String, Purchase> buildFirstProcessor(KStream<String, Purchase> kStream) {
        return kStream
                .mapValues(value -> PurchaseMapper
                        .toPurchaseBuilder(value)
                        .maskCreditCard()
                        .toPurchase());
    }

    public static KStream<String, PurchasePattern> buildSecondProcessor(KStream<String, Purchase> kStream) {
        return kStream
                .mapValues(value -> PurchaseMapper
                        .toPurchasePatternBuilder(value)
                        .toPurchase());
    }

    public static KStream<String, RewardAccumulator> buildThirdProcessor(KStream<String, Purchase> kStream) {
        return kStream
                .mapValues(value -> PurchaseMapper
                        .toRewardAccumulatorBuilder(value));
    }

    public static KStream<Long, Purchase> buildFilterProcessor(KStream<String, Purchase> kStream) {
        return kStream
                .filter((key, value) -> value.getPrice() >= 5.00)
                .selectKey((key, value)-> value.getPurchaseDate());
    }
}
