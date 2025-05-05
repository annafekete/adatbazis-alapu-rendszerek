package com.project.videoflow.controller;

import com.project.videoflow.model.Upload;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.UploadRepository;
import com.project.videoflow.repository.VideoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    private final VideoRepository videoRepository;
    private final UploadRepository uploadRepository;

    // Konstruktorba mindkét repository
    public PageController(VideoRepository videoRepository, UploadRepository uploadRepository) {
        this.videoRepository = videoRepository;
        this.uploadRepository = uploadRepository;
    }

    @GetMapping("/videos/{id}")
    public String getVideoDetails(@PathVariable Long id, Model model) {
        Optional<Video> videoOpt = videoRepository.findById(id);
        if (videoOpt.isEmpty()) return "redirect:/";

        Video video = videoOpt.get();

        // Feltöltő e-mail lekérdezése
        Optional<Upload> uploadOpt = uploadRepository.findFirstByVideoid(video.getVideoid());
        String email = uploadOpt.map(Upload::getEmail).orElse(null);

        // Feltöltő egyéb videói
        List<Video> otherVideos = email != null
                ? videoRepository.findOtherVideosByUploader(email, video.getVideoid())
                : List.of();

        model.addAttribute("video", video);
        model.addAttribute("otherVideos", otherVideos);
        return "video"; // video.html sablonhoz
    }
}
