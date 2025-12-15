package com.yodsarun.exam.demo.exception;

import com.yodsarun.exam.demo.constant.StatusCodeEnum;
import lombok.Getter;

public class BusinessException extends RuntimeException {
    @Getter
    private String code;
    private String message;

    public BusinessException(StatusCodeEnum statusCodeEnum, Throwable throwable) {
        super(throwable);
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDescription();
    }

    public BusinessException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDescription();
    }
}
