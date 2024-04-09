package kafka_practice.exception;

import kafka_practice.exception.outbox.OutboxNotFoundException;
import kafka_practice.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Id : "+ex.getUserId()+" not found");
    }

    @ExceptionHandler(OutboxNotFoundException.class)
    public ResponseEntity<?> handleException(OutboxNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("outbox Id : "+ex.getOutboxId()+" not found");
    }
}
