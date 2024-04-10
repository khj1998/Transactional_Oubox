package kafka_practice.constant;

import lombok.Getter;

@Getter
public enum OutboxStatusEnum {
    INITIALIZE("도메인 로직 성공 및 메시지가 생성됨"),
    FAILED("메시지가 생성되었으나 이벤트 발행에 실패함"),
    SUCCESS("카프카에 메시지 전달 성공");

    private final String message;

    OutboxStatusEnum(String message) {
        this.message = message;
    }
}
