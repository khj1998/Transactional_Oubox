package kafka_practice.entity;

import jakarta.persistence.*;
import kafka_practice.dto.event.RegisterEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "outbox")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message",nullable = false,columnDefinition = "varchar")
    private String message;

    @Column(name = "status",nullable = false,columnDefinition = "varchar",length = 16)
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static Outbox of(RegisterEvent registerEvent) {
        return Outbox.builder()
                .message(registerEvent.getMessage())
                .status("INIT")
                .build();
    }
}
