package com.example.gymlog.web.dto;



import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePhotoRequest {

    private MultipartFile photoFile;
}
