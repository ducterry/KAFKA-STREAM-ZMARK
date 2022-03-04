package com.ndangducbn.zmart.topology.sink;

import com.ndangducbn.zmart.constants.SerdesConstants;
import com.ndangducbn.zmart.model.origin.Purchase;
import com.ndangducbn.zmart.model.origin.PurchasePattern;
import com.ndangducbn.zmart.model.origin.RewardAccumulator;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class ZMartSink {
    public static void buildFirstSink(KStream<String, Purchase> kStream, String sink) {
         kStream.to(sink, Produced.with(SerdesConstants.stringSerde, SerdesConstants.purchaseSerde));
    }

    public static void buildSecondSink(KStream<String, PurchasePattern> kStream, String sink) {
        kStream.to(sink, Produced.with(SerdesConstants.stringSerde, SerdesConstants.purchasePatternSerde));
    }

    public static void buildThirdSink(KStream<String, RewardAccumulator> kStream, String sink) {
        kStream.to(sink, Produced.with(SerdesConstants.stringSerde, SerdesConstants.rewardAccumulatorSerde));
    }

    public static void buildFilterSink(KStream<Long, Purchase> kStream, String sink) {
        kStream.to(sink, Produced.with(SerdesConstants.longSerde, SerdesConstants.purchaseSerde));
    }

    public static void buildCoffeeSink(KStream<String, Purchase> kStream, String sink) {
        kStream.to(sink, Produced.with(SerdesConstants.stringSerde, SerdesConstants.purchaseSerde));
    }

    public static void buildElectronicsSink(KStream<String, Purchase> kStream, String sink) {
        kStream.to(sink, Produced.with(SerdesConstants.stringSerde, SerdesConstants.purchaseSerde));
    }
}
