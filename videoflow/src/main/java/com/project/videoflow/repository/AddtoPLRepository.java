package com.project.videoflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.videoflow.model.AddtoPL;
import com.project.videoflow.model.AddtoPLId;

public interface AddtoPLRepository extends JpaRepository<AddtoPL, AddtoPLId> {
    List<AddtoPL> findByVideoid(Long videoid);
    List<AddtoPL> findByPlaylistid(Long playlistid);
    AddtoPL findByVideoidAndPlaylistid(Long videoid, Long playlistid);
    void deleteByVideoidAndPlaylistid(Long videoid, Long playlistid);
    
}
