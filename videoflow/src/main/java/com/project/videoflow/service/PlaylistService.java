package com.project.videoflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.videoflow.dto.PlaylistRequest;
import com.project.videoflow.model.Playlist;
import com.project.videoflow.repository.PlaylistRepository;

@Service
public class PlaylistService {
    
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }
    public Playlist createPlaylist(PlaylistRequest playlistRequest) {
        Playlist playlist = new Playlist();
        playlist.setPlaylistnev(playlistRequest.getPlaylistnev());
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }
    
    public Optional<Playlist> getPlaylistById(Long id) {
        return playlistRepository.findById(id);
    }
    
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
    
    public Playlist updatePlaylist(Long id, PlaylistRequest playlistRequest) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            Playlist playlist = optionalPlaylist.get();
            playlist.setPlaylistnev(playlistRequest.getPlaylistnev());
            return playlistRepository.save(playlist);
        }
        return null;
    }
}
