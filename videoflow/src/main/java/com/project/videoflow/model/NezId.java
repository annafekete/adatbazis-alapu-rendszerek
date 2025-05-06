package com.project.videoflow.model;

import java.io.Serializable;
import java.util.Objects;

public class NezId implements Serializable {

    private String email;
    private Long videoid;

    // Alapértelmezett konstruktor
    public NezId() {}

    public NezId(String email, Long videoid) {
        this.email = email;
        this.videoid = videoid;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getVideoid() {
        return videoid;
    }

    public void setVideoid(Long videoid) {
        this.videoid = videoid;
    }

    // hashCode és equals metódusok az összehasonlításhoz és a hashMap működéséhez
    @Override
    public int hashCode() {
        return Objects.hash(email, videoid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NezId nezId = (NezId) o;
        return Objects.equals(email, nezId.email) && Objects.equals(videoid, nezId.videoid);
    }
}
