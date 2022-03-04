package com.ndangducbn.zmart.topology.source;

import com.ndangducbn.zmart.constants.SerdesConstants;
import com.ndangducbn.zmart.model.origin.Purchase;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;

public class ZmartSource {
    public static KStream<String, Purchase> buildSource(StreamsBuilder builder, String sourceNode) {
        return builder.stream(sourceNode, Consumed.with(
                SerdesConstants.stringSerde,
                SerdesConstants.purchaseSerde));
    }
}
