package kafka_practice;

import kafka_practice.service.kafka.KafkaConsumerService;
import kafka_practice.service.kafka.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1,
		       brokerProperties = {"listeners=PLAINTEXT://localhost:9092"},
		       ports = {9092})
class KafkaPracticeApplicationTests {
	@Autowired
	private KafkaConsumerService consumer;

	@Autowired
	private KafkaProducerService producer;

	@Test
	void 카프카브로커_프로듀서_컨슈머_메시지전달() throws InterruptedException {

	}

}
