package kafka_practice.dto.res;

import kafka_practice.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRes {
    private Long userId;
    private String message;

    public static RegistrationRes of(Registration registration) {
        return RegistrationRes.builder()
                .userId(registration.getUser().getId())
                .message(registration.getMessage())
                .build();
    }
}
