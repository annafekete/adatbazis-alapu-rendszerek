package com.project.videoflow.model;

// model - Entitások



import jakarta.persistence.*;

@Entity
@Table(name = "FELHASZNALO")
public class User {
    @Id
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "FELHASZNALONEV", nullable = false, unique = true)
    private String felhasznalonev;

    @Column(name = "JELSZO", nullable = false)
    private String jelszo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SZEREPKORID")
    private Role szerepkor;

    @Column(name = "AVATARURL")
    private String avatarUrl;

    public User() {
    }

    public User(String email, String felhasznalonev, String jelszo, Role szerepkor) {
        this.email = email;
        this.felhasznalonev = felhasznalonev;
        this.jelszo = jelszo;
        this.szerepkor = szerepkor;
        this.avatarUrl = "src\\main\\resources\\static\\images\\user_profile.webp";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFelhasznalonev() {
        return felhasznalonev;
    }

    public void setFelhasznalonev(String username) {
        this.felhasznalonev = username;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String password) {
        this.jelszo = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Role getSzerepkor() {
        return this.szerepkor;
    }

    public void setSzerepkor(Role szerepkor) {
        this.szerepkor = szerepkor;
    }

}