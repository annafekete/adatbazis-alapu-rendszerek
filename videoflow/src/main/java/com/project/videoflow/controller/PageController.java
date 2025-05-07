package com.project.videoflow.controller;

import com.project.videoflow.model.*;
import com.project.videoflow.repository.*;
import com.project.videoflow.service.PlaylistService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    private final VideoRepository videoRepository;
    private final UploadRepository uploadRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ViewRepository viewRepository;
    private final CreatePLRepository createPLRepository;
    private final PlaylistService playlistService;

    public PageController(VideoRepository videoRepository, UploadRepository uploadRepository, UserRepository userRepository,
                          CommentRepository commentRepository, ViewRepository viewRepository, CreatePLRepository createPLRepository,
                          PlaylistService playlistService) {
        this.createPLRepository = createPLRepository;
        this.playlistService = playlistService;
        this.videoRepository = videoRepository;
        this.uploadRepository = uploadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.viewRepository = viewRepository;
    }

    @GetMapping("/videos/{id}")
    public String getVideoDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<Video> videoOpt = videoRepository.findById(id);
        if (videoOpt.isEmpty()) return "redirect:/";

        Video video = videoOpt.get();

        // ➕ Megtekintésszám növelése
        video.setMegtekintesSzam(video.getMegtekintesSzam() + 1);
        videoRepository.save(video);

        // ➕ Néző elmentése, ha be van jelentkezve
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            Nez nez = new Nez();
            nez.setVideoid(video.getVideoid());
            nez.setEmail(loggedInUser.getEmail());
            viewRepository.save(nez);
        }

        // Feltöltő e-mail lekérdezése
        Optional<Upload> uploadOpt = uploadRepository.findFirstByVideoid(video.getVideoid());
        String email = uploadOpt.map(Upload::getEmail).orElse(null);

        // Feltöltő egyéb videói
        List<Video> otherVideos = email != null
                ? videoRepository.findOtherVideosByUploader(email, video.getVideoid())
                : List.of();


        // Feltolto lejatszasi listai
        List<CreatePL> createdPlaylists = createPLRepository.findByEmail(email);
        List<Long> userPlaylistIds = createdPlaylists.stream()
            .map(CreatePL::getPlaylistid)
            .toList();
        List<Playlist> userPlaylists = playlistService.getPlaylistById(userPlaylistIds);

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
        model.addAttribute("playlists", userPlaylists);


        return "video"; // video.html sablonhoz
    }

    @PostMapping("/videos/{id}/comment")
    public String postComment(@PathVariable("id") Long id, @RequestParam("tartalom") String tartalom, HttpSession session) {
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
