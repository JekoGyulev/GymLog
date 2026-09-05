package com.example.gymlog.web.dto;



import com.example.gymlog.user.model.User;
import lombok.experimental.UtilityClass;


@UtilityClass
public class DtoMapper {

    public static UpdatePersonalInformationRequest fromUserToPersonalInformationRequest(User user) {
        return UpdatePersonalInformationRequest.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .location(user.getLocation())
                .bio(user.getBio())
                .build();
    }




}
