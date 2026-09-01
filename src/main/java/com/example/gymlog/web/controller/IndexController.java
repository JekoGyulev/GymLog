package com.example.gymlog.web.controller;


import com.example.gymlog.security.UserPrincipal;
import com.example.gymlog.user.model.User;
import com.example.gymlog.user.service.UserService;
import com.example.gymlog.web.dto.LoginRequest;
import com.example.gymlog.web.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView registerPage(RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());

        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@Valid RegisterRequest registerRequest,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        this.userService.register(registerRequest);

        redirectAttributes.addFlashAttribute("successfulRegistrationMessage", "You have successfully registered!");

        return "redirect:/login";
    }


    @GetMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginRequest", new LoginRequest());

        String loginErrorMessage = (String) request.getSession().getAttribute("error");

        if (loginErrorMessage != null) {
            modelAndView.addObject("loginErrorMessage", loginErrorMessage);
            request.getSession().removeAttribute("error");
        }

        return modelAndView;
    }


    @GetMapping("/home")
    public ModelAndView homePage(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = this.userService.getUserById(userPrincipal.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", user);

        return modelAndView;
    }




    @GetMapping("/forgot-password")
    public ModelAndView forgotPasswordPage() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

}
