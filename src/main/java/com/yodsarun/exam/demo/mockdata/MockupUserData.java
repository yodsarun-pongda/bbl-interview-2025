package com.yodsarun.exam.demo.mockdata;

import com.yodsarun.exam.demo.constant.StatusCodeEnum;
import com.yodsarun.exam.demo.exception.BusinessException;
import com.yodsarun.exam.demo.model.data.UsersModel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
public class MockupUserData {
    private final List<UsersModel> userDataList = new ArrayList<>();

    private static final String usersDataEndPoint = "https://jsonplaceholder.typicode.com/users";

    @PostConstruct
    public void init() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<UsersModel>> response = restTemplate.exchange(
                    usersDataEndPoint,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            List<UsersModel> body = response.getBody();
            if (body == null) {
                log.error("not found data");
            } else {
                userDataList.clear();
                userDataList.addAll(body);
            }


        } catch (RestClientException e) {
            log.error("Error while getting data", e);
        }
    }

    public List<UsersModel> getUserDataList() {
        if (userDataList.isEmpty()) {
            init();
        }
        return userDataList;
    }

    public UsersModel getUserById(String userId) {
        if (userDataList.isEmpty()) {
            init();
        }
        return userDataList
                .stream()
                .filter(user ->
                        user.getId().toString().equals(userId)
                )
                .findFirst()
                .orElseThrow(() -> new BusinessException(StatusCodeEnum.NOT_FOUND_404));
    }

    public boolean deleteUser(UsersModel usersModel) {
        return userDataList.removeIf(user -> user == usersModel);
    }
}
