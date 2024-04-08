package kafka_practice.dto.event;

import kafka_practice.dto.res.RegistrationRes;
import kafka_practice.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegisterEvent extends ApplicationEvent {
    private final Long userId;
    private final String message;
    private final Registration registration;
    private Long outboxId;

    public RegisterEvent(Registration registration) {
        super(registration);
        this.registration = registration;
        this.userId = registration.getUser().getId();
        this.message = registration.getMessage();
    }

    public void setOutBoxId(Long id) {
        this.outboxId = id;
    }

}
