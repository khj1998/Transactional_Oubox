package kafka_practice.listener;

import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.repository.OutboxRepository;
import kafka_practice.service.outbox.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
public class RegistrationExternalMessageListener {
    private final OutboxService outboxService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageToKafka(RegisterEvent registerEvent) {
        outboxService.updateOutBoxRecord(registerEvent);
    }
}
