package com.zinzulux.kafka;

import com.zinzulux.kafka.producer.KafkaProducerExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class SpringKafkaExampleApplication
		implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
			.getLogger(SpringKafkaExampleApplication.class);

	@Autowired
	private KafkaProducerExample kafkaProducerExample;

	@Value("${com.zinzulux.kafka.topic-1}")
	private String topic1;

	@Value("${com.zinzulux.kafka.topic-2}")
	private String topic2;

	public static void main(String[] args) {
		LOG.info("Starting...");
		SpringApplication.run(SpringKafkaExampleApplication.class, args);
		LOG.info("Application terminated");
	}

	public Integer generateRandomId(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	@Override
	public void run(String... args) throws InterruptedException {
		LOG.info("Executing");


		Thread.sleep(5000);
		kafkaProducerExample.sendMessageWithCallback("test1 " + generateRandomId(0, 10000000), topic1);

		Thread.sleep(5000);
		kafkaProducerExample.sendMessageWithCallback("test2 " + generateRandomId(0, 10000000), topic2);
	}
}