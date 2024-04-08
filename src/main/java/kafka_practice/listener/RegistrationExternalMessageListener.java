package kafka_practice.listener;

import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.dto.req.MessageDto;
import kafka_practice.service.outbox.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
public class RegistrationExternalMessageListener {
    private final OutboxService outboxService;
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageAndUpdateOutbox(RegisterEvent registerEvent) {
        log.info("After Commit Start");
        MessageDto messageDto = getMessageDto(registerEvent);
        kafkaTemplate.send("kafka-practice",messageDto);

        outboxService.updateOutBoxRecord(registerEvent);

        log.info("After Commit End Outbox 메시지 완료처리 및 kafka에 메시지 발행");
    }

    private MessageDto getMessageDto(RegisterEvent registerEvent) {
        return new MessageDto(registerEvent.getMessage());
    }
}
