package com.project.videoflow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "HOZZAAD")
public class AddtoPL {
    @Id
    private Long videoid;

    @Id
    private Long playlistid;

    public Long getVideoid() {
        return videoid;
    }

    public void setVideoid(Long videoid) {
        this.videoid = videoid;
    }

    public Long getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(Long playlistid) {
        this.playlistid = playlistid;
    }
}
