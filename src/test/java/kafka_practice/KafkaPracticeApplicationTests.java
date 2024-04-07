package kafka_practice;

import kafka_practice.constant.OutboxStatusEnum;
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

	}

}
