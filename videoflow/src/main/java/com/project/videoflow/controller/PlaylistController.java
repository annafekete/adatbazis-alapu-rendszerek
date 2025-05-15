package com.project.videoflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.videoflow.model.AddtoPL;
import com.project.videoflow.model.CreatePL;
import com.project.videoflow.model.Playlist;
import com.project.videoflow.model.User;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.AddtoPLRepository;
import com.project.videoflow.repository.CreatePLRepository;
import com.project.videoflow.repository.VideoRepository;
import com.project.videoflow.service.PlaylistService;
import com.project.videoflow.service.VideoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PlaylistController {

    private final PlaylistService playlistService;
    private final CreatePLRepository createPLRepository;
    private final AddtoPLRepository addtoPLRepository;
    private final VideoRepository videoRepository;
    private final VideoService videoService;

    public PlaylistController(PlaylistService playlistService, CreatePLRepository createPLRepository,
            AddtoPLRepository addtoPLRepository, VideoRepository videoRepository, VideoService videoService) {
        this.videoService = videoService;
        this.videoRepository = videoRepository;
        this.addtoPLRepository = addtoPLRepository;
        this.playlistService = playlistService;
        this.createPLRepository = createPLRepository;

    }

    @GetMapping("/playlist")
    public String playlist(HttpSession session, Model model,
            @RequestParam(value = "playlistId", required = false) Long playlistId) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            model.addAttribute("error", "Nem vagy bejelentkezve! Kérlek jelentkezz be a feltöltéshez.");
            return "redirect:/login";
        }
        String email = loggedInUser.getEmail();
        List<CreatePL> createdPlaylists = createPLRepository.findByEmail(email);
        List<Long> userPlaylistIds = createdPlaylists.stream()
                .map(CreatePL::getPlaylistid)
                .toList();
        List<Playlist> userPlaylists = playlistService.getPlaylistById(userPlaylistIds);
        model.addAttribute("playlists", userPlaylists);

        List<AddtoPL> addtoPLs = addtoPLRepository.findByPlaylistid(playlistId);
        List<Long> videoids = addtoPLs.stream()
                .map(AddtoPL::getVideoid)
                .toList();
        List<Video> videos = videoService.getVideosById(videoids);
        model.addAttribute("videos", videos);
        return "playlist";
    }

    @GetMapping("/createPlaylist")
    public String createPlaylist() {
        return "createPlaylist";
    }

    @PostMapping("/createPlaylist")
    public String createPlaylist(@RequestParam("playlistnev") String playlistnev, HttpSession session, Model model) {
        if (session.getAttribute("loggedInUser") == null) {
            model.addAttribute("error", "Nem vagy bejelentkezve! Kérlek jelentkezz be a feltöltéshez.");
            return "createPlaylist";
        }
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            model.addAttribute("error", "Profil nem található! Kérlek ellenőrizd a bejelentkezést.");
            return "createPlaylist";
        }
        String email = loggedInUser.getEmail();
        try {
            playlistService.createPlaylist(playlistnev, email);
            return "redirect:/playlist";
        } catch (Exception e) {
            model.addAttribute("error", "Hiba történt a letrehozas során: " + e.getMessage());
            return "createPlaylist";
        }
    }

    @DeleteMapping("/deletePlaylist/{id}")
    @ResponseBody
    public ResponseEntity<?> deletePlaylist(@PathVariable("id") Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        playlistService.deletePlaylist(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/playlist/addVideo")
    public String addVideoToPlaylist(@RequestParam("playlistId") Long playlistid, @RequestParam("videoId") Long videoid,
            HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        try {
            playlistService.addVideoToPlaylist(playlistid, videoid);
            return "redirect:/videos/" + videoid;
        } catch (Exception e) {
            return "redirect:/playlist?error=" + e.getMessage();
        }

    }

    @DeleteMapping("/playlist/removeVideo")
    @ResponseBody
    public ResponseEntity<?> removeVideoFromPlaylist(@RequestParam("videoId") Long videoid, @RequestParam("playlistId") Long playlistid, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            playlistService.removeVideoFromPlaylist(videoid, playlistid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/playlist/videos")
    @ResponseBody
    public List<Video> getPlaylistVideos(@RequestParam("playlistId") Long playlistId) {
        List<AddtoPL> addtoPLs = addtoPLRepository.findByPlaylistid(playlistId);
        List<Long> videoids = addtoPLs.stream().map(AddtoPL::getVideoid).toList();
        return videoService.getVideosById(videoids);
    }
}
