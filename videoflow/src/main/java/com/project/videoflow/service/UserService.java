package com.project.videoflow.service;

import com.project.videoflow.dto.ProfileDto;
import com.project.videoflow.model.User;

public interface UserService {
    ProfileDto getCurrentUserProfile();
    ProfileDto getProfileByUser(User user);

    void updateUser(ProfileDto profileDto);
    void updateProfilePicture(String imageUrl);
}