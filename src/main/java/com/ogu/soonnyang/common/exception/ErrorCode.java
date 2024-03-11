package com.ogu.soonnyang.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ExceptionEnumModel {
    /* Cat Exception */
    INVALID_CAT_GENDER(400, "C001", "유효하지 않은 고양이 성별입니다."),
    INVALID_CAT_STATUS(400, "C002", "유효하지 않은 고양이 활성화 타입입니다."),

    /* Etc Exception */
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(400, "E001", "요청 데이터가 잘못되었습니다."),
    UNHANDLED_EXCEPTION(500, "E000", "알 수 없는 서버 에러가 발생했습니다.");

    private int status;
    private String errorCode;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.errorCode = code;
    }

    @Override
    public String getKey() {
        return this.errorCode;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}
