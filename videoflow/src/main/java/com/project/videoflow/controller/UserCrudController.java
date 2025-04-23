package com.project.videoflow.controller;

import com.project.videoflow.model.Role;
import com.project.videoflow.model.User;
import com.project.videoflow.repository.RoleRepository;
import com.project.videoflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crud")
public class UserCrudController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "user-crud";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user) {
        userRepository.save(user);
        return "redirect:/crud";
    }

    @GetMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        userRepository.deleteById(email);
        return "redirect:/crud";
    }

    @PostMapping("/edit/{email}")
    public String editUser(@PathVariable String email,
                           @RequestParam("felhasznalonev") String felhasznalonev,
                           @RequestParam("jelszo") String jelszo,
                           @RequestParam("szerepkorId") Long szerepkorId) {

        User user = userRepository.findById(email).orElse(null);
        Role role = roleRepository.findById(szerepkorId).orElse(null);

        if (user != null && role != null) {
            user.setFelhasznalonev(felhasznalonev);
            user.setJelszo(jelszo);
            user.setSzerepkor(role);
            userRepository.save(user);
        }

        return "redirect:/crud";
    }
}
