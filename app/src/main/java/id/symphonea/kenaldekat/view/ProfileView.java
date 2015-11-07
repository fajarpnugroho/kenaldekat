package id.symphonea.kenaldekat.view;

import id.symphonea.kenaldekat.api.model.response.DanaKampanyeResponse;
import id.symphonea.kenaldekat.api.model.response.VisionMissionResponse;

public interface ProfileView {
    void showDataProfile(VisionMissionResponse visionMissionResponse);

    void showLoading();

    void hideLoading();

    void showDanaKampanye(DanaKampanyeResponse danaKampanyeResponse);
}
