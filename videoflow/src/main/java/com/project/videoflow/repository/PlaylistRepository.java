package com.project.videoflow.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.videoflow.model.Playlist;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
    Playlist findByPlaylistnev(String playlistnev);

    boolean existsByPlaylistnev(String playlistnev);

    Playlist findByPlaylistid(Long playlistid);
    
    boolean existsByPlaylistid(Long playlistid);
}
