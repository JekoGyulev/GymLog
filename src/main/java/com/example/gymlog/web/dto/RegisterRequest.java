package com.example.gymlog.web.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Your first and last name must be filled in")
    @Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "Enter your first and last name, starting with a capital letter." )
    private String fullName;
    @Size(min = 5, message = "Your username must be at least 5 characters long")
    private String username;
    @Size(min = 5, message = "Your password must be at least 5 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Your password is too weak")
    private String password;
    @NotBlank(message = "The password confirmation field must be filled in")
    private String confirmPassword;
    @Email(message = "Your email is invalid")
    private String email;


}
