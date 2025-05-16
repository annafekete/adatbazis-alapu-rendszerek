package com.project.videoflow.model;

import java.io.Serializable;
import java.util.Objects;

public class UploadId implements Serializable {
    private Long videoid;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UploadId))
            return false;
        UploadId that = (UploadId) o;
        return Objects.equals(videoid, that.videoid) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoid, email);
    }

    public void setEmail(String email) {
    }

    public void setVideoid(Long videoid) {
    }
}