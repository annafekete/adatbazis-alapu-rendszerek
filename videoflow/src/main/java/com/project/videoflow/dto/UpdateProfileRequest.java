package com.project.videoflow.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateProfileRequest {
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String felhasznalonev) {
        this.username = felhasznalonev;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String jelszo) {
        this.password = jelszo;
    }

}
