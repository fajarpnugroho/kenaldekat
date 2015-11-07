package id.symphonea.kenaldekat.view;

import id.symphonea.kenaldekat.api.model.response.NewsResponse;

public interface VideoView {
    void showListBerita(NewsResponse newsResponse);

    void initRecycler();
}
