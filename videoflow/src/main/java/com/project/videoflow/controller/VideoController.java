package com.project.videoflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.videoflow.service.VideoService;
import jakarta.servlet.http.HttpSession;
import com.project.videoflow.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.VideoRepository;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;

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

    @GetMapping("")
    public String showHome(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Video> videos;
        if (query != null && !query.isEmpty()) {
            videos = videoRepository.searchByVideocim(query);
        }
        else {
            videos = videoRepository.findAll();
        }
        model.addAttribute("videos", videos);
        return "home";
    }

    @GetMapping("/category")
    public String filterByCategory(
            @RequestParam(value = "name", required = false) String categoryName,
            @RequestParam(value = "filter", required = false, defaultValue = "none") String filter,
            Model model) {

        List<Video> videos;

        // Ellenőrizzük, hogy a kategória ALL vagy üres-e
        boolean isAllOrEmpty = categoryName == null || categoryName.trim().isEmpty() || "ALL".equalsIgnoreCase(categoryName) || " ".equals(categoryName);

        // Szűrés a filter paraméter alapján
        switch (filter) {
            case "topViewed":
                if (isAllOrEmpty) {
                    videos = videoRepository.findAllOrderByViews();
                } else {
                    videos = videoRepository.findTopByCategoryOrderByViews(categoryName);
                }
                break;
            case "newest":
                if (isAllOrEmpty) {
                    videos = videoRepository.findAllOrderByDate();
                } else {
                    videos = videoRepository.findTopByCategoryOrderByDate(categoryName);
                }
                break;
            case "mostLiked":
                if (isAllOrEmpty) {
                    videos = videoRepository.findAllOrderByLikes();
                } else {
                    videos = videoRepository.findTopByCategoryOrderByLikes(categoryName);
                }
                break;
            default:
                if (isAllOrEmpty) {
                    videos = videoRepository.findAll();
                } else {
                    videos = videoRepository.findByKategoriaIgnoreCase(categoryName);
                }
        }

        model.addAttribute("videos", videos);
        model.addAttribute("selectedCategory", categoryName);
        model.addAttribute("filter", filter);

        return "home";
    }

    @GetMapping("/top-viewed")
    public String getTopViewedVideos(Model model) {
        List<Video> videos = videoRepository.findAllOrderByViews();
        model.addAttribute("videos", videos);
        return "home";
    }

    @GetMapping("/most-liked")
    public String getMostLikedVideos(Model model) {
        List<Video> videos = videoRepository.findAllOrderByLikes();
        model.addAttribute("videos", videos);
        return "home";
    }

    @GetMapping("/newest")
    public String getNewestVideos(Model model) {
        List<Video> videos = videoRepository.findAllOrderByDate();
        model.addAttribute("videos", videos);
        return "home";
    }
}