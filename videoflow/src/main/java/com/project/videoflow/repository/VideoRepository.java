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

    @Query("SELECT v FROM Video v LEFT JOIN Nez n ON v.videoid = n.videoid WHERE v.kategoria = :kategoria GROUP BY v ORDER BY COUNT(n) DESC, v.videocim ASC")
    List<Video> findTopByCategoryOrderByViews(@Param("kategoria") String kategoria);

    @Query("SELECT v FROM Video v LEFT JOIN Nez n ON v.videoid = n.videoid GROUP BY v ORDER BY COUNT(n) DESC, v.videocim ASC")
    List<Video> findAllOrderByViews();

    @Query("SELECT v FROM Video v JOIN Kedveli k ON v.videoid = k.videoid WHERE v.kategoria = :kategoria GROUP BY v ORDER BY COUNT(k) DESC, v.videocim ASC")
    List<Video> findTopByCategoryOrderByLikes(@Param("kategoria") String kategoria);

    @Query("SELECT v FROM Video v LEFT JOIN Kedveli k ON v.videoid = k.videoid GROUP BY v ORDER BY COUNT(k) DESC, v.videocim ASC")
    List<Video> findAllOrderByLikes();

    @Query("SELECT v FROM Video v JOIN Feltolti f ON v.videoid = f.videoid WHERE v.kategoria = :kategoria ORDER BY f.feltoltesIdeje DESC")
    List<Video> findTopByCategoryOrderByDate(@Param("kategoria") String kategoria);

    @Query("SELECT v FROM Video v JOIN Feltolti f ON v.videoid = f.videoid ORDER BY f.feltoltesIdeje DESC")
    List<Video> findAllOrderByDate();

    Video findByVideoid(Long videoid);

    @Query("SELECT v FROM Video v WHERE v.kategoria = :kategoria AND v.videoid <> :excludeId")
    List<Video> findSimilarVideosByCategory(@Param("kategoria") String kategoria, @Param("excludeId") Long excludeId);

    @Query("SELECT v FROM Video v WHERE v.kulcsszo = :kulcsszo AND v.videoid <> :excludeId")
    List<Video> findSimilarVideosByKeyword(@Param("kulcsszo") String kulcsszo, @Param("excludeId") Long excludeId);

    List<Video> findByKategoria(String kategoria);

    List<Video> findAllByVideoid(Long videoid);

}
