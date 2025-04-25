package com.project.videoflow.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/out")
    public String logout(HttpSession session) {
        session.invalidate(); // törli a session-t
        return "login"; // vissza a login oldalra, kis visszajelzéssel
    }

}
