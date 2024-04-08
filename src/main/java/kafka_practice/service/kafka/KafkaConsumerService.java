package kafka_practice.service.kafka;

import kafka_practice.dto.req.MessageDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Getter
@Component
public class KafkaConsumerService {
    private final CountDownLatch latch = new CountDownLatch(10);

    @KafkaListener(topics = "kafka-practice",containerFactory = "kafkaListenerContainerFactory")
    public void receive(ConsumerRecord<String,MessageDto> consumerRecord) {
        MessageDto payload = consumerRecord.value();
        log.info("received payload = {}",payload.toString());
        latch.countDown();
    }
}
