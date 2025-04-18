package com.project.videoflow.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VideoController {

    @GetMapping("/video")
    public String reg() {
        return "video";
    }
}
