package id.symphonea.kenaldekat.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.Injector;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.analytic.AnalyticManager;
import id.symphonea.kenaldekat.api.model.response.DanaKampanyeResponse;
import id.symphonea.kenaldekat.api.model.response.PaslonEntity;
import id.symphonea.kenaldekat.api.model.response.VisionMissionResponse;
import id.symphonea.kenaldekat.presenter.ProfilePresenter;
import id.symphonea.kenaldekat.view.ProfileView;
import id.symphonea.kenaldekat.view.widget.CandidateProfileView;
import id.symphonea.kenaldekat.view.widget.DanaView;
import id.symphonea.kenaldekat.view.widget.VisionMissionView;

public class ProfileFragment extends BaseFragment implements ProfileView {

    public static final String ARG_PESERTA_ID = "arg_peserta_id";

    @Bind(R.id.view_calon) CandidateProfileView calonView;
    @Bind(R.id.view_wakil) CandidateProfileView wakilView;
    @Bind(R.id.view_vision_mission) VisionMissionView visionMissionView;
    @Bind(R.id.view_dana) DanaView danaView;
    @Bind(R.id.btn_sumber) FrameLayout btnSumber;

    @Bind(R.id.content) LinearLayout content;
    @Bind(R.id.loading) ProgressBar loading;

    @Inject ProfilePresenter presenter;
    @Inject AnalyticManager analyticManager;

    public ProfileFragment() {}

    public static ProfileFragment newInstance(String pesertaId) {
        Bundle args = new Bundle();
        args.putString(ARG_PESERTA_ID, pesertaId);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.INSTANCE.getApplicationComponent().inject(this);

        presenter.initView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadDetailVisionMission(getArguments().getString(ARG_PESERTA_ID));
        presenter.loadDanaKampanye(getArguments().getString(ARG_PESERTA_ID));
    }

    @Override
    public void showDataProfile(final VisionMissionResponse visionMissionResponse) {
        if (visionMissionResponse.data.results.count > 0) {

            PaslonEntity calonEntity = visionMissionResponse.data.results.vision_missions
                    .get(0).paslon.get(0);

            PaslonEntity wakilEntity = visionMissionResponse.data.results.vision_missions.get(0)
                    .paslon.get(1);

            analyticManager.sendScreen("Show candidate profile of " + calonEntity.nama + " & "
                    + wakilEntity.nama);

            calonView.bind(calonEntity);
            wakilView.bind(wakilEntity);

            visionMissionView.bind(visionMissionResponse.data.results.vision_missions.get(0).visi,
                    visionMissionResponse.data.results.vision_missions.get(0).misi);

            btnSumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSumber(visionMissionResponse.data.results.vision_missions.get(0).sumber);
                }
            });

        }  else {
            content.setVisibility(View.GONE);
            return;
        }
    }

    private void showSumber(String sumber) {

        analyticManager.sendEvent("Profile candidate", "Open source", sumber);

        Uri uri = Uri.parse(sumber);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getActivity().startActivity(intent);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showDanaKampanye(DanaKampanyeResponse danaKampanyeResponse) {
        content.setVisibility(View.VISIBLE);
        danaView.bind(danaKampanyeResponse.data.results.danakampanye.get(0));
    }
}
