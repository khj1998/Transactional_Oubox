package kafka_practice.entity;

import jakarta.persistence.*;
import kafka_practice.constant.RegisterMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "registration")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "message",nullable = false,length = 45,columnDefinition = "varchar")
    private String message;

    @UpdateTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static Registration of(User user) {
        return Registration.builder()
                .user(user)
                .message(RegisterMessageEnum.DEFAULT_REGISTER.getMessage())
                .build();
    }
}
