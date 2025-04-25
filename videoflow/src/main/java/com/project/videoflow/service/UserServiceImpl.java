package com.project.videoflow.service;

import com.project.videoflow.dto.ProfileDto;
import com.project.videoflow.model.User;
import com.project.videoflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByFelhasznalonev(username);

        if (user == null) {
            throw new UsernameNotFoundException("Felhasználó nem található: " + username);
        }
        return user;
    }

    @Override
    public ProfileDto getCurrentUserProfile() {
        User user = getLoggedInUser();

        ProfileDto profileDto = new ProfileDto();
        profileDto.setEmail(user.getEmail());
        profileDto.setFirstName(user.getFelhasznalonev()); // vagy ha lesz külön firstName meződ, azt is kezelhetjük
        profileDto.setAvatarUrl(user.getAvatarUrl());

        return profileDto;
    }

    @Override
    public ProfileDto getProfileByUser(User user) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setEmail(user.getEmail());
        profileDto.setFirstName(user.getFelhasznalonev());
        profileDto.setAvatarUrl(user.getAvatarUrl());
        return profileDto;
    }


    @Override
    public void updateUser(ProfileDto profileDto) {
        User user = getLoggedInUser();

        user.setEmail(profileDto.getEmail());
        user.setFelhasznalonev(profileDto.getFirstName()); // új név átírása

        userRepository.save(user);
    }

    @Override
    public void updateProfilePicture(String imageUrl) {
        User user = getLoggedInUser();

        user.setAvatarUrl(imageUrl);
        userRepository.save(user);
    }
}
