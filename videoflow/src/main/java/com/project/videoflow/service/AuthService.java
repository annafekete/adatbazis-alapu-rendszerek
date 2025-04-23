package com.project.videoflow.service;

import com.project.videoflow.dto.LoginRequest;
import com.project.videoflow.model.User;

public interface AuthService {
    User login(LoginRequest request);
}
