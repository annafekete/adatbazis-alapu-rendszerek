package com.project.videoflow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LETREHOZ")
@IdClass(CreatePLId.class)
public class CreatePL {
    @Id
    private String email;

    @Id
    private Long playlistid;


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPlaylistid() {
        return this.playlistid;
    }

    public void setPlaylistid(Long playlistid) {
        this.playlistid = playlistid;
    }

}
