package com.project.videoflow.model;

import jakarta.persistence.*;

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
    @Column(name = "file_path")
    private String filePath;

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

}