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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class OutboxService {
    private final RegistrationRepository registrationRepository;
    private final OutboxRepository outboxRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveRegistrationAndOutbox(RegisterEvent registerEvent) {
        Registration registration = saveRegistration(registerEvent);

        Long outboxId = saveOutbox(registerEvent,registration);

        return outboxId;
    }

    private Registration saveRegistration(RegisterEvent registerEvent) {
        User user = userRepository.findById(registerEvent.getUserId())
                .orElseThrow(() -> new UserNotFoundException(registerEvent.getUserId()));

        Registration registration = Registration.builder()
                .user(user)
                .message(RegisterMessageEnum.DEFAULT_REGISTER.getMessage())
                .build();

        return registrationRepository.save(registration);
    }

    private Long saveOutbox(RegisterEvent registerEvent,Registration registration) {
        Outbox outbox = Outbox.of(registerEvent,registration);
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
