package com.guzov.controller;

import com.guzov.domain.Role;
import com.guzov.domain.User;
import com.guzov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String userList(Map<String, Object> model) {
        model.put("users", userService.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Map<String, Object> model) {
        model.put("user", user);
        model.put("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.updateUser(user, username, form);
        return "redirect:/user";
    }


    @GetMapping("profile")
    public String getProfile(Map<String, Object> model, @AuthenticationPrincipal User user) {
        model.put("username", user.getUsername());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam String password) {
        userService.updateProfile(user, password);

        return "redirect:/user/profile";
    }



    @PostMapping("subscribe")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @RequestParam User user
    ) {
        userService.subscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getId();
    }



    @PostMapping("unsubscribe")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @RequestParam User user
    ) {
        userService.unsubscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getId();
    }
}
