package com.fabien.log_consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "logs", groupId = "log-consumer-group")
    public void consumeLog(ConsumerRecord<String, String> record) {
        System.out.println("📥 Log reçu depuis Kafka : " + record.value());
    }
}
