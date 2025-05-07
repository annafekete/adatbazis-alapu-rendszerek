package com.project.videoflow.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.videoflow.model.CreatePL;
import com.project.videoflow.model.CreatePLId;

import org.springframework.stereotype.Repository;

@Repository
public interface CreatePLRepository extends JpaRepository<CreatePL, CreatePLId> {
    CreatePL findByEmailAndPlaylistid(String email, Long playlistid);
    boolean existsByEmailAndPlaylistid(String email, Long playlistid);
    
    List<CreatePL> findByEmail(String email);
}
