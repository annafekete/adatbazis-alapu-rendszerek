package com.project.videoflow.model;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Egy videó feldolgozás eredményét reprezentáló osztály
 */
public class VideoProcessingResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String videoId;
    private String originalFilename;
    private String processedFilename;
    private String videoUrl;
    private String thumbnailUrl;
    private long fileSize;
    private int duration; // másodpercben
    private String format;
    private LocalDateTime processedAt;
    private String userId;

    // Alapértelmezett konstruktor
    public VideoProcessingResult() {
    }

    // Paraméterezett konstruktor a gyakori mezőkhöz
    public VideoProcessingResult(String videoId, String videoUrl, String thumbnailUrl, int duration, long fileSize) {
        this.videoId = videoId;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.duration = duration;
        this.fileSize = fileSize;
    }

    // Getterek és setterek

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getProcessedFilename() {
        return processedFilename;
    }

    public void setProcessedFilename(String processedFilename) {
        this.processedFilename = processedFilename;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VideoProcessingResult{" +
                "videoId='" + videoId + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", fileSize=" + fileSize +
                ", duration=" + duration +
                ", format='" + format + '\'' +
                ", processedAt=" + processedAt +
                '}';
    }
}

