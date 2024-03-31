package kafka_practice.constant;

import lombok.Getter;

@Getter
public enum StatusEnum {
    INITIALIZE("메시지가 새로 생성됨"),
    FAILED("메시지가 생성되었으나 이벤트 발행에 실패함"),
    SUCCESS("메시지 생성 및 이벤트 발생에 성공");

    private final String message;

    StatusEnum(String message) {
        this.message = message;
    }
}
