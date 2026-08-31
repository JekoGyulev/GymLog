package com.example.gymlog.user.service.impl;

import com.example.gymlog.security.UserPrincipal;
import com.example.gymlog.user.enums.Role;
import com.example.gymlog.user.model.User;
import com.example.gymlog.user.repository.UserRepository;
import com.example.gymlog.user.service.UserService;
import com.example.gymlog.utils.EmailAlreadyExists;
import com.example.gymlog.utils.UsernameAlreadyExists;
import com.example.gymlog.web.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        if (user.getEmail() != null) {
            // Publish event with kafka to a microservice that sends email to the newly registered user
            // Microservice Name : GymBeast Notification Service
        }


        return user;
    }

    private User initUser(RegisterRequest registerRequest) {
        return User.builder()
                .firstName(registerRequest.getFullName().split("\\s+")[0])
                .lastName(registerRequest.getFullName().split("\\s+")[1])
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .isActive(true)
                .role(Role.USER)
                .build();
    }
}
