package com.project.videoflow.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "LEJATSZASI_LISTA")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistid;

    private String playlistnev;

    @ManyToMany
    @JoinTable(name = "HOZZAAD", joinColumns = @JoinColumn(name = "playlistid"), inverseJoinColumns = @JoinColumn(name = "videoid"))
    private List<Video> videos = new ArrayList<>();

    public Long getPlaylistid() {
        return this.playlistid;
    }

    public void setPlaylistid(Long playlistid) {
        this.playlistid = playlistid;
    }

    public String getPlaylistnev() {
        return this.playlistnev;
    }

    public void setPlaylistnev(String playlistnev) {
        this.playlistnev = playlistnev;
    }

}
