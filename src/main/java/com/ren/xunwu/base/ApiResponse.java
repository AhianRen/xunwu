package com.ren.xunwu.base;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private int code;
    private String message;
    private Object data;
    private boolean more;

    public ApiResponse(){
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandardMessage();
    }

    public ApiResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse ofMessage(int code,String message){
        return new ApiResponse(code, message, null);
    }

    public static ApiResponse ofSuccess(Object data) {
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    public static ApiResponse ofStatus(Status status) {
        return new ApiResponse(status.getCode(), status.getStandardMessage(), null);
    }



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public enum Status {
        SUCCESS(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
        NOT_VALID_PARAM(40005, "Not valid Params"),
        NOT_SUPPORTED_OPERATION(40006, "Operation not supported"),
        NOT_LOGIN(50000, "Not Login");

        private int code;
        private String standardMessage;
    }
}
