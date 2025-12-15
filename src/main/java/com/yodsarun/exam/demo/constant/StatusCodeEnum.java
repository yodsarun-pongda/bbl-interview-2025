package com.yodsarun.exam.demo.constant;

import lombok.Getter;

public enum StatusCodeEnum {
    SUCCESS_200("2000", "Success"),
    BAD_REQUEST_400("4000", "Bad request"),
    INTERNAL_SERVER_ERROR_500("5000", "Internal Server Error"),
    NOT_FOUND_404("4040", "Data not found"),
    RESOURCE_CONFLICE("4090", "Resource Conflict")
    ;

    private final String code;
    private final String description;

    StatusCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
