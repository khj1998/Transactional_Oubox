package kafka_practice.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDto {
    private String message;

    public MessageDto(String message) {
        this.message = message;
    }
}
