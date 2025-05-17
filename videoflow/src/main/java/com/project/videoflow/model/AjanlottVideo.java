package com.project.videoflow.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AJANLOTT_VIDEO")
public class AjanlottVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "VIDEOID", nullable = false)
    private Long videoid;

    @Column(name = "HOZZAADVA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hozzaadva;

    public AjanlottVideo() {}

    public AjanlottVideo(String email, Long videoid) {
        this.email = email;
        this.videoid = videoid;
        this.hozzaadva = new Date();
    }

    // Getterek és setterek

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getVideoid() {
        return videoid;
    }

    public void setVideoid(Long videoid) {
        this.videoid = videoid;
    }

    public Date getHozzaadva() {
        return hozzaadva;
    }

    public void setHozzaadva(Date hozzaadva) {
        this.hozzaadva = hozzaadva;
    }
}
