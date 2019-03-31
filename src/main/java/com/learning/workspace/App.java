package com.learning.workspace;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * Simple kafka Producer
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		String topicName = "MyFirstTopic";
		String key = "Key1";
		String value = "Value-1";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);

		ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
		producer.send(record);
		Future<RecordMetadata> acknowledge = producer.send(record);
		RecordMetadata  metaData = acknowledge.get();
		System.out.println(metaData.toString());
		producer.close();

		System.out.println("SimpleProducer Completed.");
	}
}
