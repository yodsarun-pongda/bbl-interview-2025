package com.yodsarun.exam.demo.exception;

import com.yodsarun.exam.demo.constant.StatusCodeEnum;
import com.yodsarun.exam.demo.model.common.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHalder {
    private GlobalExceptionHalder() {

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception ex) {
        return ((ResponseEntity.BodyBuilder) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)).body(new ResponseModel<String>().setData(ex.getMessage()));
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<?> handleException(BusinessException ex) {
        var httpStauts = HttpStatus.INTERNAL_SERVER_ERROR;
        if (Objects.equals(ex.getCode(), StatusCodeEnum.NOT_FOUND_404.getCode())) {
            httpStauts = HttpStatus.NOT_FOUND;
        }

        return ((ResponseEntity.BodyBuilder) ResponseEntity.status(httpStauts)).body(new ResponseModel<String>().setData(ex.getMessage()));
    }
}
