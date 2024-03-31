package kafka_practice.service;

import kafka_practice.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaConsumer {
    private CountDownLatch latch = new CountDownLatch(10);
    private List<MessageDto> payloads = new ArrayList<>();
    private MessageDto payload;

    @KafkaListener(topics = "kafka-practice",containerFactory = "kafkaListenerContainerFactory")
    public void receive(ConsumerRecord<String,MessageDto> consumerRecord) {
        payload = consumerRecord.value();
        log.info("received payload = {}",payload.toString());
        payloads.add(payload);
        latch.countDown();
    }
}
