/*package com.project.videoflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String reg() {
        return "upload";
    }
}*//*

package com.project.videoflow.controller;

import com.project.videoflow.model.Video;
import com.project.videoflow.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/videos")
public class UploadController {

    @Autowired
    private VideoService videoService;

    @Value("${video.upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> uploadVideo(
            @RequestParam("video") MultipartFile videoFile,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("keyword") String keyword,
            @AuthenticationPrincipal UserDetails userDetails) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Create directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate unique filename
            String originalFilename = videoFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            String filePath = uploadDir + File.separator + uniqueFilename;

            // Save file to server
            Path path = Paths.get(filePath);
            Files.write(path, videoFile.getBytes());

            // Save metadata to database
            Video video = new Video();
            video.setVideocim(title);
            video.setLeiras(description);
            video.setKategoria(category);
            video.setKulcsszo(keyword);
            video.setFilePath(uniqueFilename);
            video.setUsername(userDetails.getUsername());

            Video savedVideo = videoService.saveVideo(video);

            response.put("success", true);
            response.put("videoId", savedVideo.getId());
            response.put("message", "Video uploaded successfully");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Failed to upload video: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/{id}")
    public String viewVideo(@PathVariable Long id) {
        return "redirect:/videos/" + id;
    }
}*/
package com.project.videoflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String reg() {
        return "upload";
    }
}
