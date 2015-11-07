package id.symphonea.kenaldekat.view;

import id.symphonea.kenaldekat.api.model.response.MediaResponse;

public interface MediaView {
    void showJejakMedia(MediaResponse mediaResponse);

    void initRecycler();

    void showList();

    void hideList();

    void showLoading();

    void hideLoading();

    void loadMoreList(MediaResponse mediaResponse);
}
