package com.ndangducbn.zmart.transformer;

import com.ndangducbn.zmart.model.origin.Purchase;
import com.ndangducbn.zmart.model.origin.RewardAccumulator;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Objects;

public class RewardTransformer implements ValueTransformer<Purchase, RewardAccumulator> {
    private KeyValueStore<String, Integer> stateStore;
    private String storeName;
    private ProcessorContext context;

    public RewardTransformer(String storeName) {
        Objects.requireNonNull(storeName, "Store Name khong duoc null!");
        this.storeName = storeName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
        this.stateStore = (KeyValueStore) this.context.getStateStore(storeName);
    }

    @Override
    public RewardAccumulator transform(Purchase purchase) {
        return null;
    }

    @Override
    public void close() {

    }

}
