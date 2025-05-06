package com.project.videoflow.service;

import com.project.videoflow.model.Upload;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.UploadRepository;
import com.project.videoflow.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class VideoService {

    private final JdbcTemplate jdbcTemplate; //eljaras meghivasahoz
    private final VideoRepository videoRepository;
    private final UploadRepository feltoltiRepository;

    @Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;

    public VideoService(VideoRepository videoRepository, UploadRepository feltoltiRepository, JdbcTemplate jdbcTemplate) {
        this.videoRepository = videoRepository;
        this.feltoltiRepository = feltoltiRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void uploadVideo(MultipartFile videoFile, String videocim, String kategoria, String kulcsszo, String leiras, String email) {
        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String uniqueFileName = System.currentTimeMillis() + "_" + videoFile.getOriginalFilename();
            String filePath = uploadDir + File.separator + uniqueFileName;
            Path targetLocation = Paths.get(filePath);
            Files.copy(videoFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            jdbcTemplate.update("CALL feltoltes_mentes(?, ?, ?, ?, ?, ?)",
                    videocim, kategoria, kulcsszo, leiras, filePath, email);

        } catch (IOException e) {
            throw new RuntimeException("Hiba történt a fájl feltöltése során", e);
        }
    }

}