package com.ndangducbn.zmart;

import com.ndangducbn.zmart.configs.KafkaProducerConfig;
import com.ndangducbn.zmart.constants.SourceConstants;
import com.ndangducbn.zmart.model.data.DataFake;
import com.ndangducbn.zmart.utils.ProducerUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ProducerKafkaStreamApplication {
    public static void main(String[] args) {
        DataFake.data
                .stream()
                .map(item -> new ProducerRecord(SourceConstants.SOURCE_NODE, item.getCustomerId(), ProducerUtils.toJson(item)))
                .forEach(item2 -> {
                            ProducerUtils.send(KafkaProducerConfig.config(), item2);
                            showInfo(item2);
                        }
                );
    }

    public static void showInfo(ProducerRecord record) {
        System.out.println("----- THÔNG TIN BẢN GHI:------");
        System.out.println("\t  . Key      : " + record.key().toString());
        System.out.println("\t  . Value    : " + record.value().toString());
    }
}
