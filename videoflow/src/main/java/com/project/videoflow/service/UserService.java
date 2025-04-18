package com.project.videoflow.service;

import com.project.videoflow.dto.ProfileDto;

public interface UserService {
    ProfileDto getCurrentUserProfile();
    void updateUser(ProfileDto profileDto);
    void updateProfilePicture(String imageUrl);
}