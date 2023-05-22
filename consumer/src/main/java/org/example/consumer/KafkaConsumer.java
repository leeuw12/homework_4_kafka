package org.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    public static final String TOPIC_NAME = "kafka.example.topic";

    @KafkaListener(topics = TOPIC_NAME)
    public void consume(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String receivedPartition,
                        @Header(KafkaHeaders.OFFSET) String receivedOffset) {
        log.info("Receive from Topic {} by partition {}, offset {}: {}", receivedTopic, receivedPartition, receivedOffset, message);
    }
}
