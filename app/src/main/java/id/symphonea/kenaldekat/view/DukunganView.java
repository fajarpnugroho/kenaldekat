package id.symphonea.kenaldekat.view;

import id.symphonea.kenaldekat.api.model.response.DukunganEntity;
import id.symphonea.kenaldekat.api.model.response.DukunganResponse;

public interface DukunganView {
    void initRecycler();

    void showListDukungan(DukunganResponse dukunganResponse);

    void addedDukungan(DukunganEntity dukunganEntity);
}
