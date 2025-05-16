package com.project.videoflow.controller;

import com.project.videoflow.model.User;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final VideoRepository videoRepository;
    private final UploadRepository uploadRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ViewRepository viewRepository;

    public HomeController(VideoRepository videoRepository,
                          UploadRepository uploadRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository,
                          ViewRepository viewRepository) {
        this.videoRepository = videoRepository;
        this.uploadRepository = uploadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.viewRepository = viewRepository;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        // Legaktívabb tag lekérése
        String email = uploadRepository.findTopUploaderEmail();
        User user = userRepository.findByEmail(email);
        String felhasznaloNev = (user != null) ? user.getFelhasznalonev() : "ismeretlen";

        // Legaktívabb kommentelő lekérdezése
        String commentEmail = commentRepository.findTopCommenterEmail();
        User commentUser = userRepository.findByEmail(commentEmail);
        String topCommenterName = (commentUser != null) ? commentUser.getFelhasznalonev() : "ismeretlen";

        // Legaktívabb megtekintő lekérdezés
        String topViewerName = viewRepository.findTopViewerUsername();

        List<Video> topLikedVideos = videoRepository.findTop4MostLikedVideos();
        model.addAttribute("topLikedVideos", topLikedVideos);

        model.addAttribute("topViewerName", topViewerName);
        model.addAttribute("topCommenterName", topCommenterName);
        model.addAttribute("topUploaderName", felhasznaloNev);
        model.addAttribute("videos", videoRepository.findAll());
        model.addAttribute("session", session);

        return "home";
    }
}

