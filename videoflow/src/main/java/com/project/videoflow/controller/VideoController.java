package com.project.videoflow.controller;

import org.springframework.web.bind.annotation.*;
import com.project.videoflow.service.VideoService;
import com.project.videoflow.dto.VideoUploadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService; public VideoController(VideoService videoService) { this.videoService = videoService; } @PostMapping("/upload") public ResponseEntity<String> uploadVideo( @RequestParam("video") MultipartFile videoFile, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("category") String category, @RequestParam("keyword") String keyword, @RequestParam("email") String email ) { try { videoService.uploadVideo( videoFile, title, category, keyword, description, email ); return ResponseEntity.ok("Videó sikeresen feltöltve!"); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Hiba történt a feltöltés során: " + e.getMessage()); } }

}
