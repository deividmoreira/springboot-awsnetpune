package br.com.itau.neptunepoc.exceptions;

import lombok.Getter;

import static br.com.itau.neptunepoc.exceptions.BusinessConstantError.DEFAULT_BUSINESS_ERROR;

@Getter
public class BusinessException extends RuntimeException {

    private final transient ErrorCode errorCode;
    private String[] messages;

    public BusinessException() {
        this.errorCode = new ErrorCode(DEFAULT_BUSINESS_ERROR);
    }

    public BusinessException(String key) {
        this.errorCode = new ErrorCode(key);
    }

    public BusinessException(String key, Throwable cause) {
        super.initCause(cause);
        this.errorCode = new ErrorCode(key);
    }

    public BusinessException(String key, String... messages) {
        this.errorCode = new ErrorCode(key);
        this.messages = messages;
    }

    public BusinessException(String key, Throwable cause, String... messages) {
        super.initCause(cause);
        this.errorCode = new ErrorCode(key);
        this.messages = messages;
    }
}