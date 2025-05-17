package com.project.videoflow.controller;

import com.project.videoflow.model.*;
import com.project.videoflow.repository.*;
import com.project.videoflow.service.PlaylistService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class PageController {

    private final VideoRepository videoRepository;
    private final UploadRepository uploadRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ViewRepository viewRepository;
    private final CreatePLRepository createPLRepository;
    private final PlaylistService playlistService;
    private LikeRepository likeRepository;
    private AddtoPLRepository addtoPLRepository;

    public PageController(VideoRepository videoRepository, UploadRepository uploadRepository,
            UserRepository userRepository,
            CommentRepository commentRepository, ViewRepository viewRepository, CreatePLRepository createPLRepository,
            PlaylistService playlistService, LikeRepository likeRepository, AddtoPLRepository addtoPLRepository) {
        this.addtoPLRepository = addtoPLRepository;
        this.createPLRepository = createPLRepository;
        this.playlistService = playlistService;
        this.videoRepository = videoRepository;
        this.uploadRepository = uploadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.viewRepository = viewRepository;
        this.likeRepository = likeRepository;
    }

    /*
     * @GetMapping("/videos/{id}")
     * public String getVideoDetails(@PathVariable("id") Long id, Model model,
     * HttpSession session) {
     * Optional<Video> videoOpt = videoRepository.findById(id);
     * if (videoOpt.isEmpty()) return "redirect:/";
     * 
     * Video video = videoOpt.get();
     * 
     * // ➕ Megtekintésszám növelése
     * video.setMegtekintesSzam(video.getMegtekintesSzam() + 1);
     * videoRepository.save(video);
     * 
     * // ➕ Néző elmentése, ha be van jelentkezve
     * User loggedInUser = (User) session.getAttribute("loggedInUser");
     * if (loggedInUser != null) {
     * Nez nez = new Nez();
     * nez.setVideoid(video.getVideoid());
     * nez.setEmail(loggedInUser.getEmail());
     * viewRepository.save(nez);
     * }
     * 
     * // Feltöltő e-mail lekérdezése
     * Optional<Upload> uploadOpt =
     * uploadRepository.findFirstByVideoid(video.getVideoid());
     * String email = uploadOpt.map(Upload::getEmail).orElse(null);
     * 
     * // Feltöltő egyéb videói
     * List<Video> otherVideos = email != null
     * ? videoRepository.findOtherVideosByUploader(email, video.getVideoid())
     * : List.of();
     * 
     * 
     * // Feltolto lejatszasi listai
     * String userEmail = loggedInUser != null ? loggedInUser.getEmail() : null;
     * List<CreatePL> createdPlaylists = createPLRepository.findByEmail(userEmail);
     * List<Long> userPlaylistIds = createdPlaylists.stream()
     * .map(CreatePL::getPlaylistid)
     * .toList();
     * List<Playlist> userPlaylists =
     * playlistService.getPlaylistById(userPlaylistIds);
     * 
     * User user = userRepository.findByEmail(email);
     * String felhasznalonev = (user != null) ? user.getFelhasznalonev() :
     * "Ismeretlen";
     * Date feltoltesIdeje = uploadOpt.map(Upload::getFeltoltesIdeje).orElse(null);
     * List<Comment> hozzaszolasok = commentRepository.findCommentsByVideoId(id);
     * 
     * model.addAttribute("video", video);
     * model.addAttribute("hozzaszolasok", hozzaszolasok);
     * model.addAttribute("feltoltesIdeje", feltoltesIdeje);
     * model.addAttribute("feltoltoNev", felhasznalonev);
     * model.addAttribute("video", video);
     * model.addAttribute("otherVideos", otherVideos);
     * model.addAttribute("playlists", userPlaylists);
     * 
     * 
     * return "video"; // video.html sablonhoz
     * }
     */

    @GetMapping("/videos/{id}")
    public String getVideoDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<Video> videoOpt = videoRepository.findById(id);
        if (videoOpt.isEmpty())
            return "redirect:/";

        Video video = videoOpt.get();

        // Felhasználó lekérése session-ből
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Néző mentése, ha be van jelentkezve
        if (loggedInUser != null) {
            Nez nez = new Nez();
            nez.setVideoid(video.getVideoid());
            nez.setEmail(loggedInUser.getEmail());
            viewRepository.save(nez);
        }

        // Feltöltő adatok
        Optional<Upload> uploadOpt = uploadRepository.findFirstByVideoid(video.getVideoid());
        String email = uploadOpt.map(Upload::getEmail).orElse(null);
        User uploader = userRepository.findByEmail(email);
        String feltoltoNev = uploader != null ? uploader.getFelhasznalonev() : "Ismeretlen";
        Date feltoltesIdeje = uploadOpt.map(Upload::getFeltoltesIdeje).orElse(null);

        // Hozzászólások
        List<Comment> hozzaszolasok = commentRepository.findCommentsByVideoId(id);

        // Más videók a feltöltőtől
        List<Video> otherVideos = email != null
                ? videoRepository.findOtherVideosByUploader(email, video.getVideoid())
                : List.of();

        // Hasonló videók kulcsszó alapján
        List<Video> similarKeywordVideos = videoRepository.findSimilarVideosByKeyword(video.getKulcsszo(),
                video.getVideoid());

        // Playlist-ek
        String userEmail = loggedInUser != null ? loggedInUser.getEmail() : null;
        List<CreatePL> createdPlaylists = createPLRepository.findByEmail(userEmail);
        List<Long> userPlaylistIds = createdPlaylists.stream()
                .map(CreatePL::getPlaylistid)
                .toList();
        List<Playlist> userPlaylists = playlistService.getPlaylistById(userPlaylistIds);

        // ✅ LIKE funkcióhoz szükséges változók
        boolean loggedIn = loggedInUser != null;
        boolean alreadyLiked = false;

        if (loggedIn) {
            alreadyLiked = likeRepository.existsByEmailAndVideoid(loggedInUser.getEmail(), id);
        }

        int likeCount = likeRepository.countByVideoid(id);

        // Felhasznalok akik lejatszasi listahoz adtak a videot
        List<AddtoPL> addtoPLs = addtoPLRepository.findByVideoid(id);

        

        Set<String> userEmails = new HashSet<>();
        for (AddtoPL addtoPL : addtoPLs) {
            Long playlistId = addtoPL.getPlaylistid();
            CreatePL createPL = createPLRepository.findByPlaylistid(playlistId);
            if (createPL != null) {
                String userEmailFromPL = createPL.getEmail();
                userEmails.add(userEmailFromPL);
            }
        }

        List<User> PLusers = userRepository.findAllByEmailIn(userEmails);

        List<String> PLusernames = PLusers.stream()
                .map(User::getFelhasznalonev)
                .toList();

        //Felhasznalok akik kedveltek a videot
        List<Kedveli> likes = likeRepository.findByVideoid(id);
        Set<String> likers = new HashSet<>();
        for (Kedveli like : likes) {
            String emailFromLike = like.getEmail();
            likers.add(emailFromLike);
        }
        List<User> likersUsers = userRepository.findAllByEmailIn(likers);
        List<String> likersUsernames = likersUsers.stream()
                .map(User::getFelhasznalonev)
                .toList();

        // Model attribútumok
        model.addAttribute("video", video);
        model.addAttribute("feltoltoNev", feltoltoNev);
        model.addAttribute("feltoltesIdeje", feltoltesIdeje);
        model.addAttribute("hozzaszolasok", hozzaszolasok);
        model.addAttribute("otherVideos", otherVideos);
        model.addAttribute("similarKeywordVideos", similarKeywordVideos);
        model.addAttribute("playlists", userPlaylists);
        model.addAttribute("feltoltoEmail", email);
        model.addAttribute("usernames", PLusernames);
        model.addAttribute("likers", likersUsernames);

        // ➕ LIKE funkcióhoz
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("alreadyLiked", alreadyLiked);
        model.addAttribute("likeCount", likeCount);

        return "video";
    }

    @PostMapping("/videos/{id}/comment")
    public String postComment(@PathVariable("id") Long id, @RequestParam("tartalom") String tartalom,
            HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null)
            return "redirect:/login";

        Comment komment = new Comment();
        komment.setVideoid(id);
        komment.setEmail(loggedInUser.getEmail());
        komment.setTartalom(tartalom);

        commentRepository.save(komment);

        return "redirect:/videos/" + id;
    }

    @GetMapping("/videos/{id}/view")
    public String viewVideo(@PathVariable("id") Long id, Model model, HttpSession session) {
        Video video = videoRepository.findById(id).orElse(null);
        if (video == null)
            return "redirect:/videos";

        User user = (User) session.getAttribute("loggedInUser");

        boolean loggedIn = user != null;
        boolean alreadyLiked = false;

        if (loggedIn) {
            alreadyLiked = likeRepository.existsByEmailAndVideoid(user.getEmail(), id);
        }

        int likeCount = likeRepository.countByVideoid(id);

        model.addAttribute("video", video);
        model.addAttribute("likeCount", likeCount);
        model.addAttribute("alreadyLiked", alreadyLiked);
        model.addAttribute("loggedIn", loggedIn);

        return "video";
    }

    @PostMapping("/playlist/create-uploader")
    public String createPlaylistWithUploaderVideos(@RequestParam("feltoltoEmail") String feltoltoEmail, HttpSession session) {
        User uploader = userRepository.findByEmail(feltoltoEmail);

        if (uploader == null) {
            System.out.println("Feltöltő nem található.");
            return "redirect:/";
        }
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            System.out.println("Nincs bejelentkezve felhasználó.");
            return "redirect:/login";
        }

        List<Upload> videos = uploadRepository.findAllByEmail(uploader.getEmail());
        if (videos.isEmpty()) {
            System.out.println("Nincsenek feltöltött videók.");
            return "redirect:/";
        }
    
        Playlist playlist = new Playlist();
        playlist.setPlaylistnev(uploader.getFelhasznalonev() + " feltöltött videói");
        Playlist savedPlaylist = playlistService.createPlaylist(playlist.getPlaylistnev(), loggedInUser.getEmail());
        

        for (Upload video : videos) {
            playlistService.addVideoToPlaylist(savedPlaylist.getPlaylistid(), video.getVideoid());
        }
        System.out.println("Playlist created with videos from: " + uploader.getFelhasznalonev());
        return "redirect:/playlist";
    }

    @PostMapping("/playlist/create-category")
    public String createPlaylistWithCategoryVideos(@RequestParam("kategoria") String kategoria, HttpSession session) {
        if (kategoria == null || kategoria.isEmpty()) {
            System.out.println("Kategória nem található.");
            return "redirect:/";
        }
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            System.out.println("Nincs bejelentkezve felhasználó.");
            return "redirect:/login";
        }

        List<Video> videos = videoRepository.findByKategoria(kategoria);
        if (videos.isEmpty()) {
            System.out.println("Nincsenek feltöltött videók.");
            return "redirect:/";
        }

        Playlist playlist = new Playlist();
        playlist.setPlaylistnev("Kategoria: " + kategoria);
        Playlist savedPlaylist = playlistService.createPlaylist(playlist.getPlaylistnev(), loggedInUser.getEmail());

        for (Video v : videos) {
            playlistService.addVideoToPlaylist(savedPlaylist.getPlaylistid(), v.getVideoid());
        }
        System.out.println("Playlist created with videos from category: " + kategoria);
        return "redirect:/playlist";
    }
}
