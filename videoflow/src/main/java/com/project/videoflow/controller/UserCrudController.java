package com.project.videoflow.controller;

import com.project.videoflow.model.*;
import com.project.videoflow.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/crud")
public class UserCrudController {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private CommentRepository commentRepository;
    @Autowired private VideoRepository videoRepository;
    @Autowired private UploadRepository uploadRepository;
    @Autowired private LikeRepository kedveliRepository;

    @GetMapping
    public String list(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !isAdmin(user)) return "redirect:/login";

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("newUser", new User());

        model.addAttribute("comments", commentRepository.findAll());
        model.addAttribute("newComment", new Comment());

        model.addAttribute("videos", videoRepository.findAll());
        model.addAttribute("newVideo", new Video());

        model.addAttribute("uploads", uploadRepository.findAll());
        model.addAttribute("likes", kedveliRepository.findAll());

        return "user-crud";
    }

    private boolean isAdmin(User user) {
        String role = user.getSzerepkor().getSzerepkornev();
        return "admin".equalsIgnoreCase(role) || "szuperadmin".equalsIgnoreCase(role);
    }

    // === USER ===
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        user.setJelszo(passwordEncoder.encode(user.getJelszo()));
        userRepository.save(user);
        return "redirect:/crud";
    }

    @PostMapping("/edit/{email}")
    public String editUser(@PathVariable String email,
                           @RequestParam String felhasznalonev,
                           @RequestParam String jelszo,
                           @RequestParam Long szerepkorId) {
        User user = userRepository.findById(email).orElse(null);
        if (user != null) {
            user.setFelhasznalonev(felhasznalonev);
            if (!jelszo.isBlank()) user.setJelszo(passwordEncoder.encode(jelszo));
            roleRepository.findById(szerepkorId).ifPresent(user::setSzerepkor);
            userRepository.save(user);
        }
        return "redirect:/crud";
    }

    @GetMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        userRepository.deleteById(email);
        return "redirect:/crud";
    }

    // === COMMENTS ===
    @PostMapping("/comments/add")
    public String addComment(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return "redirect:/crud";
    }

    @PostMapping("/comments/edit/{id}")
    public String editComment(@PathVariable Long id, @RequestParam Long videoid,
                              @RequestParam String email, @RequestParam String tartalom) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c != null) {
            c.setVideoid(videoid);
            c.setEmail(email);
            c.setTartalom(tartalom);
            commentRepository.save(c);
        }
        return "redirect:/crud";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return "redirect:/crud";
    }

    // === VIDEOS ===
    @PostMapping("/videos/add")
    public String addVideo(@ModelAttribute Video video) {
        videoRepository.save(video);
        return "redirect:/crud";
    }

    @PostMapping("/videos/edit/{id}")
    public String editVideo(@PathVariable Long id,
                            @RequestParam String videocim,
                            @RequestParam String kategoria,
                            @RequestParam String leiras,
                            @RequestParam String kulcsszo) {
        Video v = videoRepository.findById(id).orElse(null);
        if (v != null) {
            v.setVideocim(videocim);
            v.setKategoria(kategoria);
            v.setLeiras(leiras);
            v.setKulcsszo(kulcsszo);
            videoRepository.save(v);
        }
        return "redirect:/crud";
    }

    @GetMapping("/videos/delete/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoRepository.deleteById(id);
        return "redirect:/crud";
    }

    @PostMapping("/uploads/add")
    public String addUpload(@RequestParam String email, @RequestParam Long videoid) {
        Upload upload = new Upload();
        upload.setEmail(email);
        upload.setVideoid(videoid);
        upload.setFeltoltesIdeje(new Date());
        uploadRepository.save(upload);
        return "redirect:/crud";
    }

    @PostMapping("/uploads/delete")
    public String deleteUpload(@RequestParam String email, @RequestParam Long videoid) {
        UploadId id = new UploadId();
        id.setEmail(email);
        id.setVideoid(videoid);
        uploadRepository.deleteById(id);
        return "redirect:/crud";
    }

    @PostMapping("/likes/add")
    public String addLike(@RequestParam String email, @RequestParam Long videoid) {
        Kedveli kedveli = new Kedveli();
        kedveli.setEmail(email);
        kedveli.setVideoid(videoid);
        kedveliRepository.save(kedveli);
        return "redirect:/crud";
    }

    @PostMapping("/likes/delete")
    public String deleteLike(@RequestParam String email, @RequestParam Long videoid) {
        KedveliId id = new KedveliId();
        id.setEmail(email);
        id.setVideoid(videoid);
        kedveliRepository.deleteById(id);
        return "redirect:/crud";
    }


}
