package com.project.videoflow.service;

import com.project.videoflow.dto.LoginRequest;
import com.project.videoflow.model.User;
import com.project.videoflow.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User login(LoginRequest request) {
        System.out.println("AuthServiceImpl - keresett felhasznalonev: " + request.getFelhasznalonev());
        User user = userRepository.findByFelhasznalonev(request.getFelhasznalonev());

        if (user == null) {
            System.out.println("Nincs ilyen felhasználó: " + request.getFelhasznalonev());
            throw new RuntimeException("Hibás felhasználónév vagy jelszó.");
        }

        if (!passwordEncoder.matches(request.getJelszo(), user.getJelszo())) {
            System.out.println("Hibás jelszó!");
            throw new RuntimeException("Hibás felhasználónév vagy jelszó.");
        }

        System.out.println("Sikeres azonosítás!");
        return user;
    }

}
