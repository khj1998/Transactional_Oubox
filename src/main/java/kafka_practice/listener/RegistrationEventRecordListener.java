package kafka_practice.listener;


import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.service.outbox.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
public class RegistrationEventRecordListener {
    private final OutboxService outboxService;
    
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void recordOutbox(RegisterEvent registerEvent) {
        Long outboxId = outboxService.saveRegistrationAndOutbox(registerEvent);
        registerEvent.setOutBoxId(outboxId);
    }
}
