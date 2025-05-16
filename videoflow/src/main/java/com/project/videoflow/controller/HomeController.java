package com.project.videoflow.controller;

import com.project.videoflow.model.User;
import com.project.videoflow.repository.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    private final VideoRepository videoRepository;
    private final UploadRepository uploadRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ViewRepository viewRepository;
    private final LikeRepository likeRepository;

    public HomeController(VideoRepository videoRepository,
                          UploadRepository uploadRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository,
                          ViewRepository viewRepository,
                          LikeRepository likeRepository) {
        this.videoRepository = videoRepository;
        this.uploadRepository = uploadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.viewRepository = viewRepository;
        this.likeRepository = likeRepository;
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

        // Legaktívabb kedvelő lekérdezés
        String likerEmail = likeRepository.findTopLiker();
        User likerUser = userRepository.findByEmail(likerEmail);
        String topLikerName = (likerUser != null) ? likerUser.getFelhasznalonev() : "ismeretlen";
        model.addAttribute("topLikerName", topLikerName);

        model.addAttribute("topViewerName", topViewerName);
        model.addAttribute("topCommenterName", topCommenterName);
        model.addAttribute("topUploaderName", felhasznaloNev);
        model.addAttribute("videos", videoRepository.findAll());
        model.addAttribute("session", session);

        return "home";
    }
}

