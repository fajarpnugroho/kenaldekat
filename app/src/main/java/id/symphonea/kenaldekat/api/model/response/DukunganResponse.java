package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public final class DukunganResponse {
    public final List<DukunganEntity> data;

    public DukunganResponse(List<DukunganEntity> data) {
        this.data = data;
    }
}

