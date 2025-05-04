package com.project.videoflow.controller;

import org.springframework.ui.Model;
import com.project.videoflow.repository.VideoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final VideoRepository videoRepository;

    public HomeController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("videos", videoRepository.findAll());
        model.addAttribute("session", session); // hogy működjön: ${session.loggedInUser}
        return "home";
    }
}
