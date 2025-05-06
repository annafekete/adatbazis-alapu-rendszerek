package com.project.videoflow.repository;

import com.project.videoflow.model.Upload;
import com.project.videoflow.model.UploadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UploadRepository extends JpaRepository<Upload, UploadId> {

    Optional<Upload> findFirstByVideoid(Long videoid);

    @Query(value = """
    SELECT f.email 
    FROM FELTOLTI f 
    GROUP BY f.email 
    ORDER BY COUNT(*) DESC 
    FETCH FIRST 1 ROWS ONLY
    """, nativeQuery = true)
    String findTopUploaderEmail();

}
