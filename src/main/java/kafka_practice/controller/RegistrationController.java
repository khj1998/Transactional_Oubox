package kafka_practice.controller;

import kafka_practice.dto.req.RegistrationReq;
import kafka_practice.entity.Registration;
import kafka_practice.service.registration.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Registration> register(@RequestBody RegistrationReq req) {
        return ResponseEntity.ok(registrationService.register(req));
    }
}
