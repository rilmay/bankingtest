package com.guzov.controller;

import com.guzov.domain.User;
import com.guzov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (userService.registerUser(user)) {
            return "redirect:/login";
        } else {
            model.put("message", "User already exists");
            return "registration";
        }
    }
}
