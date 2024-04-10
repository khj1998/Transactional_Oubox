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
        /**
         * 도메인 로직은 성공했지만 그 이후 작업에서 에러가 발생해 ROLLBACK 된 경우
         * 예약 id를 FK로 가지는 failed 상태의 outbox 생성
         */
        outboxService.saveFailedOutbox(registerEvent);
    }
}
