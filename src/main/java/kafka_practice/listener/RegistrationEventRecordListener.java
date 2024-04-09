package kafka_practice.listener;

import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.service.outbox.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationEventRecordListener {
    private final OutboxService outboxService;

    @EventListener
    public void listenBeforeCommit(RegisterEvent registerEvent) {
        log.info("Before Commit Start --> {}",registerEvent);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void recordOutbox(RegisterEvent registerEvent) {
        Long outboxId = outboxService.saveOutbox(registerEvent);
        registerEvent.setOutBoxId(outboxId);
        log.info("Before Commit End 예약 저장 및 Outbox 메시지 영속");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void saveFailedOutbox(RegisterEvent registerEvent) {
        log.info("이벤트 객체 값 : {}",registerEvent);
    }
}
