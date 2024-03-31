package kafka_practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.Callback;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private String message;
    private String status;
}
