package kafka_practice;

import kafka_practice.constant.StatusEnum;
import kafka_practice.dto.req.MessageDto;
import kafka_practice.service.kafka.KafkaConsumer;
import kafka_practice.service.kafka.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@EmbeddedKafka(partitions = 1,
		       brokerProperties = {"listeners=PLAINTEXT://localhost:9092"},
		       ports = {9092})
class KafkaPracticeApplicationTests {
	@Autowired
	private KafkaConsumer consumer;

	@Autowired
	private KafkaProducer producer;

	@Test
	void 카프카브로커_프로듀서_컨슈머_메시지전달() throws InterruptedException {
		String topic = "kafka-practice";

		MessageDto messageDto = MessageDto.builder()
				.message("kafka practice")
				.status(StatusEnum.INITIALIZE.getMessage())
				.build();

		producer.send(topic,messageDto);

		consumer.getLatch().await(10,TimeUnit.SECONDS);
		System.out.println(consumer.getPayload().getMessage());
	}

}
