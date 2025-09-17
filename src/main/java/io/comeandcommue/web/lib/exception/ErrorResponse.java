package io.comeandcommue.web.lib.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;
    private final String code;
    private final int status;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
    }

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.code = null;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message);
    }
}
