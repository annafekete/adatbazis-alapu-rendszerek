package com.project.videoflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpdateController {

    @GetMapping("/update")
    public String reg() {
        return "update";
    }
}

