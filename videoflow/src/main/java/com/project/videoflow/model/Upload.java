package com.project.videoflow.model;

import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "FELTOLTI")
@IdClass(UploadId.class)
public class Upload {
    @Id
    private Long videoid;

    @Id private String email;

    @Column(name = "feltoltes_ideje")

    @Temporal(TemporalType.TIMESTAMP) private Date feltoltesIdeje = new Date();

    public Long getVideoid() { return videoid; }
    public void setVideoid(Long videoid) { this.videoid = videoid; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getFeltoltesIdeje() { return feltoltesIdeje; }
    public void setFeltoltesIdeje(Date feltoltesIdeje) { this.feltoltesIdeje = feltoltesIdeje; }

}