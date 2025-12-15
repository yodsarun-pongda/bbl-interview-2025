package com.yodsarun.exam.demo.controller;

import com.yodsarun.exam.demo.constant.CommonConstant;
import com.yodsarun.exam.demo.constant.PathConstant;
import com.yodsarun.exam.demo.model.common.ResponseModel;
import com.yodsarun.exam.demo.model.data.UsersModel;
import com.yodsarun.exam.demo.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommonConstant.API_V1)
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @GetMapping(value = {PathConstant.USER_ENDPOINT})
    public ResponseEntity<ResponseModel<List<UsersModel>>> inquiryUser() {
        System.out.println("hello world");
        return usersService.getAllUsers();
    }

    @GetMapping(value = {PathConstant.USER_BY_ID_ENDPOINT})
    public ResponseEntity<ResponseModel<UsersModel>> inquiryUserById(
        @PathVariable String userId
    ) {
        return usersService.inquiryUserById(userId);
    }

    @PostMapping(value = {PathConstant.USER_ENDPOINT})
    public ResponseEntity<ResponseModel<Void>> createUser(
            @RequestBody UsersModel usersModel
    ) {
        return usersService.createUser(usersModel);
    }

    @PutMapping(value = {PathConstant.USER_BY_ID_ENDPOINT})
    public ResponseEntity<ResponseModel<Void>> updateUser(
            @PathVariable String userId,
            @RequestBody UsersModel usersModel
    ) {
        return usersService.updateUser(userId, usersModel);
    }

    @DeleteMapping(value = {PathConstant.USER_BY_ID_ENDPOINT})
    public ResponseEntity<ResponseModel<Void>> deleteUserById(
            @PathVariable String userId
    ) {
        return usersService.deleteUser(userId);
    }
}
