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

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.Injector;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.NewsResponse;
import id.symphonea.kenaldekat.presenter.VideoPresenter;
import id.symphonea.kenaldekat.view.DividerItemDecoration;
import id.symphonea.kenaldekat.view.VideoView;
import id.symphonea.kenaldekat.view.adapter.BeritaAdapter;
import id.symphonea.kenaldekat.view.listener.LoadMoreScrollListener;

public class VideoFragment extends BaseFragment implements VideoView, BeritaAdapter.Listener {

    public static final String ARG_PASLON_ID = "arg_paslod_id";

    @Bind(R.id.recyclerview) RecyclerView recyclerView;

    @Inject VideoPresenter presenter;

    private BeritaAdapter adapter;

    public static VideoFragment newInstance(String paslonId) {
        Bundle args = new Bundle();
        args.putString(ARG_PASLON_ID, paslonId);

        VideoFragment fragment = new VideoFragment();
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
        presenter.loadBeritaStream(getArguments().getString(ARG_PASLON_ID));
    }

    @Override
    public void showListBerita(NewsResponse newsResponse) {
        adapter.setNewsEntities(newsResponse.data.results.news);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addOnScrollListener(new LoadMoreScrollListener(presenter));

        adapter = new BeritaAdapter(this);
    }

    @Override
    public void onItemClickListener(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getActivity().startActivity(intent);
    }
}
