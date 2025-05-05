package com.project.videoflow.repository;

import com.project.videoflow.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM HOZZASZOLAS WHERE VIDEOID = :videoId ORDER BY COMMENTID DESC", nativeQuery = true)
    List<Comment> findCommentsByVideoId(@Param("videoId") Long videoId);
}
