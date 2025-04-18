package com.project.videoflow.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.videoflow.model.VideoProcessingTask;
import com.project.videoflow.model.VideoProcessingResult;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Videó feldolgozást kezelő szolgáltatás
 */
@Service
public class VideoProcessingService {

    @Value("${videoflow.upload.dir:${user.home}/videoflow/uploads}")
    private String uploadDir;

    @Value("${videoflow.processed.dir:${user.home}/videoflow/processed}")
    private String processedDir;

    @Value("${videoflow.temp.dir:${user.home}/videoflow/temp}")
    private String tempDir;

    @Autowired
    private ResourceLoader resourceLoader;

    // Nyilvántartjuk a felhasználókhoz tartozó videókat
    private Map<String, String> videoOwnership = new ConcurrentHashMap<>();

    // Nyilvántartjuk a feldolgozási feladatokat és állapotukat
    private Map<String, Integer> taskProgress = new ConcurrentHashMap<>();
    private Map<String, String> taskStatus = new ConcurrentHashMap<>();
    private Map<String, String> taskOwnership = new ConcurrentHashMap<>();
    private Map<String, VideoProcessingResult> taskResults = new ConcurrentHashMap<>();

    // Megosztási tokenek nyilvántartása
    private Map<String, String> shareTokens = new ConcurrentHashMap<>();

