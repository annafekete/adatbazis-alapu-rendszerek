package com.project.videoflow.model;

import java.io.Serializable;
import java.util.Objects;

public class CreatePLId implements Serializable{
    private String email;
    private Long playlistid;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CreatePLId))
            return false;
        CreatePLId that = (CreatePLId) o;
        return Objects.equals(playlistid, that.playlistid) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistid, email);
    }

}
