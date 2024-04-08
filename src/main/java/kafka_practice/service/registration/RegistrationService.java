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
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;

    @Transactional
    public RegistrationRes register(RegistrationReq req) {
        Registration registration = saveRegistration(req);

        RegisterEvent registerEvent = new RegisterEvent(registration);
        eventPublisher.publishEvent(registerEvent);

        return RegistrationRes.of(registration);
    }

    private Registration saveRegistration(RegistrationReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new UserNotFoundException(req.getUserId()));

        Registration registration = Registration.of(user);

        return registrationRepository.save(registration);
    }
}
