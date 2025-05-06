package com.project.videoflow.model;
import java.io.Serializable;
import java.util.Objects;

public class KedveliId implements Serializable {
    private String email;
    private Long videoid;

    public KedveliId() {}

    public KedveliId(String email, Long videoid) {
        this.email = email;
        this.videoid = videoid;
    }

    // equals & hashCode kötelező!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KedveliId)) return false;
        KedveliId that = (KedveliId) o;
        return Objects.equals(email, that.email) && Objects.equals(videoid, that.videoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, videoid);
    }
}

