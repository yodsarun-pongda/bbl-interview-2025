package com.yodsarun.exam.demo.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T>{
    private T data;

    public ResponseModel() {}

    public ResponseModel<T> RespnseModel(T data) {
        this.data = data;
        return this;
    }

    public ResponseModel<T> setData(T data) {
        this.data = data;
        return this;
    }
}
