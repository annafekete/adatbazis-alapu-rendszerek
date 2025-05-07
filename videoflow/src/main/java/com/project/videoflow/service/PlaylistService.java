package com.project.videoflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.videoflow.dto.PlaylistRequest;
import com.project.videoflow.model.AddtoPL;
import com.project.videoflow.model.CreatePL;
import com.project.videoflow.model.Playlist;
import com.project.videoflow.model.Video;
import com.project.videoflow.repository.AddtoPLRepository;
import com.project.videoflow.repository.CreatePLRepository;
import com.project.videoflow.repository.PlaylistRepository;
import com.project.videoflow.repository.VideoRepository;

@Service
public class PlaylistService {
    
    private final PlaylistRepository playlistRepository;
    private final CreatePLRepository createPLRepository;
    private final VideoRepository videoRepository;
    private final AddtoPLRepository addtoPLRepository;

    public PlaylistService(PlaylistRepository playlistRepository, CreatePLRepository createPLRepository, VideoRepository videoRepository, AddtoPLRepository addtoPLRepository) {
        this.addtoPLRepository = addtoPLRepository;
        this.videoRepository = videoRepository;
        this.createPLRepository = createPLRepository;
        this.playlistRepository = playlistRepository;
    }
    public void createPlaylist(String playlistnev, String email) {
        Playlist playlist = new Playlist();
        if(playlistnev == null) {
            throw new IllegalArgumentException("A lejatszasi lista neve nem lehet ures");
        }
        playlist.setPlaylistnev(playlistnev);
        Playlist savedPlaylist = playlistRepository.save(playlist);
        System.out.println("Saved playlist name: " + savedPlaylist.getPlaylistnev());

        CreatePL createPL = new CreatePL();
        createPL.setEmail(email);
        createPL.setPlaylistid(savedPlaylist.getPlaylistid());
        createPLRepository.save(createPL);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }
    
    public List<Playlist> getPlaylistById(List<Long> ids) {
        return playlistRepository.findAllById(ids);
    }
    
    public void deletePlaylist(Long id) {
        try {
            playlistRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Failed to delete playlist: " + e.getMessage());
            throw e;
        }
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

    public void addVideoToPlaylist(Long playlistId, Long videoId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found"));
        AddtoPL addtoPL = new AddtoPL();
        addtoPL.setPlaylistid(playlistId);
        addtoPL.setVideoid(videoId);
        addtoPLRepository.save(addtoPL);
        
    }
}
