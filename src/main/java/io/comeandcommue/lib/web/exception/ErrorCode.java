package io.comeandcommue.lib.web.exception;

public enum ErrorCode {
    BAD_REQUEST(400, "C400", "잘못된 요청입니다."),
    UNAUTHORIZED(401, "C401", "인증이 필요합니다."),
    FORBIDDEN(403, "C403", "접근 권한이 없습니다."),
    NOT_FOUND(404, "C404", "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "C500", "서버 오류가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
