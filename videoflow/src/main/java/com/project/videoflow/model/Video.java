
package com.project.videoflow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoid;

    private String videocim;
    private String kategoria;
    private String leiras;
    private String kulcsszo;
    private int megtekintesSzam;
    private String filePath;
    private String username;
    private int likes;
    private LocalDateTime uploadDate;

    public Long getVideoid() {
        return videoid;
    }

    public void setVideoid(Long videoid) {
        this.videoid = videoid;
    }

    public String getVideocim() {
        return videocim;
    }

    public void setVideocim(String videocim) {
        this.videocim = videocim;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getKulcsszo() {
        return kulcsszo;
    }

    public void setKulcsszo(String kulcsszo) {
        this.kulcsszo = kulcsszo;
    }

    public int getMegtekintesSzam() {
        return megtekintesSzam;
    }

    public void setMegtekintesSzam(int megtekintesSzam) {
        this.megtekintesSzam = megtekintesSzam;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return this.videoid;
    }
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void incrementViews() {
        this.megtekintesSzam++;
    }
    public void incrementLikes() {
        this.likes++;
    }
    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}

