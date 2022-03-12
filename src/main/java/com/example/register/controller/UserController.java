package com.example.register.controller;

import com.example.register.model.UserModel;
import com.example.register.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());

        return "register_page";
    }
    @GetMapping ("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        System.out.println("register account: " + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getUsername(), userModel.getPassword(), userModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        UserModel authenticated = userService.authenticate(userModel.getUsername(), userModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getUsername());
            return "personal_page";
        } else {
            return "error_page";
        }

    }
}
