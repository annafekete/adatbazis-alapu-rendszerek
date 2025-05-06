package com.project.videoflow.controller;

import org.springframework.web.bind.annotation.*;

import com.project.videoflow.service.VideoService;

import jakarta.servlet.http.HttpSession;

import com.project.videoflow.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("video") MultipartFile videoFile,
            @RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam("category") String category, @RequestParam("keyword") String keyword, HttpSession session) {
        try {
            if (session.getAttribute("loggedInUser") == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Nem vagy bejelentkezve! Kérlek jelentkezz be a feltöltéshez.");
            }
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Profil nem található! Kérlek ellenőrizd a bejelentkezést.");
            }
            String email = loggedInUser.getEmail();

            videoService.uploadVideo(videoFile, title, category, keyword, description, email);
            return ResponseEntity.ok("Videó sikeresen feltöltve!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt a feltöltés során: " + e.getMessage());
        }
    }

}
