package com.project.videoflow.model;

import java.io.Serializable;
import java.util.Objects;

public class FeltoltiId implements Serializable {
    private String email;
    private Long videoid;

    public FeltoltiId() {}

    public FeltoltiId(String email, Long videoid) {
        this.email = email;
        this.videoid = videoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeltoltiId)) return false;
        FeltoltiId that = (FeltoltiId) o;
        return Objects.equals(email, that.email) && Objects.equals(videoid, that.videoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, videoid);
    }
}