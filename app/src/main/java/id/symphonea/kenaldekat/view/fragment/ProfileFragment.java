package id.symphonea.kenaldekat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.Injector;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.PaslonEntity;
import id.symphonea.kenaldekat.api.model.response.VisionMissionResponse;
import id.symphonea.kenaldekat.presenter.ProfilePresenter;
import id.symphonea.kenaldekat.view.ProfileView;
import id.symphonea.kenaldekat.view.widget.CandidateContentView;
import id.symphonea.kenaldekat.view.widget.CandidateGroupView;
import id.symphonea.kenaldekat.view.widget.ContentView;

public class ProfileFragment extends BaseFragment implements ProfileView {

    public static final String ARG_PESERTA_ID = "arg_peserta_id";

    @Bind(R.id.content) LinearLayout contentRoot;
    @Bind(R.id.loading) ProgressBar loading;

    @Inject ProfilePresenter presenter;

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
    }

    @Override
    public void showDataProfile(VisionMissionResponse visionMissionResponse) {
        if (visionMissionResponse.data.results.count > 0) {

            List<PaslonEntity> paslon = visionMissionResponse.data.results.vision_missions
                    .get(0).paslon;

            String visi = visionMissionResponse.data.results.vision_missions.get(0).visi;
            String misi = visionMissionResponse.data.results.vision_missions.get(0).misi;
            String sumber = visionMissionResponse.data.results.vision_missions.get(0).sumber;

            generatedPaslon(paslon);
            generatedVisi(visi);
            generatedMisi(misi);
            generatedSumber(sumber);
        }
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    private void generatedSumber(String sumber) {
        ContentView view = new ContentView(getActivity());
        view.bind("Sumber", sumber);

        contentRoot.addView(view);
    }

    private void generatedMisi(String misi) {
        ContentView view = new ContentView(getActivity());
        view.bind("Misi", misi);

        contentRoot.addView(view);
    }

    private void generatedVisi(String visi) {
        ContentView view = new ContentView(getActivity());
        view.bind("Visi", visi);

        contentRoot.addView(view);
    }

    private void generatedPaslon(List<PaslonEntity> paslon) {
        for (PaslonEntity paslonEntity : paslon) {
            List<CandidateContentView> views = new ArrayList<>();

            views.add(generateCandidateView("Nama", paslonEntity.nama));
            views.add(generateCandidateView("Pekerjaan", paslonEntity.pekerjaan));


            CandidateGroupView groupView = new CandidateGroupView(getActivity());
            groupView.bind(paslonEntity.kind, views);

            contentRoot.addView(groupView);
        }

    }

    private CandidateContentView generateCandidateView(String key, String value) {
        CandidateContentView contentView = new CandidateContentView(getActivity());
        contentView.bind(key, value);
        return contentView;
    }
}
