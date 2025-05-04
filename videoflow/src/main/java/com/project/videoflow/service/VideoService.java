package com.project.videoflow.service;

import com.project.videoflow.model.Upload;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.UploadRepository;
import com.project.videoflow.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Value;
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

    private final VideoRepository videoRepository;
    private final UploadRepository feltoltiRepository;
    @Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;
    public VideoService(VideoRepository videoRepository, UploadRepository feltoltiRepository) {
        this.videoRepository = videoRepository;
        this.feltoltiRepository = feltoltiRepository; }

    @Transactional public void uploadVideo(MultipartFile videoFile, String videocim, String kategoria, String kulcsszo, String leiras, String email) {
        try { // Létrehozzuk a célkönyvtárat, ha nem létezik
            File directory = new File(uploadDir);
            if (!directory.exists()) { directory.mkdirs(); } // Egyedi fájlnév generálása, hogy elkerüljük az ütközéseket
            String uniqueFileName = System.currentTimeMillis() + "_" + videoFile.getOriginalFilename(); // Fájl útvonal beállítása
            String filePath = uploadDir + File.separator + uniqueFileName; // Fájl mentése a fájlrendszerre
            Path targetLocation = Paths.get(filePath); Files.copy(videoFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING); // Video entitás létrehozása és mentése
            Video video = new Video(); video.setVideocim(videocim); video.setKategoria(kategoria); video.setKulcsszo(kulcsszo); video.setLeiras(leiras); video.setFilePath(filePath); // Most már beállítjuk a filePath-t is
            video.setMegtekintesSzam(0); Video savedVideo = videoRepository.save(video); // Feltöltési bejegyzés létrehozása
            Upload feltolti = new Upload(); feltolti.setVideoid(savedVideo.getVideoid()); feltolti.setEmail(email); feltoltiRepository.save(feltolti); }
        catch (IOException ex) {
            throw new RuntimeException("Hiba történt a fájl feltöltése során", ex); } } // Megtartjuk a régi metódust is a visszafelé kompatibilitás érdekében, ha szükséges

    @Transactional public void uploadVideo(String videocim, String kategoria, String kulcsszo, String leiras, String email) {
        Video video = new Video();
        video.setVideocim(videocim);
        video.setKategoria(kategoria);
        video.setKulcsszo(kulcsszo);
        video.setLeiras(leiras);
        video.setMegtekintesSzam(0);
        Video savedVideo = videoRepository.save(video);
        Upload feltolti = new Upload();
        feltolti.setVideoid(savedVideo.getVideoid());
        feltolti.setEmail(email); feltoltiRepository.save(feltolti); } }