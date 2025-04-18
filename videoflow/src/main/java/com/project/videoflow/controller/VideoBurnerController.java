package com.project.videoflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.videoflow.model.VideoProcessingService;
import com.project.videoflow.model.VideoProcessingTask;
import com.project.videoflow.model.VideoProcessingResult;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class VideoBurnerController {

    @Autowired
    private VideoProcessingService videoProcessingService;

    /**
     * Megjeleníti a videóbeégetés oldalt
     */
    @GetMapping("/burn-video")
    public String showVideoBurnerPage(Model model, Principal principal) {
        // Aktuális felhasználó felhasználónevének hozzáadása a modellhez
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "video-burner";
    }

    /**
     * Videó feltöltése és ideiglenes tárolása
     */
    @PostMapping("/api/videos/upload")
    @ResponseBody
    public ResponseEntity<?> uploadVideo(@RequestParam("video") MultipartFile videoFile, Principal principal) {
        try {
            // Ellenőrizzük, hogy videó fájl-e
            if (!videoFile.getContentType().startsWith("video/")) {
                return ResponseEntity.badRequest().body("Csak videó fájlok tölthetők fel.");
            }

            // Ellenőrizzük a fájl méretét (pl. max 100MB)
            if (videoFile.getSize() > 100 * 1024 * 1024) {
                return ResponseEntity.badRequest().body("A fájl mérete nem haladhatja meg a 100MB-ot.");
            }

            // Ideiglenes tárolás és azonosító generálása
            String videoId = videoProcessingService.storeTemporaryVideo(videoFile, principal.getName());

            Map<String, Object> response = new HashMap<>();
            response.put("videoId", videoId);
            response.put("fileName", videoFile.getOriginalFilename());
            response.put("fileSize", videoFile.getSize());

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt a fájl feltöltése során: " + e.getMessage());
        }
    }

    /**
     * Videó feldolgozás indítása
     */
    @PostMapping("/api/videos/process")
    @ResponseBody
    public ResponseEntity<?> processVideo(@RequestBody VideoProcessingTask task, Principal principal) {
        try {
            // Ellenőrizzük, hogy a megadott videó létezik-e és a felhasználóhoz tartozik-e
            if (!videoProcessingService.isVideoOwnedByUser(task.getVideoId(), principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nincs jogosultsága a megadott videóhoz.");
            }

            // Feladat beütemezése
            String taskId = videoProcessingService.scheduleProcessingTask(task);

            Map<String, Object> response = new HashMap<>();
            response.put("taskId", taskId);
            response.put("status", "scheduled");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt a feldolgozás során: " + e.getMessage());
        }
    }

    /**
     * Feldolgozási állapot lekérdezése
     */
    @GetMapping("/api/videos/process/{taskId}/status")
    @ResponseBody
    public ResponseEntity<?> getProcessingStatus(@PathVariable String taskId, Principal principal) {
        try {
            // Ellenőrizzük, hogy a feladat a felhasználóhoz tartozik-e
            if (!videoProcessingService.isTaskOwnedByUser(taskId, principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nincs jogosultsága a megadott feladathoz.");
            }

            // Állapot lekérdezése
            int progress = videoProcessingService.getTaskProgress(taskId);
            String status = videoProcessingService.getTaskStatus(taskId);

            Map<String, Object> response = new HashMap<>();
            response.put("taskId", taskId);
            response.put("status", status);
            response.put("progress", progress);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt az állapot lekérdezése során: " + e.getMessage());
        }
    }

    /**
     * Elkészült videó elérési útjának lekérdezése
     */
    @GetMapping("/api/videos/process/{taskId}/result")
    @ResponseBody
    public ResponseEntity<?> getProcessingResult(@PathVariable String taskId, Principal principal) {
        try {
            // Ellenőrizzük, hogy a feladat a felhasználóhoz tartozik-e
            if (!videoProcessingService.isTaskOwnedByUser(taskId, principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nincs jogosultsága a megadott feladathoz.");
            }

            // Ellenőrizzük, hogy a feldolgozás befejeződött-e
            if (!videoProcessingService.isTaskCompleted(taskId)) {
                return ResponseEntity.badRequest().body("A feldolgozás még nem fejeződött be.");
            }

            // Eredmény lekérdezése
            VideoProcessingResult result = videoProcessingService.getTaskResult(taskId);

            Map<String, Object> response = new HashMap<>();
            response.put("taskId", taskId);
            response.put("videoUrl", result.getVideoUrl());
            response.put("thumbnailUrl", result.getThumbnailUrl());
            response.put("duration", result.getDuration());
            response.put("fileSize", result.getFileSize());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt az eredmény lekérdezése során: " + e.getMessage());
        }
    }

    /**
     * Videó letöltése
     */
    @GetMapping("/api/videos/{videoId}/download")
    public ResponseEntity<Resource> downloadVideo(@PathVariable String videoId, Principal principal) {
        try {
            // Ellenőrizzük, hogy a videó a felhasználóhoz tartozik-e
            if (!videoProcessingService.isVideoOwnedByUser(videoId, principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // Videó letöltése
            Resource videoResource = videoProcessingService.getVideoAsResource(videoId);
            String filename = videoProcessingService.getVideoFilename(videoId);
            String contentType = videoProcessingService.getVideoContentType(videoId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(videoResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Videó megosztása - egyedi URL generálása
     */
    @PostMapping("/api/videos/{videoId}/share")
    @ResponseBody
    public ResponseEntity<?> shareVideo(@PathVariable String videoId, Principal principal) {
        try {
            // Ellenőrizzük, hogy a videó a felhasználóhoz tartozik-e
            if (!videoProcessingService.isVideoOwnedByUser(videoId, principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nincs jogosultsága a megadott videóhoz.");
            }

            // Megosztási link generálása
            String shareToken = videoProcessingService.generateShareToken(videoId);
            String shareUrl = "/shared-video/" + shareToken;

            Map<String, Object> response = new HashMap<>();
            response.put("shareUrl", shareUrl);
            response.put("fullShareUrl", "https://videoflow.example.com" + shareUrl);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt a megosztás során: " + e.getMessage());
        }
    }

    /**
     * Megosztott videó oldal megjelenítése
     */
    @GetMapping("/shared-video/{shareToken}")
    public String showSharedVideo(@PathVariable String shareToken, Model model) {
        try {
            // Ellenőrizzük, hogy érvényes-e a megosztási token
            if (!videoProcessingService.isValidShareToken(shareToken)) {
                return "error/404";
            }

            // Videó adatok lekérése
            String videoId = videoProcessingService.getVideoIdFromShareToken(shareToken);
            VideoProcessingResult videoResult = videoProcessingService.getVideoResultById(videoId);

            // Adatok hozzáadása a modellhez
            model.addAttribute("video", videoResult);
            model.addAttribute("shareToken", shareToken);

            return "shared-video";
        } catch (Exception e) {
            return "error/500";
        }
    }

    /**
     * Ideiglenes videó törlése
     */
    @DeleteMapping("/api/videos/{videoId}")
    @ResponseBody
    public ResponseEntity<?> deleteVideo(@PathVariable String videoId, Principal principal) {
        try {
            // Ellenőrizzük, hogy a videó a felhasználóhoz tartozik-e
            if (!videoProcessingService.isVideoOwnedByUser(videoId, principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nincs jogosultsága a megadott videóhoz.");
            }

            // Videó törlése
            boolean deleted = videoProcessingService.deleteVideo(videoId);

            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Nem sikerült törölni a videót.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt a törlés során: " + e.getMessage());
        }
    }

    /**
     * Előnézeti kép generálása egy időpontban
     */
    @PostMapping("/api/videos/{videoId}/preview")
    @ResponseBody
    public ResponseEntity<?> generatePreview(@PathVariable String videoId,
                                             @RequestParam("timestamp") double timestamp,
                                             @RequestBody VideoProcessingTask previewSettings,
                                             Principal principal) {
        try {
            // Ellenőrizzük, hogy a videó a felhasználóhoz tartozik-e
            if (!videoProcessingService.isVideoOwnedByUser(videoId, principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nincs jogosultsága a megadott videóhoz.");
            }

            // Előnézeti kép generálása
            String previewImageUrl = videoProcessingService.generatePreviewImage(videoId, timestamp, previewSettings);

            Map<String, Object> response = new HashMap<>();
            response.put("previewImageUrl", previewImageUrl);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hiba történt az előnézeti kép generálása során: " + e.getMessage());
        }
    }
}