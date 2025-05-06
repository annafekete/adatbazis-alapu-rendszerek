package com.project.videoflow.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "KEDVELI")
@IdClass(KedveliId.class)
public class Kedveli {

    @Id
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "videoid")
    private Long videoid;

    @ManyToOne
    @JoinColumn(name = "videoid", insertable = false, updatable = false)
    private Video video;


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getVideoid() { return videoid; }
    public void setVideoid(Long videoid) { this.videoid = videoid; }

    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }
}
