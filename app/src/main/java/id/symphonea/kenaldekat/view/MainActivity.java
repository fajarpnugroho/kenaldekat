package id.symphonea.kenaldekat.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.Injector;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.CandidatesResponse;
import id.symphonea.kenaldekat.api.model.response.ProvincesResponse;
import id.symphonea.kenaldekat.api.model.response.ProvinsiEntity;
import id.symphonea.kenaldekat.presenter.MainPresenter;
import id.symphonea.kenaldekat.view.adapter.CandidatesAdapter;
import id.symphonea.kenaldekat.view.adapter.ProvincesAdapter;
import id.symphonea.kenaldekat.view.listener.LoadMoreScrollListener;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainView,
        AdapterView.OnItemSelectedListener, CandidatesAdapter.Listener {

    public static final int STATE_LOADING = 0;
    public static final int STATE_DONE = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final String EXTRA_PESERTA_ID = "extra_peserta_id";
    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_PROVINSI = "provinsi";

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.spinner_nav) Spinner spinnerNav;
    @Bind(R.id.recyclerview) RecyclerView recyclerView;
    @Bind(R.id.empty) TextView emptyView;
    @Bind(R.id.loading) ProgressBar loading;

    @Inject
    MainPresenter presenter;

    private CandidatesAdapter candidatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.INSTANCE.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initRecyclerView();

        presenter.initView(this);
        presenter.loadProvinces();
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new LoadMoreScrollListener(presenter));

        candidatesAdapter = new CandidatesAdapter(this);
    }

    @Override
    public void showListProvince(ProvincesResponse response) {
        Timber.v(response.data.toString());
        ProvincesAdapter adapter = new ProvincesAdapter(this, response);

        spinnerNav.setAdapter(adapter);
        spinnerNav.setOnItemSelectedListener(this);
    }

    @Override
    public void showListCandidates(CandidatesResponse candidatesResponse) {
        reset(STATE_DONE);
        if (candidatesResponse.data.results.candidates.size() > 0) {
            candidatesAdapter.setCandidatesResponse(candidatesResponse.data.results.candidates);
            recyclerView.setAdapter(candidatesAdapter);
        } else {
            reset(STATE_EMPTY);
        }
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
    public void showLoadingView() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void reset(int state) {
        switch (state) {
            case STATE_LOADING:
                showLoadingView();
                hideErrorView();
                hideEmptyView();
                break;
            case STATE_DONE:
                hideLoadingView();
                showList();
                hideErrorView();
                hideEmptyView();
                break;
            case STATE_ERROR:
                hideLoadingView();
                hideList();
                showErrorView();
                hideEmptyView();
                break;
            case STATE_EMPTY:
                hideLoadingView();
                hideList();
                hideErrorView();
                showEmptyView();
                break;
        }
    }

    @Override
    public void showMoreList(CandidatesResponse candidatesResponse) {
        candidatesAdapter.addedMoreList(candidatesResponse.data.results.candidates);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hideList();
        ProvinsiEntity provinsiEntity = (ProvinsiEntity) parent.getItemAtPosition(position);
        reset(STATE_LOADING);
        presenter.loadCandidates(provinsiEntity);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onItemClickListener(String pesertaId, String url, String provinsi) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_PESERTA_ID, pesertaId);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_PROVINSI, provinsi);
        startActivity(intent);
    }
}
