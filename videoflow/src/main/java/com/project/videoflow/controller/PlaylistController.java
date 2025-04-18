package com.project.videoflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaylistController {

    @GetMapping("/playlist")
    public String playlist() {
        return "playlist";
    }
}
