package com.ndangducbn.zmart;

import com.ndangducbn.zmart.configs.KafkaConsumerConfig;
import com.ndangducbn.zmart.enums.EnumsFeature;
import com.ndangducbn.zmart.topology.ZMartTopology;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerKafkaStreamApplication {
    public static void main(String[] args) {
        KafkaStreams kafkaStreams = new KafkaStreams(
                ZMartTopology.build(EnumsFeature.FEATURE_1),
                new StreamsConfig(KafkaConsumerConfig.config()));

        kafkaStreams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
        try {
            Thread.sleep(5000);
            System.out.println(kafkaStreams.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kafkaStreams.close();
    }
}
