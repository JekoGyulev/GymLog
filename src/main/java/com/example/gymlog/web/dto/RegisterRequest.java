package com.example.gymlog.web.dto;


import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Your first and last name must be filled in")
    @Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "Enter your first and last name, starting with a capital letter." )
    private String fullName;
    @NotNull(message = "Your birthdate must be filled in")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @Size(min = 5, message = "Your username must be at least 5 characters long")
    private String username;
    @Size(min = 5, message = "Your password must be at least 5 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Your password is too weak")
    private String password;
    @NotBlank(message = "The password confirmation field must be filled in")
    private String confirmPassword;
    @Email(message = "Your email is invalid")
    @NotBlank(message = "Your email must be filled in")
    private String email;


}
