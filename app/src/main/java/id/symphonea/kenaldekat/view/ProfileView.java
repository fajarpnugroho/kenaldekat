package id.symphonea.kenaldekat.view;

import id.symphonea.kenaldekat.api.model.response.VisionMissionResponse;

public interface ProfileView {
    void showDataProfile(VisionMissionResponse visionMissionResponse);

    void showLoading();

    void hideLoading();
}
