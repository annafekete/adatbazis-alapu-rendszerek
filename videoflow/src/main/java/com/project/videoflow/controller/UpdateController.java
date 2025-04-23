package com.project.videoflow.controller;

import com.project.videoflow.dto.ProfileDto;
import com.project.videoflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UpdateController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String userProfile(Model model) {
        ProfileDto profile = userService.getCurrentUserProfile();
        model.addAttribute("profile", profile);
        return "profile"; // profil nézet oldal
    }

    @PostMapping("/profile/update")
    public String updateUserProfile(@ModelAttribute("profile") ProfileDto profileDto,
                                    RedirectAttributes redirectAttributes) {
        userService.updateUser(profileDto);
        redirectAttributes.addFlashAttribute("successMessage", "Profil sikeresen frissítve!");
        return "redirect:/profile";}
}
