package com.example.gymlog.web.controller;


import com.example.gymlog.security.UserPrincipal;
import com.example.gymlog.user.model.User;
import com.example.gymlog.user.service.UserService;
import com.example.gymlog.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        User user = this.userService.getUserById(userPrincipal.getId());

        UpdatePersonalInformationRequest updatePersonalInformationRequest = DtoMapper.fromUserToPersonalInformationRequest(user);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userAge", Math.abs(Period.between(LocalDate.now(), user.getBirthDate()).getYears()));
        modelAndView.addObject("updatePhotoRequest", new UpdatePhotoRequest());
        modelAndView.addObject("changePasswordRequest", new ChangePasswordRequest());
        modelAndView.addObject("updatePersonalInformationRequest", updatePersonalInformationRequest);


        return modelAndView;
    }


    @PatchMapping("/update-personal-info")
    public ModelAndView updatePersonalInformation(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                  @Valid UpdatePersonalInformationRequest updatePersonalInformationRequest,
                                                  BindingResult bindingResult,
                                                  RedirectAttributes redirectAttributes) {

        User user = this.userService.getUserById(userPrincipal.getId());

        if (bindingResult.hasErrors()) {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-profile");
            modelAndView.addObject("user", user);
            modelAndView.addObject("userAge", Math.abs(Period.between(LocalDate.now(), user.getBirthDate()).getYears()));
            modelAndView.addObject("updatePersonalInformationRequest", updatePersonalInformationRequest);
            modelAndView.addObject("updatePhotoRequest", new UpdatePhotoRequest());
            modelAndView.addObject("changePasswordRequest", new ChangePasswordRequest());
            modelAndView.addObject("scrollTo", "personal-info");
            modelAndView.addObject("editing", true);

            return modelAndView;
        }


        this.userService.updatePersonalInfo(updatePersonalInformationRequest, user);

        redirectAttributes.addFlashAttribute("successfulPersonalInfoUpdate", "You have successfully updated your information!");

        return new ModelAndView("redirect:/users/profile#personal-info");
    }


    @PatchMapping("/update-photo")
    public ModelAndView updatePhoto(@RequestParam(value = "delete", defaultValue = "false") boolean deletePhoto,
                              @AuthenticationPrincipal UserPrincipal userPrincipal,
                              UpdatePhotoRequest updatePhotoRequest) {

        this.userService.updatePhoto(updatePhotoRequest, deletePhoto, userPrincipal.getId());

        return new ModelAndView("redirect:/users/profile");
    }

    @PatchMapping("/change-password")
    public ModelAndView changePassword(@Valid ChangePasswordRequest changePasswordRequest,
                                       BindingResult bindingResult,
                                       @AuthenticationPrincipal UserPrincipal userPrincipal,
                                       RedirectAttributes redirectAttributes) {

        User user = this.userService.getUserById(userPrincipal.getId());

        UpdatePersonalInformationRequest updatePersonalInformationRequest = DtoMapper.fromUserToPersonalInformationRequest(user);


        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-profile");
            modelAndView.addObject("user", user);
            modelAndView.addObject("userAge", Math.abs(Period.between(LocalDate.now(), user.getBirthDate()).getYears()));
            modelAndView.addObject("updatePhotoRequest", new UpdatePhotoRequest());
            modelAndView.addObject("updatePersonalInformationRequest", updatePersonalInformationRequest);
            modelAndView.addObject("scrollTo", "change-password");
            return modelAndView;
        }

        this.userService.changePassword(changePasswordRequest, user);

        redirectAttributes.addFlashAttribute("successfulChangePassword", "You have successfully changed your password!" );

        return new ModelAndView("redirect:/users/profile#change-password");
    }

    // TODO: Implement delete account functionality

    // TODO: Implement export personal information and training & body information to PDF file functionality









    @GetMapping("/{id}/workouts")
    public ModelAndView workoutsPage(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @GetMapping("/{id}/progress")
    public ModelAndView progressPage(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @GetMapping("/{id}/prs")
    public ModelAndView prsPage(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @GetMapping("/{id}/nutrition")
    public ModelAndView nutritionPage(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }


    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView adminDashboardPage(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

}
