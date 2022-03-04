package com.ndangducbn.zmart.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerUtils {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static void send(KafkaProducer<Long, String> producer, ProducerRecord record) {
        producer.send(record).get();
    }

    @SneakyThrows
    public static <T> String toJson(T object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }
}
