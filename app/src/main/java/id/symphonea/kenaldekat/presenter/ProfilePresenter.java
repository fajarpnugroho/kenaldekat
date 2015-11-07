package id.symphonea.kenaldekat.presenter;

import javax.inject.Inject;

import id.symphonea.kenaldekat.api.ApiConfig;
import id.symphonea.kenaldekat.api.model.response.DanaKampanyeResponse;
import id.symphonea.kenaldekat.api.model.response.VisionMissionResponse;
import id.symphonea.kenaldekat.api.service.CandidateService;
import id.symphonea.kenaldekat.api.service.KampanyeService;
import id.symphonea.kenaldekat.view.ProfileView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProfilePresenter implements BasePresenter {

    @Inject CandidateService candidateService;
    @Inject KampanyeService kampanyeService;

    @Inject public ProfilePresenter() {}

    private ProfileView view;

    public void initView(ProfileView view) {
        this.view = view;
    }

    public void loadDetailVisionMission(String pesertaId) {
        view.showLoading();

        Call<VisionMissionResponse>  call = candidateService.listOfVisionMissions(0, 10,
                pesertaId, ApiConfig.API_KEY);
        call.enqueue(new Callback<VisionMissionResponse>() {
            @Override
            public void onResponse(Response<VisionMissionResponse> response, Retrofit retrofit) {
                view.hideLoading();

                if (response.body() != null) {
                    VisionMissionResponse visionMissionResponse = response.body();
                    view.showDataProfile(visionMissionResponse);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.hideLoading();
            }
        });
    }

    public void loadDanaKampanye(String paslodId) {
        Call<DanaKampanyeResponse> call = kampanyeService.getListDanaKampanye(ApiConfig.API_KEY,
                paslodId);
        call.enqueue(new Callback<DanaKampanyeResponse>() {
            @Override
            public void onResponse(Response<DanaKampanyeResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    DanaKampanyeResponse danaKampanyeResponse = response.body();
                    view.showDanaKampanye(danaKampanyeResponse);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
