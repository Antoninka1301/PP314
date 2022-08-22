package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

import java.security.Principal;

@Controller
public class UserRestController {
    private final UserDetailsServiceImpl userService;

    @Autowired
    public UserRestController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
//    public String pageUser(Principal principal, Model model) {
//        model.addAttribute("user", userService.findByUsername(principal.getName()));
//        return ("user");
//    }
    public ResponseEntity<User> pageUser(@AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(currentUser);
    }
}
