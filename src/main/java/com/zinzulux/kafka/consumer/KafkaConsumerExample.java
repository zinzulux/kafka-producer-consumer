package com.zinzulux.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaConsumerExample {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private String topic;
    private String group;


    @KafkaListener(topics = "topic-01", groupId = "group_id")
    public void consume(String message) throws IOException {
        LOG.info("consume: topic:[{}] message:[{}]", "topic-01", message);
    }

}
