package com.project.videoflow.model;

// model - Entitások



import jakarta.persistence.*;

@Entity
@Table(name = "FELHASZNALO")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false, unique = true)
    String felhasznalonev;

    @Column(nullable = false)
    String jelszo;

    @ManyToOne
    @JoinColumn(name = "szerepkorid", nullable = false)
    private Role szerepkor;
    
    @Column(nullable = true)
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
    

    public Role getSzerepkor() {
        return this.szerepkor;
    }

    public void setSzerepkor(Role szerepkor) {
        this.szerepkor = szerepkor;
    }

}
