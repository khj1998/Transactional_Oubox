package kafka_practice.listener;


import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.entity.Outbox;
import kafka_practice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
public class RegistrationEventRecordListener {
    private final OutboxRepository outboxRepository;
    
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void recordOutbox(RegisterEvent registerEvent) {
        Outbox outbox = Outbox.of(registerEvent);
        outboxRepository.save(outbox);
    }
}
