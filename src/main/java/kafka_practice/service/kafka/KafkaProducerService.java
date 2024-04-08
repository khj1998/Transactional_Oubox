package kafka_practice.service.kafka;

import kafka_practice.dto.req.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public void send(String topic,MessageDto payload) {
        log.info("sending payload = {} to topic = {}",payload,topic);
        kafkaTemplate.send(topic,payload);
    }
}
