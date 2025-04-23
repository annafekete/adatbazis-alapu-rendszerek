package com.project.videoflow.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "SZEREPKOR")
public class Role {
    @Id
    private Long szerepkorid;

    @Column(nullable = false, unique = true)
    private String szerepkornev;

    @OneToMany(mappedBy = "szerepkor")
    private List<User> users;
    
    //Konstruktor
    public Role() {
    }

    public Role(int szerepkorid, String szerepkornev) {
        this.szerepkorid = Long.valueOf(szerepkorid);
        this.szerepkornev = szerepkornev;
    }

    //getterel es setterek

    public Long getSzerepkorid() {
        return this.szerepkorid;
    }

    public void setSzerepkorid(Long szerepkorid) {
        this.szerepkorid = szerepkorid;
    }

    public String getSzerepkornev() {
        return this.szerepkornev;
    }

    public void setSzerepkornev(String szerepkornev) {
        this.szerepkornev = szerepkornev;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    

}
