package com.zinzulux.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducerExample {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafkaProducerExample(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessageWithCallback(String message, String topicName) {
		LOG.info("sendMessageWithCallback: topic:[{}] message:[{}]", topicName, message);

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				LOG.info("Success Callback: [{}] delivered with offset - [{}]", message,
						result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOG.warn("Failure Callback: Cannot deliver message [{}]. [{}]", message, ex.getMessage());
			}
		});
	}

}
