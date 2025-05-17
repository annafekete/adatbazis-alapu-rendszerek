package com.project.videoflow.controller;

import com.project.videoflow.model.Kedveli;
import com.project.videoflow.model.User;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.LikeRepository;
import com.project.videoflow.repository.VideoRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LikeController {

    private LikeRepository likeRepository;
    private VideoRepository videoRepository;

    public LikeController(LikeRepository likeRepository, VideoRepository videoRepository) {
        this.likeRepository = likeRepository;
        this.videoRepository = videoRepository;
    }

    @PostMapping("/videos/{id}/like")
    public String likeVideo(@PathVariable("id") Long id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null)
            return "redirect:/login";

        if (!likeRepository.existsByEmailAndVideoid(user.getEmail(), id)) {
            Kedveli like = new Kedveli();
            like.setEmail(user.getEmail());
            like.setVideoid(id);
            likeRepository.save(like);
        }

        return "redirect:/videos/" + id;
    }
}
