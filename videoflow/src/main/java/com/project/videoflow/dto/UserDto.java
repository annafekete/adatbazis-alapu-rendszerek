package com.project.videoflow.dto;

import com.project.videoflow.model.Role;


public class UserDto {
    private String email;

    private String felhasznalonev;

    private String jelszo;

    private Role szerepkor;
    

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFelhasznalonev() {
        return this.felhasznalonev;
    }

    public void setFelhasznalonev(String username) {
        this.felhasznalonev = username;
    }

    public String getJelszo() {
        return this.jelszo;
    }

    public void setJelszo(String password) {
        this.jelszo = password;
    }

    public Role getSzerepkor() {
        return this.szerepkor;
    }

    public void setSzerepkor(Role szerepkor) {
        this.szerepkor = szerepkor;
    }

}