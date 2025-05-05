package com.project.videoflow.dto;

public class PlaylistRequest {
    private String playlistnev;

    public PlaylistRequest() {}

    public PlaylistRequest(String playlistnev) {
        this.playlistnev = playlistnev;
    }

    public String getPlaylistnev() {
        return playlistnev;
    }

    public void setPlaylistnev(String playlistnev) {
        this.playlistnev = playlistnev;
    }
}
