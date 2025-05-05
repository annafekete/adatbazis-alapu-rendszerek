package com.project.videoflow.repository;
import com.project.videoflow.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    //lekérdezés az aktuális videó feltöltőjének többi videojához
    @Query("SELECT v FROM Video v JOIN Upload u ON v.videoid = u.videoid WHERE u.email = :email AND v.videoid <> :excludeId")
    List<Video> findOtherVideosByUploader(@Param("email") String email, @Param("excludeId") Long excludeId);
}
