package com.project.videoflow.controller;

import com.project.videoflow.dto.LoginRequest;
import com.project.videoflow.model.User;
import com.project.videoflow.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute LoginRequest request, Model model, HttpSession session) {
        try {
            User user = authService.login(request);

            session.setAttribute("loggedInUser", user);
            session.setAttribute("isAdmin", isAdmin(user)); // új!

            System.out.println("Szerepkör: " + user.getSzerepkor().getSzerepkornev());
            System.out.println("isAdmin: " + isAdmin(user));

            model.addAttribute("success", "Sikeres bejelentkezés!");
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    private boolean isAdmin(User user) {
        String roleName = user.getSzerepkor().getSzerepkornev();
        return "admin".equalsIgnoreCase(roleName) || "szuperadmin".equalsIgnoreCase(roleName);
    }

}
