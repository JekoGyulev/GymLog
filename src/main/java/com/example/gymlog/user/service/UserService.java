package com.example.gymlog.user.service;

import com.example.gymlog.user.model.User;
import com.example.gymlog.web.dto.RegisterRequest;
import jakarta.validation.Valid;

public interface UserService {
    User register(RegisterRequest registerRequest);
}
