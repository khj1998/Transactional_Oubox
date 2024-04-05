package kafka_practice.service.registration;

import kafka_practice.dto.req.RegistrationReq;
import kafka_practice.entity.Registration;
import kafka_practice.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    @Transactional
    public Registration register(RegistrationReq req) {
        Registration registration = Registration.builder()
                                    .message(req.getMessage())
                                    .build();
        registrationRepository.save(registration);

        return registration;
    }
}
