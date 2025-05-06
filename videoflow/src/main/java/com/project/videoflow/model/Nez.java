package com.project.videoflow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "NEZ")
public class Nez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewid")
    private Long viewid;

    @Column(name = "email")
    private String email;

    @Column(name = "videoid")
    private Long videoid;

    @ManyToOne
    @JoinColumn(name = "videoid", insertable = false, updatable = false)
    private Video video;

    public Long getViewid() {
        return viewid;
    }

    public void setViewid(Long viewid) {
        this.viewid = viewid;
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

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}