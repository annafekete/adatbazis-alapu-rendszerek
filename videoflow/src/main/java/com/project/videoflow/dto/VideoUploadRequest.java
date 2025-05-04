package com.project.videoflow.dto;

public class VideoUploadRequest {
    private String videocim;
    private String kategoria;
    private String kulcsszo;
    private String leiras;
    private String email;

// --- Constructors --- public VideoUploadRequest() {} public VideoUploadRequest(String videocim, String kategoria, String kulcsszo, String leiras, String email) { this.videocim = videocim; this.kategoria = kategoria; this.kulcsszo = kulcsszo; this.leiras = leiras; this.email = email; } // --- Getters and Setters --- public String getVideocim() { return videocim; } public void setVideocim(String videocim) { this.videocim = videocim; } public String getKategoria() { return kategoria; } public void setKategoria(String kategoria) { this.kategoria = kategoria; } public String getKulcsszo() { return kulcsszo; } public void setKulcsszo(String kulcsszo) { this.kulcsszo = kulcsszo; } public String getLeiras() { return leiras; } public void setLeiras(String leiras) { this.leiras = leiras; } public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }

}