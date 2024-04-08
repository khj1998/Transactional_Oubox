package kafka_practice.listener;

import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.dto.req.MessageDto;
import kafka_practice.service.outbox.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
public class RegistrationExternalMessageListener {
    private final OutboxService outboxService;
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageAndUpdateOutbox(RegisterEvent registerEvent) {
        MessageDto messageDto = getMessageDto(registerEvent);
        kafkaTemplate.send("kafka-practice",messageDto);
        
        outboxService.updateOutBoxRecord(registerEvent);
    }

    private MessageDto getMessageDto(RegisterEvent registerEvent) {
        return new MessageDto(registerEvent.getMessage());
    }
}
