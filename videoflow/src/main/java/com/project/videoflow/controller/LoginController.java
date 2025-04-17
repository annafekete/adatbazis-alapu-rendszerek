package com.project.videoflow.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/log")
    public String reg() {
        return "login";
    }
}

