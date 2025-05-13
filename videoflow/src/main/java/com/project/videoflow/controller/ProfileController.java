package com.project.videoflow.controller;

import com.project.videoflow.dto.ProfileDto;
import com.project.videoflow.model.User;
import com.project.videoflow.service.UserService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";
        String role = user.getSzerepkor().getSzerepkornev();
        model.addAttribute("role", role);
        String username = user.getFelhasznalonev();
        model.addAttribute("username", username);
        ProfileDto profile = userService.getProfileByUser(user);
        model.addAttribute("profile", profile);
        return "profile";
    }



    // Profil frissítés form
    @GetMapping("/profile/update")
    public String updateProfileForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";}
        ProfileDto profile = userService.getCurrentUserProfile();  // Lekéri az aktuális felhasználói adatokat
        model.addAttribute("profile", profile);  // Átadja az adatokat a formhoz
        return "update";  // Update oldal betöltése
    }

    // Profil frissítése
    public String updateProfile(@ModelAttribute("profile") ProfileDto profileDto, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";}
        userService.updateUser(profileDto);
        return "redirect:/profile";
    }

    @PostMapping("/profile/update/picture")
    public String updateProfilePicture(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";}
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/images/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));
            userService.updateProfilePicture("/images/" + fileName);}
        return "redirect:/profile";}
}
