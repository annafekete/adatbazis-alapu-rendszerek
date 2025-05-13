package com.project.videoflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.videoflow.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String reg(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("loggedInUser");
        Long role = user.getSzerepkor().getSzerepkorid();
        if(role == 1 || role == 5) {
            return "redirect:/profile";
        }
        return "upload";
    }
}
