package com.example.gymlog.web.dto;


import com.example.gymlog.user.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePersonalInformationRequest {

    @NotBlank(message = "Your first name must be filled in")
    @Pattern(regexp = "^[A-Z][a-z]*(?:['-][A-Z][a-z]*)*$", message = "Enter your first name, starting with a capital letter" )
    private String firstName;
    @NotBlank(message = "Your last name must be filled in")
    @Pattern(regexp = "^[A-Z][a-z]*(?:['-][A-Z][a-z]*)*$", message = "Enter your last name, starting with a capital letter")
    private String lastName;
    @Size(min = 5, message = "Your username must be at least 5 characters long")
    @NotBlank(message = "Your username must be filled in")
    private String username;
    @Email(message = "Your email is invalid")
    @NotBlank(message = "Your email must be filled in")
    private String email;
    @Pattern(regexp = "^\\+?\\d*$", message = "Your phone number is invalid")
    private String phone;
    @NotNull(message = "Your birthdate must be filled in")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private Gender gender;
    private String location;
    private String bio;


}
