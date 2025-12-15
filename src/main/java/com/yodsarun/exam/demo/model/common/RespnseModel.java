package com.yodsarun.exam.demo.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespnseModel<T>{
    private T data;

    public RespnseModel() {}

    public RespnseModel(T data) {
        this.data = data;
    }

    public RespnseModel<T> setData(T data) {
        this.data = data;
        return this;
    }
}
