package com.project.videoflow.controller;

import com.project.videoflow.model.Comment;
import com.project.videoflow.model.Upload;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.CommentRepository;
import com.project.videoflow.repository.UploadRepository;
import com.project.videoflow.repository.VideoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.project.videoflow.model.User;
import com.project.videoflow.repository.UserRepository;

@Controller
public class PageController {

    private final VideoRepository videoRepository;
    private final UploadRepository uploadRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // Konstruktorba mindkét repository
    public PageController(VideoRepository videoRepository, UploadRepository uploadRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.videoRepository = videoRepository;
        this.uploadRepository = uploadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
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

        User user = userRepository.findByEmail(email);
        String felhasznalonev = (user != null) ? user.getFelhasznalonev() : "Ismeretlen";
        Date feltoltesIdeje = uploadOpt.map(Upload::getFeltoltesIdeje).orElse(null);
        List<Comment> hozzaszolasok = commentRepository.findCommentsByVideoId(id);

        model.addAttribute("video", video);
        model.addAttribute("hozzaszolasok", hozzaszolasok);
        model.addAttribute("feltoltesIdeje", feltoltesIdeje);
        model.addAttribute("feltoltoNev", felhasznalonev);
        model.addAttribute("video", video);
        model.addAttribute("otherVideos", otherVideos);

        return "video"; // video.html sablonhoz
    }

    @PostMapping("/videos/{id}/comment")
    public String postComment(@PathVariable Long id, @RequestParam String tartalom, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";

        Comment komment = new Comment();
        komment.setVideoid(id);
        komment.setEmail(loggedInUser.getEmail());
        komment.setTartalom(tartalom);

        commentRepository.save(komment);

        return "redirect:/videos/" + id;
    }

}
