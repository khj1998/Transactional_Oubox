package kafka_practice.service.outbox;

import kafka_practice.constant.RegisterMessageEnum;
import kafka_practice.constant.OutboxStatusEnum;
import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.entity.Outbox;
import kafka_practice.entity.Registration;
import kafka_practice.entity.User;
import kafka_practice.exception.outbox.OutboxNotFoundException;
import kafka_practice.exception.user.UserNotFoundException;
import kafka_practice.repository.OutboxRepository;
import kafka_practice.repository.RegistrationRepository;
import kafka_practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OutboxService {
    private final OutboxRepository outboxRepository;

    @Transactional
    public Long saveOutbox(RegisterEvent registerEvent) {
        Outbox outbox = Outbox.of(registerEvent);
        outboxRepository.save(outbox);

        return outbox.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateOutBoxRecord(RegisterEvent registerEvent) {
        Long outboxId = registerEvent.getOutboxId();

        Outbox outbox = outboxRepository.findById(outboxId)
                .orElseThrow(() -> new OutboxNotFoundException(outboxId));
        outbox.updateOutboxStatusToSuccess();
    }
}
