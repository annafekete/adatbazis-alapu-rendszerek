package com.project.videoflow.controller;

import com.project.videoflow.model.User;
import com.project.videoflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserCrudController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("newUser", new User());
        return "userCrud";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user) {
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/edit/{email}")
    public String editUser(@PathVariable String email,
                           @RequestParam("felhasznalonev") String felhasznalonev,
                           @RequestParam("jelszo") String jelszo) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setFelhasznalonev(felhasznalonev);
            existingUser.setJelszo(jelszo);
            userRepository.save(existingUser);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
        return "redirect:/admin/users";
    }
}
