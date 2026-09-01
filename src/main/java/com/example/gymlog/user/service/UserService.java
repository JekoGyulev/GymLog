package com.example.gymlog.user.service;

import com.example.gymlog.user.model.User;
import com.example.gymlog.web.dto.RegisterRequest;

import java.util.UUID;

public interface UserService {
    User register(RegisterRequest registerRequest);

    User getUserById(UUID userId);
}
