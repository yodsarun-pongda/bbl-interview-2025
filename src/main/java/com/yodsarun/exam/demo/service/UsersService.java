package com.yodsarun.exam.demo.service;

import com.yodsarun.exam.demo.constant.PathConstant;
import com.yodsarun.exam.demo.constant.StatusCodeEnum;
import com.yodsarun.exam.demo.exception.BusinessException;
import com.yodsarun.exam.demo.mockdata.MockupUserData;
import com.yodsarun.exam.demo.model.common.ResponseModel;
import com.yodsarun.exam.demo.model.data.UsersModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {
    private final MockupUserData mockupUserData;

    public ResponseEntity<ResponseModel<List<UsersModel>>> getAllUsers() {
        var users = mockupUserData.getUserDataList();
        var response = new ResponseModel<List<UsersModel>>().setData(users);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseModel<UsersModel>> inquiryUserById(String userId) {
        try {
            var matchedUser = mockupUserData.getUserDataList()
                    .stream()
                    .filter(user ->
                            user.getId().toString().equals(userId)
                    )
                    .findFirst()
                    .orElseThrow(() -> new BusinessException(StatusCodeEnum.NOT_FOUND_404));
            log.info("Found Matched userId: {}", userId);
            return ResponseEntity.ok().body(new ResponseModel<UsersModel>().setData(matchedUser));
        } catch (Exception ex) {
            throw new BusinessException(StatusCodeEnum.INTERNAL_SERVER_ERROR_500, ex);
        }
    }

    public ResponseEntity<ResponseModel<Void>> createUser(UsersModel usersModel) {
        var userId = usersModel.getId().toString();
        URI location = URI.create(PathConstant.USER_BY_ID_ENDPOINT.replace("{userId}", userId));

        var matchedUser = mockupUserData.getUserById(userId);
        if (matchedUser != null) {
            throw new BusinessException(StatusCodeEnum.RESOURCE_CONFLICE);
        }

        mockupUserData.getUserDataList().add(usersModel);
        log.info("User: {} have been created", userId);

        return ResponseEntity.created(location).body(new ResponseModel<>());
    }

    public ResponseEntity<ResponseModel<Void>> updateUser(String userId, UsersModel usersModelRequest) {
        try {
            var matchedUser = mockupUserData.getUserById(userId);
            matchedUser.setName(usersModelRequest.getName());
            matchedUser.setUsername(usersModelRequest.getUsername());
            matchedUser.setEmail(usersModelRequest.getEmail());
            matchedUser.setPhone(usersModelRequest.getUsername());
            matchedUser.setWebsite(usersModelRequest.getWebsite());

            // Calling repository.save()

            return ResponseEntity.ok().body(new ResponseModel<>());
        } catch (Exception e) {
            log.error("Error while update user: {}", userId, e);
            throw e;
        }
    }

    public ResponseEntity<ResponseModel<Void>> deleteUser(String userId) {
        try {
            var matchedUser = mockupUserData.getUserById(userId);
            var isSuccess = mockupUserData.deleteUser(matchedUser);
            if (isSuccess) {
                return ResponseEntity.ok().body(new ResponseModel<>());
            } else {
                throw new BusinessException(StatusCodeEnum.INTERNAL_SERVER_ERROR_500);
            }
        } catch (Exception e) {
            log.error("Error while update user: {}", userId, e);
            throw e;
        }
    }
}
