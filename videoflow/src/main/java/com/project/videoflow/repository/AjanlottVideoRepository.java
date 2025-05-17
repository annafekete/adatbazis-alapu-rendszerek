package com.project.videoflow.repository;

import com.project.videoflow.model.AjanlottVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AjanlottVideoRepository extends JpaRepository<AjanlottVideo, Long> {

    @Query("SELECT v.videoid FROM AjanlottVideo v WHERE v.email = :email")
    List<Long> findRecommendedVideoIdsByEmail(String email);
}
