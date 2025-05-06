package com.project.videoflow.repository;
import com.project.videoflow.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT v FROM Video v JOIN Upload u ON v.videoid = u.videoid WHERE u.email = :email AND v.videoid <> :excludeId")
    List<Video> findOtherVideosByUploader(@Param("email") String email, @Param("excludeId") Long excludeId);

    @Query("SELECT v FROM Video v WHERE LOWER(v.videocim) LIKE LOWER(CONCAT('%', :videocim, '%'))")
    List<Video> searchByVideocim(@Param("videocim") String videocim);

    @Query("SELECT v FROM Video v WHERE LOWER(v.kategoria) = LOWER(:kategoria)")
    List<Video> findByKategoriaIgnoreCase(@Param("kategoria") String kategoria);

    @Query("SELECT v FROM Video v WHERE LOWER(v.kategoria) = LOWER(:kategoria) ORDER BY v.megtekintesSzam DESC")
    List<Video> findTopByCategoryOrderByViews(@Param("kategoria") String kategoria);

    @Query("SELECT v FROM Video v JOIN Feltolti f ON v.videoid = f.videoid WHERE LOWER(v.kategoria) = LOWER(:kategoria) ORDER BY f.feltoltesIdeje DESC")
    List<Video> findTopByCategoryOrderByDate(@Param("kategoria") String kategoria);

    @Query(value = "SELECT v.* FROM VIDEO v LEFT JOIN (SELECT videoid, COUNT(*) as like_count FROM KEDVELI GROUP BY videoid) k ON v.videoid = k.videoid WHERE LOWER(v.kategoria) = LOWER(:kategoria) ORDER BY NVL(k.like_count, 0) DESC", nativeQuery = true)
    List<Video> findTopByCategoryOrderByLikes(@Param("kategoria") String kategoria);

    @Query("SELECT v FROM Video v ORDER BY v.megtekintesSzam DESC")
    List<Video> findAllOrderByViews();

    @Query("SELECT v FROM Video v JOIN Feltolti f ON v.videoid = f.videoid ORDER BY f.feltoltesIdeje DESC")
    List<Video> findAllOrderByDate();

    @Query(value = "SELECT v.* FROM VIDEO v LEFT JOIN (SELECT videoid, COUNT(*) as like_count FROM KEDVELI GROUP BY videoid) k ON v.videoid = k.videoid ORDER BY NVL(k.like_count, 0) DESC", nativeQuery = true)
    List<Video> findAllOrderByLikes();
}