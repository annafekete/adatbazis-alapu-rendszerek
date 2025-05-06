package com.project.videoflow.controller;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.videoflow.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> uploadVideo(
            @RequestParam("video") MultipartFile videoFile,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("keyword") String keyword,
            @RequestParam("email") String email
    ) {
        try {
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
            @RequestParam("name") String categoryName,
            @RequestParam(value = "filter", required = false, defaultValue = "none") String filter,
            Model model) {

        List<Video> videos;

        // Debugging: Log the input parameters
        System.out.println("Category: " + categoryName + ", Filter: " + filter);

        // Ha "all" kategória van kiválasztva vagy üres a kategória
        if (categoryName == null || categoryName.trim().isEmpty() || categoryName.equals("all")) {
            // Az összes videó között szűrünk
            switch (filter) {
                case "topViewed":
                    System.out.println("Fetching all videos ordered by views");
                    videos = videoRepository.findAllOrderByViews();
                    break;
                case "newest":
                    System.out.println("Fetching all videos ordered by date");
                    videos = videoRepository.findAllOrderByDate();
                    break;
                case "mostLiked":
                    System.out.println("Fetching all videos ordered by likes");
                    videos = videoRepository.findAllOrderByLikes();
                    break;
                default:
                    System.out.println("Fetching all videos with no specific order");
                    videos = videoRepository.findAll();
            }
        } else {
            // Kategórián belül szűrünk
            switch (filter) {
                case "topViewed":
                    System.out.println("Fetching videos in category '" + categoryName + "' ordered by views");
                    videos = videoRepository.findTopByCategoryOrderByViews(categoryName);
                    break;
                case "newest":
                    System.out.println("Fetching videos in category '" + categoryName + "' ordered by date");
                    videos = videoRepository.findTopByCategoryOrderByDate(categoryName);
                    break;
                case "mostLiked":
                    System.out.println("Fetching videos in category '" + categoryName + "' ordered by likes");
                    videos = videoRepository.findTopByCategoryOrderByLikes(categoryName);
                    break;
                default:
                    System.out.println("Fetching videos in category '" + categoryName + "' with no specific order");
                    videos = videoRepository.findByKategoriaIgnoreCase(categoryName);
            }
        }

        System.out.println("Number of videos found: " + (videos != null ? videos.size() : "null"));

        model.addAttribute("videos", videos);
        model.addAttribute("selectedCategory", categoryName);
        model.addAttribute("filter", filter);

        return "home";
    }

    @GetMapping("/home")
    public String showHomee(@RequestParam(required = false) String filter, Model model) {
        // Kategóriák listája, amit szűrni fogunk
        List<String> categories = List.of("funny", "education", "music", "food", "animals", "sports", "travel", "other");

        // Tároljuk a szűrt videókat
        Map<String, List<Video>> filteredVideos = new HashMap<>();

        // A kategóriák mindegyikére végigmegyünk
        for (String category : categories) {
            List<Video> videos = new ArrayList<>();

            // Szűrés alapján videók lekérése
            switch (filter) {
                case "topViewed":
                    videos = videoRepository.findTopByCategoryOrderByViews(category);
                    break;
                case "newest":
                    videos = videoRepository.findTopByCategoryOrderByDate(category);
                    break;
                case "mostLiked":
                    videos = videoRepository.findTopByCategoryOrderByLikes(category);
                    break;
                default:
                    videos = videoRepository.findByKategoriaIgnoreCase(category);
            }

            filteredVideos.put(category, videos);
        }

        model.addAttribute("filteredVideos", filteredVideos);
        model.addAttribute("filter", filter);

        return "home";
    }
}