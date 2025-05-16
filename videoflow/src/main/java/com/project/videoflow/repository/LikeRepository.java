package com.project.videoflow.repository;

import com.project.videoflow.model.Kedveli;
import com.project.videoflow.model.KedveliId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Kedveli, KedveliId> {

    // Egy felhasználó összes kedvelt videója
    List<Kedveli> findByEmail(String email);

    // Egy videó kedveléseinek lekérdezése
    List<Kedveli> findByVideoid(Long videoid);

    // Legtöbb kedvelést adó felhasználó
    @Query(value = """
        SELECT email
        FROM KEDVELI
        GROUP BY email
        ORDER BY COUNT(*) DESC
        FETCH FIRST 1 ROWS ONLY
        """, nativeQuery = true)
    String findTopLiker();
}