    // Párhuzamos feldolgozáshoz
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    /**
     * Ideiglenes videó tárolása
     */
    public String storeTemporaryVideo(MultipartFile file, String username) throws IOException {
        // Könyvtárak létrehozása, ha még nem léteznek
        createDirectories();

        // Egyedi azonosító generálása
        String videoId = UUID.randomUUID().toString();

        // Fájl mentése
        Path uploadPath = Paths.get(uploadDir);
        Path filePath = uploadPath.resolve(videoId + getFileExtension(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Tulajdonjog rögzítése
        videoOwnership.put(videoId, username);

        return videoId;
    }

    /**
     * Ellenőrizzük, hogy a videó a megadott felhasználóhoz tartozik-e
     */
    public boolean isVideoOwnedByUser(String videoId, String username) {
        String owner = videoOwnership.get(videoId);
        return owner != null && owner.equals(username);
    }

    /**
     * Videó feldolgozási feladat beütemezése
     */
    public String scheduleProcessingTask(VideoProcessingTask task) {
        String taskId = UUID.randomUUID().toString();

        // Feladat tulajdonosa = videó tulajdonosa
        String owner = videoOwnership.get(task.getVideoId());
        taskOwnership.put(taskId, owner);

        // Kezdeti állapot beállítása
        taskStatus.put(taskId, "pending");
        taskProgress.put(taskId, 0);

        // Beütemezzük a feldolgozást
        executorService.submit(() -> processVideoTask(taskId, task));

        return taskId;
    }

    /**
     * Ellenőrizzük, hogy a feladat a megadott felhasználóhoz tartozik-e
     */
    public boolean isTaskOwnedByUser(String taskId, String username) {
        String owner = taskOwnership.get(taskId);
        return owner != null && owner.equals(username);
    }

    /**
     * Feladat haladásának lekérdezése
     */
    public int getTaskProgress(String taskId) {
        Integer progress = taskProgress.get(taskId);
        return progress != null ? progress : 0;
    }

    /**
     * Feladat állapotának lekérdezése
     */
    public String getTaskStatus(String taskId) {
        String status = taskStatus.get(taskId);
        return status != null ? status : "unknown";
    }

    /**
     * Ellenőrizzük, hogy a feladat befejeződött-e
     */
    public boolean isTaskCompleted(String taskId) {
        String status = taskStatus.get(taskId);
        return "completed".equals(status);
    }

    /**
     * Feladat eredményének lekérdezése
     */
    public VideoProcessingResult getTaskResult(String taskId) {
        return taskResults.get(taskId);
    }

    /**
     * Videó fájl lekérése Resource objektumként
     */
    public Resource getVideoAsResource(String videoId) throws MalformedURLException {
        Path processedPath = Paths.get(processedDir);

        // Először a feldolgozott videók között keressük
        Path videoPath = processedPath.resolve(videoId + ".mp4");
        if (!Files.exists(videoPath)) {
            // Ha nincs feldolgozott, akkor az eredeti feltöltést adjuk vissza
            Path uploadPath = Paths.get(uploadDir);

            // Keressük a megfelelő fájlt a kiterjesztéssel
            File uploadDir = uploadPath.toFile();
            File[] files = uploadDir.listFiles((dir, name) -> name.startsWith(videoId + "."));

            if (files != null && files.length > 0) {
                videoPath = files[0].toPath();
            } else {
                throw new RuntimeException("Video file not found");
            }
        }

        return new UrlResource(videoPath.toUri());
    }

    /**
     * Videó fájlnév lekérdezése
     */
    public String getVideoFilename(String videoId) {
        // Itt implementálhatnánk, hogy az adatbázisból lekérjük az eredeti fájlnevet
        // Egyszerűség kedvéért most csak az ID-t adjuk vissza
        return videoId + ".mp4";
    }

    /**
     * Videó MIME típus lekérdezése
     */
    public String getVideoContentType(String videoId) {
        // Itt implementálhatnánk, hogy formátum alapján határozzuk meg
        // Egyszerűség kedvéért most csak MP4-et feltételezünk
        return "video/mp4";
    }

    /**
     * Megosztási token generálása
     */
    public String generateShareToken(String videoId) {
        String shareToken = UUID.randomUUID().toString();
        shareTokens.put(shareToken, videoId);
        return shareToken;
    }

    /**
     * Megosztási token érvényességének ellenőrzése
     */
    public boolean isValidShareToken(String shareToken) {
        return shareTokens.containsKey(shareToken);
    }

    /**
     * VideoId lekérdezése megosztási token alapján
     */
    public String getVideoIdFromShareToken(String shareToken) {
        return shareTokens.get(shareToken);
    }

    /**
     * Videó eredmény lekérdezése videóId alapján
     */
    public VideoProcessingResult getVideoResultById(String videoId) {
        // Itt kereshetnénk adatbázisban, most egyszerűsítésképp egy példát adunk vissza
        VideoProcessingResult result = new VideoProcessingResult();
        result.setVideoId(videoId);
        result.setVideoUrl("/api/videos/" + videoId + "/stream");
        result.setThumbnailUrl("/api/videos/" + videoId + "/thumbnail");
        result.setDuration(120); // 2 perc
        result.setFileSize(1024 * 1024 * 5); // 5MB
        result.setFormat("mp4");
        result.setProcessedAt(LocalDateTime.now());
        return result;
    }

    /**
     * Videó törlése
     */
    public boolean deleteVideo(String videoId) {
        try {
            // Töröljük a feltöltött fájlt
            Path uploadPath = Paths.get(uploadDir);
            File uploadDir = uploadPath.toFile();
            File[] uploadedFiles = uploadDir.listFiles((dir, name) -> name.startsWith(videoId + "."));

            if (uploadedFiles != null) {
                for (File file : uploadedFiles) {
                    file.delete();
                }
            }

            // Töröljük a feldolgozott fájlt
            Path processedPath = Paths.get(processedDir);
            File processedDir = processedPath.toFile();
            File[] processedFiles = processedDir.listFiles((dir, name) -> name.startsWith(videoId + "."));

            if (processedFiles != null) {
                for (File file : processedFiles) {
                    file.delete();
                }
            }

            // Töröljük a nyilvántartásból
            videoOwnership.remove(videoId);

            // Megosztási tokenek törlése ehhez a videóhoz
            shareTokens.entrySet().removeIf(entry -> videoId.equals(entry.getValue()));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Előnézeti kép generálása
     */
    public String generatePreviewImage(String videoId, double timestamp, VideoProcessingTask settings) {
        // Ebben a demo implementációban csak egy példa URL-t adunk vissza
        // Valós implementációban itt használnánk például az FFmpeg-et
        return "/api/videos/" + videoId + "/preview?t=" + timestamp;
    }

    /**
     * Segédfüggvény a videó feldolgozásához
     */
    private void processVideoTask(String taskId, VideoProcessingTask task) {
        try {
            taskStatus.put(taskId, "processing");

            // Itt implementálnánk a tényleges videó feldolgozást, például FFmpeg használatával
            // Most csak szimulálunk egy feldolgozási folyamatot

            String videoId = task.getVideoId();

            // Szimuláljuk a haladást
            for (int i = 0; i <= 100; i += 5) {
                taskProgress.put(taskId, i);
                Thread.sleep(300); // Szimulálunk némi feldolgozási időt
            }

            // Létrehozzuk az eredményt
            VideoProcessingResult result = new VideoProcessingResult();
            result.setVideoId(videoId);
            result.setVideoUrl("/api/videos/" + videoId + "/stream");
            result.setThumbnailUrl("/api/videos/" + videoId + "/thumbnail");
            result.setDuration(120); // Példa: 2 perc
            result.setFileSize(1024 * 1024 * 10); // Példa: 10MB
            result.setFormat(task.getOutputFormat());
            result.setProcessedAt(LocalDateTime.now());
            result.setUserId(videoOwnership.get(videoId));

            // Mentjük az eredményt
            taskResults.put(taskId, result);
            taskStatus.put(taskId, "completed");

        } catch (Exception e) {
            taskStatus.put(taskId, "failed");
            e.printStackTrace();
        }
    }

    /**
     * Létrehozza a szükséges könyvtárstruktúrát
     */
    private void createDirectories() throws IOException {
        Files.createDirectories(Paths.get(uploadDir));
        Files.createDirectories(Paths.get(processedDir));
        Files.createDirectories(Paths.get(tempDir));
    }

    /**
     * Fájl kiterjesztés kinyerése
     */
    private String getFileExtension(String filename) {
        if (filename == null) {
            return "";
        }
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex);
    }
}