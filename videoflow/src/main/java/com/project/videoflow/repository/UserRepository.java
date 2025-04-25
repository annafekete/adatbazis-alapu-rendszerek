// repository - Spring Data JPA interfészek

package com.project.videoflow.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.videoflow.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> { //elsodleges kulcs tipusa String (email)
    User findByEmail(String email);
    boolean existsByEmail(String email);

    User findByFelhasznalonev(String felhasznalonev);
    boolean existsByFelhasznalonev(String felhasznalonev);
}