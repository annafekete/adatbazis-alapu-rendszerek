// service - Üzleti logika

package com.project.videoflow.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.videoflow.dto.UserDto;
import com.project.videoflow.model.User;
import com.project.videoflow.repository.RoleRepository;
import com.project.videoflow.repository.UserRepository;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(UserDto userDto, Model model) {
        boolean hasErrors = false;
    
        if (userRepository.existsByEmail(userDto.getEmail())) {
            hasErrors = true;
        }
    
        if (userRepository.existsByFelhasznalonev(userDto.getFelhasznalonev())) {
            hasErrors = true;
        }
    
        if (!hasErrors) {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setFelhasznalonev(userDto.getFelhasznalonev());
            user.setJelszo(passwordEncoder.encode(userDto.getJelszo()));
            user.setSzerepkor(userDto.getSzerepkor());    
            userRepository.save(user);
        }
    }
}