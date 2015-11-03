package id.symphonea.kenaldekat.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.Injector;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.MediaResponse;
import id.symphonea.kenaldekat.presenter.MediaPresenter;
import id.symphonea.kenaldekat.view.DividerItemDecoration;
import id.symphonea.kenaldekat.view.MediaView;
import id.symphonea.kenaldekat.view.adapter.MediaAdapter;
import id.symphonea.kenaldekat.view.listener.LoadMoreScrollListener;

public class MediaFragment extends BaseFragment implements MediaView, MediaAdapter.Listener {

    public static final String ARG_PASLON_ID = "arg_paslon_id";

    @Bind(R.id.recyclerview) RecyclerView recyclerView;
    @Bind(R.id.loading) ProgressBar loading;

    @Inject MediaPresenter presenter;

    private MediaAdapter mediaAdapter;

    public static MediaFragment newInstance(String paslonId) {
        Bundle args = new Bundle();
        args.putString(ARG_PASLON_ID, paslonId);

        MediaFragment fragment = new MediaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.INSTANCE.getApplicationComponent().inject(this);
        presenter.initView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();

        presenter.loadRekamJejakMedia(getArguments().getString(ARG_PASLON_ID));
    }

    @Override
    public void showJejakMedia(MediaResponse mediaResponse) {
        mediaAdapter.setMediaEntityList(mediaResponse.data.results.rekam_jejak);
        recyclerView.setAdapter(mediaAdapter);
    }

    @Override
    public void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new LoadMoreScrollListener(presenter));

        mediaAdapter = new MediaAdapter(this);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
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
    public void loadMoreList(MediaResponse mediaResponse) {
        mediaAdapter.addMoreList(mediaResponse.data.results.rekam_jejak);
    }

    @Override
    public void openUrlToBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getActivity().startActivity(intent);
    }
}
