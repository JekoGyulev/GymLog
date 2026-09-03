package com.example.gymlog.web.controller;


import com.example.gymlog.security.UserPrincipal;
import com.example.gymlog.user.model.User;
import com.example.gymlog.user.service.UserService;
import com.example.gymlog.web.dto.UpdatePhotoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

        // TODO: Add ProfileEditRequest as object to this view - when reached 3

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userAge", Math.abs(Period.between(LocalDate.now(), user.getBirthDate()).getYears()));
        modelAndView.addObject("updatePhotoRequest", new UpdatePhotoRequest());


        return modelAndView;
    }

    // TODO: Implement changing personal information and body information functionality with 1 PATCH handler method - 3


    @PatchMapping("/update-photo")
    public ModelAndView updatePhoto(@RequestParam(value = "delete", defaultValue = "false") boolean deletePhoto,
                              @AuthenticationPrincipal UserPrincipal userPrincipal,
                              UpdatePhotoRequest updatePhotoRequest) {

        this.userService.updatePhoto(updatePhotoRequest, deletePhoto, userPrincipal.getId());

        return new ModelAndView("redirect:/users/profile");
    }

    // TODO: Implement changing password - 2

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
