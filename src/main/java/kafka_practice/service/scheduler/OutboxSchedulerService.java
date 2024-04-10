package kafka_practice.service.scheduler;

import kafka_practice.entity.Outbox;
import kafka_practice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxSchedulerService {
    private final OutboxRepository outboxRepository;

    @Scheduled(fixedRate = 30000)
    public void checkOutbox() {
        List<Outbox> outboxList = outboxRepository.findAll();

        for (Outbox outbox : outboxList) {
            if (outbox.isOverTenSeconds()) {
                /**
                 * 메시지 재발행 시도
                 */
            }
        }
    }
}
