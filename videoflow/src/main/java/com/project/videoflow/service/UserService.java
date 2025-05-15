package com.project.videoflow.service;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.videoflow.dto.ProfileDto;
import com.project.videoflow.dto.UpdateProfileRequest;
import com.project.videoflow.model.User;
public interface UserService {
    ProfileDto getCurrentUserProfile();
    ProfileDto getProfileByUser(User user);

    void updateProfile(User user, UpdateProfileRequest updateProfileRequest);
    void updateProfilePicture(String imageUrl);

    
}