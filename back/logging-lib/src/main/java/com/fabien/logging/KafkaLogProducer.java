package com.fabien.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class KafkaLogProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendLog(String service, String level, String message) {
        try {
            LogEvent log = new LogEvent(service, level, message);
            String json = objectMapper.writeValueAsString(log);
            kafkaTemplate.send("logs", json);
        } catch (Exception e) {
            e.printStackTrace(); // évite les crashs silencieux
        }
    }
}
