package kafka_practice.service.registration;

import kafka_practice.dto.event.RegisterEvent;
import kafka_practice.dto.req.RegistrationReq;
import kafka_practice.dto.res.RegistrationRes;
import kafka_practice.entity.Registration;
import kafka_practice.entity.User;
import kafka_practice.exception.user.UserNotFoundException;
import kafka_practice.repository.RegistrationRepository;
import kafka_practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public RegistrationRes register(RegistrationReq req) {
        RegistrationRes registrationRes = checkRegistrationRes(req);

        RegisterEvent registerEvent = new RegisterEvent(req);
        registerEvent.setEventProperties(registrationRes);
        eventPublisher.publishEvent(registerEvent);

        return registrationRes;
    }

    private RegistrationRes checkRegistrationRes(RegistrationReq req) {
        return RegistrationRes.builder()
                .userId(req.getUserId())
                .message(req.getMessage())
                .build();
    }
}
