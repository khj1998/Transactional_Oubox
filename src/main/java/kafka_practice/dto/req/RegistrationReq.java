package kafka_practice.dto.req;

import lombok.Getter;

@Getter
public class RegistrationReq {
    private Long userId;
    private String message;
}
