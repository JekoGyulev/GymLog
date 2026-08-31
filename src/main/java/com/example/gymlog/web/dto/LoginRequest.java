package com.example.gymlog.web.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Username cannot be empty or blank")
    private String username;
    @NotBlank(message = "Password cannot be empty or blank")
    private String password;


}
