package id.symphonea.kenaldekat.presenter;

import javax.inject.Inject;

import id.symphonea.kenaldekat.api.ApiConfig;
import id.symphonea.kenaldekat.api.model.response.NewsResponse;
import id.symphonea.kenaldekat.api.service.BeritaService;
import id.symphonea.kenaldekat.view.VideoView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class VideoPresenter implements BasePresenter {


    @Inject BeritaService service;

    private VideoView videoView;

    @Inject
    public VideoPresenter() {}

    public void initView(VideoView videoView) {
        this.videoView = videoView;
    }

    public void loadBeritaStream(String paslondId) {
        Call<NewsResponse> call = service.getListBeritaCandidate(paslondId, ApiConfig.API_KEY,
                null, null);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Response<NewsResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    NewsResponse newsResponse = response.body();
                    videoView.showListBerita(newsResponse);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
