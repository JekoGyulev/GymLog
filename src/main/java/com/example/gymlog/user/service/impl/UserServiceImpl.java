package com.example.gymlog.user.service.impl;

import com.example.gymlog.security.UserPrincipal;
import com.example.gymlog.user.model.User;
import com.example.gymlog.user.repository.UserRepository;
import com.example.gymlog.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
