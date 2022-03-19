package com.example.register.controller;

import com.example.register.model.User;
import com.example.register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register_login/register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "register_login/login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User userModel) {
        System.out.println("register account: " + userModel);
        User registeredUser = userService.registerUser(userModel.getUsername(), userModel.getPassword(), userModel.getEmail());
        return registeredUser == null ? "error_pages/error_page_login" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User userModel, Model model) {
        User authenticated = userService.authenticate(userModel.getUsername(), userModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getUsername());
            return "user_page/personal_page";
        } else {
            return "error_pages/error_page_login";
        }
    }

}