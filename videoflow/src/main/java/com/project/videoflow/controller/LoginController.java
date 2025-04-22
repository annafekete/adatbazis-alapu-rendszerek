package com.project.videoflow.controller;

import com.project.videoflow.dto.LoginRequest;
import com.project.videoflow.model.User;
import com.project.videoflow.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/log")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/log")
    public String login(@ModelAttribute LoginRequest loginRequest, Model model) {
        System.out.println("Beérkezett login adat: felhasznalonev=" + loginRequest.getFelhasznalonev() + ", jelszo=" + loginRequest.getJelszo());
        try {
            User user = authService.login(loginRequest);
            System.out.println("Sikeres login: " + user.getFelhasznalonev());
            model.addAttribute("user", user);
            model.addAttribute("success", "Sikeres bejelentkezés!");
            return "home";
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

}
