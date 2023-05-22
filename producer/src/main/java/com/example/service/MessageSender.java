package com.example.service;

import com.example.config.KafkaProducerConfig;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageSender {

    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        var future = kafkaTemplate.send(KafkaProducerConfig.TOPIC_NAME, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Successful send, function - sendMessage");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Cannot send, function - sendMessage");
            }
        });
    }
}
