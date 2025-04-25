/*
package com.project.videoflow.controller;

import com.project.videoflow.model.Video;
import com.project.videoflow.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    // Videó feltöltés és mentés adatbázisba
    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("video") MultipartFile videoFile,
                              @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("category") String category,
                              @RequestParam("keyword") String keyword) throws IOException {

        // Videó mentése a szerverre
        String videoPath = "uploads/" + videoFile.getOriginalFilename();
        videoFile.transferTo(new File(videoPath));

        // Videó adatainak mentése az adatbázisba
        Video newVideo = new Video();
        newVideo.setVideocim(title);
        newVideo.setLeiras(description);
        newVideo.setKategoria(category);
        newVideo.setKulcsszo(keyword);
        newVideo.setMegtekintesSzam(0); // Kezdetben 0 megtekintés

        videoRepository.save(newVideo);

        return "Video uploaded and saved successfully!";
    }
    @GetMapping("/{videoId}")
    public Video getVideoById(@PathVariable Long videoId) {
        return videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
    }
}
*/

package com.project.videoflow.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VideoController {

    @GetMapping("/video")
    public String reg() {
        return "video";
    }
}

