package kafka_practice.dto.event;

import kafka_practice.dto.res.RegistrationRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegisterEvent extends ApplicationEvent {
    private Long userId;
    private String message;

    public RegisterEvent(Object source) {
        super(source);
    }

    public void setEventProperties(RegistrationRes registrationRes) {
        userId = registrationRes.getUserId();
        message = registrationRes.getMessage();
    }
}
