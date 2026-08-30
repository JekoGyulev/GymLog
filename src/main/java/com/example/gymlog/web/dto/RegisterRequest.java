package com.example.gymlog.web.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {


    @Size(min = 2, message = "Your first name must be at least 2 characters long")
    private String firstName;
    @Size(min = 2, message = "Your last name must be at least 2 characters long")
    private String lastName;
    @Size(min = 5, message = "Your username must be at least 5 characters long")
    private String username;
    @Size(min = 5, message = "Your password must be at least 5 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Your password is too weak")
    private String password;
    @Size(min = 5, message = "Your confirm password must be at least 5 characters long")
    private String confirmPassword;
    @Email(message = "Enter a valid email")
    private String email;


}
