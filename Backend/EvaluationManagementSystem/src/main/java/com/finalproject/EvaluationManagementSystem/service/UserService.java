package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> register(UserRequestModel userRequestModel);
}
