package com.example.gymlog.user.service;

import com.example.gymlog.user.model.User;
import com.example.gymlog.web.dto.ChangePasswordRequest;
import com.example.gymlog.web.dto.RegisterRequest;
import com.example.gymlog.web.dto.UpdatePhotoRequest;
import jakarta.validation.Valid;

import java.util.UUID;

public interface UserService {
    User register(RegisterRequest registerRequest);

    User getUserById(UUID userId);

    void updatePhoto(UpdatePhotoRequest updatePhotoRequest, boolean deletePhoto, UUID userId);

    void changePassword(ChangePasswordRequest changePasswordRequest, User user);
}
