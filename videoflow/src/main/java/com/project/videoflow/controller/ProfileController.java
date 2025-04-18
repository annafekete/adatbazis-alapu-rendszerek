package com.project.videoflow.controller;

import com.project.videoflow.dto.ProfileDto;
import com.project.videoflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    // Profil megjelenítése
    @GetMapping("/profile")
    public String showProfile(Model model) {
        ProfileDto profile = userService.getCurrentUserProfile();
        model.addAttribute("profile", profile);
        return "profile";
    }

    // Profil frissítés form
    @GetMapping("/profile/update")
    public String updateProfileForm(Model model) {
        ProfileDto profile = userService.getCurrentUserProfile();  // Lekéri az aktuális felhasználói adatokat
        model.addAttribute("profile", profile);  // Átadja az adatokat a formhoz
        return "update";  // Update oldal betöltése
    }

    // Profil frissítése
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("profile") ProfileDto profileDto) {
        userService.updateUser(profileDto);  // Felhasználói adatok frissítése
        return "redirect:/profile";  // Visszairányít a profil oldalra
    }
    @PostMapping("/profile/update/picture")
    public String updateProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/images/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));
            userService.updateProfilePicture("/images/" + fileName);
        }
        return "redirect:/profile";
    }
}
