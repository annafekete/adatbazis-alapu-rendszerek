package com.project.videoflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.io.Serializable;

@Entity
@Table(name = "FELTOLTI")
@IdClass(FeltoltiId.class)
public class Feltolti {

    @Id
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "videoid")
    private Long videoid;

    @ManyToOne
    @JoinColumn(name = "videoid", insertable = false, updatable = false)
    private Video video;

    @Column(name = "feltoltes_ideje")
    private LocalDateTime feltoltesIdeje;

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

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public LocalDateTime getFeltoltesIdeje() {
        return feltoltesIdeje;
    }

    public void setFeltoltesIdeje(LocalDateTime feltoltesIdeje) {
        this.feltoltesIdeje = feltoltesIdeje;
    }
}