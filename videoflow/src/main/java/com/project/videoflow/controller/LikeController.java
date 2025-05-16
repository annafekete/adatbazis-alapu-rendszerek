package com.project.videoflow.controller;

import com.project.videoflow.model.Kedveli;
import com.project.videoflow.model.User;
import com.project.videoflow.repository.LikeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LikeController {

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping("/videos/{id}/like")
    public String likeVideo(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        if (!likeRepository.existsByEmailAndVideoid(user.getEmail(), id)) {
            Kedveli like = new Kedveli();
            like.setEmail(user.getEmail());
            like.setVideoid(id);
            likeRepository.save(like);
        }

        return "redirect:/videos/" + id + "/view";
    }
}
