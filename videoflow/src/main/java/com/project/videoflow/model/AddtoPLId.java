package com.project.videoflow.model;

import java.io.Serializable;
import java.util.Objects;

public class AddtoPLId implements Serializable{
    private Long videoid;
    private Long playlistid;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AddtoPLId))
            return false;
        AddtoPLId that = (AddtoPLId) o;
        return Objects.equals(playlistid, that.playlistid) && Objects.equals(videoid, that.videoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistid, videoid);
    }

}
