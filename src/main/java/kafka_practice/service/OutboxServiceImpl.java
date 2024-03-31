package kafka_practice.service;

import kafka_practice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxServiceImpl {
    private final OutboxRepository outboxRepository;
}
