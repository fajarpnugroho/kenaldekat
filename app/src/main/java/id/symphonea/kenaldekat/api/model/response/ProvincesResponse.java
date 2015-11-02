package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public class ProvincesResponse {
    public final List<ProvinsiEntity> data;

    public ProvincesResponse(List<ProvinsiEntity> data) {
        this.data = data;
    }
}
