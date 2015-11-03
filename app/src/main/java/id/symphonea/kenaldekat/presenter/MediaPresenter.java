package id.symphonea.kenaldekat.presenter;

import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.symphonea.kenaldekat.api.ApiConfig;
import id.symphonea.kenaldekat.api.model.response.MediaResponse;
import id.symphonea.kenaldekat.api.service.MediaService;
import id.symphonea.kenaldekat.view.MediaView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

@Singleton
public class MediaPresenter implements BasePresenter {

    public static final int DEFAULT_LIMIT = 10;

    @Inject
    MediaService mediaService;

    private MediaView view;
    private int offset = 0;
    private String paslonId;
    private boolean maxLimit = false;
    private boolean loading = false;

    @Inject
    public MediaPresenter() {
    }

    public void initView(MediaView view) {
        this.view = view;
    }

    public void loadRekamJejakMedia(String paslonId) {
        view.showLoading();
        offset = 0;

        if (paslonId != null) {
            this.paslonId = paslonId;
        }

        Call<MediaResponse> call = mediaService.rekamJejakMedia(offset, DEFAULT_LIMIT, paslonId, ApiConfig.API_KEY);
        call.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Response<MediaResponse> response, Retrofit retrofit) {
                view.hideLoading();
                if (response.body() != null) {
                    MediaResponse mediaResponse = response.body();
                    offset += mediaResponse.data.results.rekam_jejak.size();
                    view.showJejakMedia(mediaResponse);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void onScrollEnds() {

        if (loading) {
            return;
        }

        if (maxLimit) {
            return;
        }

        loading = true;
        view.showLoading();

        Call<MediaResponse> call = mediaService.rekamJejakMedia(offset, DEFAULT_LIMIT, paslonId, ApiConfig.API_KEY);
        call.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Response<MediaResponse> response, Retrofit retrofit) {
                loading = false;
                view.hideLoading();
                if (response.body() != null) {
                    MediaResponse mediaResponse = response.body();
                    if (offset == mediaResponse.data.results.total) {
                        maxLimit = true;
                    }
                    offset += mediaResponse.data.results.rekam_jejak.size();
                    view.loadMoreList(mediaResponse);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}