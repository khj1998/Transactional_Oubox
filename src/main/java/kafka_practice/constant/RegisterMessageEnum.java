package kafka_practice.constant;

import lombok.Getter;

@Getter
public enum RegisterMessageEnum {
    DEFAULT_REGISTER("기본 예약이 완료됨");

    private final String message;

    RegisterMessageEnum(String message) {
        this.message = message;
    }
}
