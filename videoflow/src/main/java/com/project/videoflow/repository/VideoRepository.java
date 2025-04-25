/*

package com.project.videoflow.repository;

import com.project.videoflow.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByCategory(String category);

    List<Video> findByUsername(String username);

    @Query("SELECT v FROM Video v WHERE v.videocim LIKE %?1% OR v.leiras LIKE %?1% OR v.kulcsszo LIKE %?1%")
    Page<Video> search(String keyword, Pageable pageable);

    @Query("SELECT v FROM Video v ORDER BY v.megtekintesSzam DESC")
    List<Video> findTopVideos(Pageable pageable);

    @Query("SELECT v FROM Video v ORDER BY v.uploadDate DESC")
    List<Video> findRecentVideos(Pageable pageable);
}


*/
