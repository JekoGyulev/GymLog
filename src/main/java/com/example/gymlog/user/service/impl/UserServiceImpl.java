package com.example.gymlog.user.service.impl;

import com.example.gymlog.security.UserPrincipal;
import com.example.gymlog.storage.FileStorageService;
import com.example.gymlog.user.enums.Role;
import com.example.gymlog.user.enums.Unit;
import com.example.gymlog.user.model.User;
import com.example.gymlog.user.repository.UserRepository;
import com.example.gymlog.user.service.UserService;
import com.example.gymlog.utils.EmailAlreadyExists;
import com.example.gymlog.utils.UsernameAlreadyExists;
import com.example.gymlog.web.dto.RegisterRequest;
import com.example.gymlog.web.dto.UpdatePhotoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService  fileStorageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileStorageService = fileStorageService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username [%s] was not found"
                        .formatted(username)));


        UserPrincipal userPrincipal = UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .isActive(user.isActive())
                .role(user.getRole())
                .build();

        return userPrincipal;
    }

    @Override
    public User register(RegisterRequest registerRequest) {

        if (this.userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExists("Username '%s' already exists".formatted(registerRequest.getUsername()));
        }

        if (this.userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExists("Email '%s' already exists".formatted(registerRequest.getEmail()));
        }

        User user = initUser(registerRequest);

        this.userRepository.save(user);

        log.info("New user with ID [{}] and username [{}] has registered", user.getId(), user.getUsername());

        // Publish event with kafka to a microservice that sends email to the newly registered user
        // Microservice Name : GymBeast Notification Service

        return user;
    }

    @Override
    public User getUserById(UUID userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id [%s] was not found".formatted(userId)));
    }

    @Override
    public void updatePhoto(UpdatePhotoRequest updatePhotoRequest, boolean deletePhoto, UUID userId) {
        
        User user = getUserById(userId);

        if (deletePhoto) {

            String oldImageURL = user.getProfilePictureUrL();

            user.setProfilePictureUrL(null);
            this.userRepository.save(user);
            this.fileStorageService.delete(oldImageURL);

            return;
        }

        MultipartFile photoFile = updatePhotoRequest.getPhotoFile();

        String contentType = photoFile.getContentType();

        if (!List.of("image/jpeg", "image/png").contains(contentType)) {
            throw new IllegalArgumentException("Only JPG and PNG are allowed");
        }

        String imageURL = this.fileStorageService.save(updatePhotoRequest.getPhotoFile());
        user.setProfilePictureUrL(imageURL);
        this.userRepository.save(user);
    }

    private User initUser(RegisterRequest registerRequest) {
        return User.builder()
                .firstName(registerRequest.getFullName().split("\\s+")[0])
                .lastName(registerRequest.getFullName().split("\\s+")[1])
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .birthDate(registerRequest.getBirthDate())
                .preferredUnit(Unit.METRIC)
                .isActive(true)
                .role(Role.USER)
                .build();
    }
}
