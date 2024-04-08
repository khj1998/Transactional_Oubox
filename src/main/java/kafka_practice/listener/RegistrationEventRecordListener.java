package kafka_practice.listener;

import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.service.outbox.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
public class RegistrationEventRecordListener {
    private final OutboxService outboxService;
    
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void recordOutbox(RegisterEvent registerEvent) {
        log.info("Before Commit Start");
        Long outboxId = outboxService.saveOutbox(registerEvent);
        registerEvent.setOutBoxId(outboxId);
        log.info("Before Commit End 예약 저장 및 Outbox 메시지 영속");
    }
}
