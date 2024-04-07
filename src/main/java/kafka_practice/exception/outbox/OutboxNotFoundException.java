package kafka_practice.exception.outbox;

import lombok.Getter;

@Getter
public class OutboxNotFoundException extends RuntimeException {
    private final Long outboxId;

    public OutboxNotFoundException(Long outboxId) {
        this.outboxId = outboxId;
    }
}
