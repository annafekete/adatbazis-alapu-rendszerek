package com.project.videoflow.service;

import com.project.videoflow.dto.LoginRequest;
import com.project.videoflow.model.User;
import com.project.videoflow.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public AuthServiceImpl(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User login(LoginRequest request) {
        System.out.println("AuthServiceImpl - keresett felhasznalonev: " + request.getFelhasznalonev());
        User user = userRepository.findByFelhasznalonev(request.getFelhasznalonev());

        if (user == null) {
            System.out.println("Nincs ilyen felhasználó: " + request.getFelhasznalonev());
            throw new RuntimeException("Nincs ilyen felhasználó!");
        }

        if (!passwordEncoder.matches(request.getJelszo(), user.getJelszo())) {
            System.out.println("Hibás jelszó!");
            throw new RuntimeException("Hibás jelszó!");
        }

        System.out.println("Sikeres azonosítás!");

        // ✅ Trigger kiváltása az email alapján
        jdbcTemplate.update("UPDATE FELHASZNALO SET LAST_LOGIN = SYSDATE WHERE EMAIL = ?", user.getEmail());
        return user;
    }
}

