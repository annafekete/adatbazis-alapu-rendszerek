package com.project.videoflow.repository;

import com.project.videoflow.model.Nez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewRepository extends JpaRepository<Nez, Long> {

    

    //legaktívabb videómegtekintő
    @Query(value = """
        SELECT u.felhasznalonev
        FROM nez n
        JOIN felhasznalo u ON n.email = u.email
        GROUP BY u.felhasznalonev
        ORDER BY COUNT(*) DESC
        FETCH FIRST 1 ROWS ONLY
        """, nativeQuery = true)
    String findTopViewerUsername();
}
