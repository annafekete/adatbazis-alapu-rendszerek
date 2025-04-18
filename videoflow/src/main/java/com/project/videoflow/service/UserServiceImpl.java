package com.project.videoflow.service;

import com.project.videoflow.dto.ProfileDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private ProfileDto fakeUser = new ProfileDto();

    public UserServiceImpl() {
        fakeUser.setFirstName("Jane");
        fakeUser.setLastName("Smitherson");
        fakeUser.setEmail("email@genericdomain.net");
    }

    @Override
    public ProfileDto getCurrentUserProfile() {
        return fakeUser;
    }

    @Override
    public void updateUser(ProfileDto profileDto) {
        fakeUser.setFirstName(profileDto.getFirstName());
        fakeUser.setLastName(profileDto.getLastName());
        fakeUser.setEmail(profileDto.getEmail());
    }
    @Override
    public void updateProfilePicture(String imageUrl) {
        fakeUser.setAvatarUrl(imageUrl);
    }
}