package com.example.gymlog.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    @NotBlank(message = "The current password field must be filled in")
    private String oldPassword;
    @Size(min = 5, message = "Your password must be at least 5 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Your password is too weak")
    private String newPassword;
    @NotBlank(message = "The password confirmation field must be filled in")
    private String confirmNewPassword;


}
