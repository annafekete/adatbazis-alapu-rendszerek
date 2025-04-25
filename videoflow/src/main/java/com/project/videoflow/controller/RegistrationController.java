package com.project.videoflow.controller;
import com.project.videoflow.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.videoflow.dto.UserDto;
import com.project.videoflow.model.Role;
import com.project.videoflow.repository.UserRepository;
import com.project.videoflow.service.RegisterService;

@Controller
public class RegistrationController {

    @Autowired
    private RegisterService registerService;
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    RegistrationController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/reg")
    public String registerUser(@ModelAttribute("user") UserDto userDto, @RequestParam(value = "contentCreator", required = false) String contentCreator, Model model) {
        boolean emailExists = userRepository.existsByEmail(userDto.getEmail());
        boolean usernameExists = userRepository.existsByFelhasznalonev(userDto.getFelhasznalonev());

        if(emailExists) {
            return "redirect:/registration?error=email";
        }

        if (usernameExists) {
            return "redirect:/registration?error=username";
        }

        
        /*if(contentCreator != null) {
            Role role = new Role(2, "content_creator");
            userDto.setSzerepkor(role);
            
        } else {
            Role role = new Role(1, "felhasznalo");
            userDto.setSzerepkor(role); // 1 = felhasználó szerepkör
        }*/
        if (contentCreator != null) {
            Role role = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Role not found"));
            userDto.setSzerepkor(role);
        } else {
            Role role = roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Role not found"));
            userDto.setSzerepkor(role);
        }


        registerService.registerUser(userDto, model);
        return "redirect:/login?success=true";
    }
}
