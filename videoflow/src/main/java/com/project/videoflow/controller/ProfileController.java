package com.project.videoflow.controller;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.project.videoflow.dto.ProfileDto;
import com.project.videoflow.dto.UpdateProfileRequest;
import com.project.videoflow.model.User;
import com.project.videoflow.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final BCryptPasswordEncoder passwordEncoder;
    

    @Autowired
    private UserService userService;

    ProfileController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null)
            return "redirect:/login";
        String role = user.getSzerepkor().getSzerepkornev();
        model.addAttribute("role", role);
        String felhasznalonev = user.getFelhasznalonev();
        model.addAttribute("username", felhasznalonev);

        ProfileDto profile = userService.getProfileByUser(user);
        model.addAttribute("profile", profile);
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    // Profil frissítés form
    @GetMapping("/profile/update")
    public String updateProfileForm(Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        System.out.println("Update profile called by user: " + (user != null ? user.getFelhasznalonev() : "no user"));
        if (user == null) {
            return "redirect:/login";
        }
        ProfileDto profile = userService.getProfileByUser(user);
        model.addAttribute("profile", profile);
        return "update";
    }


    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("profile") ProfileDto profileDto, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
        updateProfileRequest.setUsername(profileDto.getUsername());



        String newPassword = profileDto.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setJelszo(encodedPassword);
        }


        userService.updateProfile(user, updateProfileRequest);

        userService.updateUser(user);
        session.setAttribute("loggedInUser", user);

        return "redirect:/profile";
    }


    @PostMapping("/profile/update/picture")
    public String updateProfilePicture(@RequestParam("file") MultipartFile file, HttpSession session)
            throws IOException {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/images/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));
            userService.updateProfilePicture("/images/" + fileName);
        }
        return "redirect:/profile";
    }
}
