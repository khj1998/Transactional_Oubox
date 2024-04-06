package kafka_practice.listener;

import kafka_practice.dto.event.RegisterEvent;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

public class RegistrationExternalMessageListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessage(RegisterEvent registerEvent) {

    }
}
