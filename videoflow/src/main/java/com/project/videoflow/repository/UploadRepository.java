package com.project.videoflow.repository;

import com.project.videoflow.model.Upload;
import com.project.videoflow.model.UploadId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRepository extends JpaRepository<Upload, UploadId> {

    Optional<Upload> findFirstByVideoid(Long videoid);
}
