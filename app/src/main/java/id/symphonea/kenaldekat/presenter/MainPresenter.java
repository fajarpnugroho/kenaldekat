package id.symphonea.kenaldekat.presenter;

import android.app.Application;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.symphonea.kenaldekat.KenalDekatApp;
import id.symphonea.kenaldekat.api.ApiConfig;
import id.symphonea.kenaldekat.api.model.response.CandidatesResponse;
import id.symphonea.kenaldekat.api.model.response.ProvincesResponse;
import id.symphonea.kenaldekat.api.model.response.ProvinsiEntity;
import id.symphonea.kenaldekat.api.service.CandidateService;
import id.symphonea.kenaldekat.util.AssetUtils;
import id.symphonea.kenaldekat.view.MainActivity;
import id.symphonea.kenaldekat.view.MainView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

@Singleton
public class MainPresenter {

    public static final String UNEXPECTED_ERROR = "Unexpected error";
    public static final String READER_ALREADY_BEEN_CLOSED = "Reader already been closed";
    public static final int DEFAULT_LIMIT = 10;

    @Inject
    CandidateService candidateService;

    private MainView view;
    private KenalDekatApp application;
    private String provinceId = null;
    private int offset;
    private boolean maxLimit = false;

    private boolean loading = false;

    @Inject
    public MainPresenter(KenalDekatApp application) {
        this.application = application;
    }

    public void initView(MainView view) {
        this.view = view;
    }

    public void loadProvinces() {
        // read list provinces from assets

        Reader reader = null;
        try {
            reader = AssetUtils.readFile(application, "provinces_json.json");
            ProvincesResponse response =
                    new Gson().fromJson(
                            reader,
                            ProvincesResponse.class);
            view.showListProvince(response);

        } catch (IOException e) {
            throw new RuntimeException(UNEXPECTED_ERROR);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Timber.e(READER_ALREADY_BEEN_CLOSED);
            }
        }
    }

    public void loadCandidates(ProvinsiEntity provinsiEntity) {
        offset = 0;

        if (provinsiEntity != null) {
            provinceId = String.valueOf(provinsiEntity.id);
        }

        Call<CandidatesResponse> responseCall = candidateService.listOfCandidates(offset,
                DEFAULT_LIMIT, provinceId, null, null, null, null, ApiConfig.API_KEY);

        responseCall.enqueue(new Callback<CandidatesResponse>() {
            @Override
            public void onResponse(Response<CandidatesResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    CandidatesResponse candidatesResponse = response.body();
                    offset += candidatesResponse.data.results.count;
                    view.showListCandidates(candidatesResponse);
                } else {
                    view.reset(MainActivity.STATE_EMPTY);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.reset(MainActivity.STATE_ERROR);
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
        view.reset(MainActivity.STATE_LOADING);

        Call<CandidatesResponse> responseCall = candidateService.listOfCandidates(offset,
                DEFAULT_LIMIT, provinceId, null, null, null, null, ApiConfig.API_KEY);

        responseCall.enqueue(new Callback<CandidatesResponse>() {
            @Override
            public void onResponse(Response<CandidatesResponse> response, Retrofit retrofit) {
                loading = false;
                view.reset(MainActivity.STATE_DONE);

                if (response.body() != null) {
                    CandidatesResponse candidatesResponse = response.body();
                    offset += candidatesResponse.data.results.count;
                    if (offset == candidatesResponse.data.results.total) {
                        maxLimit = true;
                    }
                    view.showMoreList(candidatesResponse);
                } else {
                    view.reset(MainActivity.STATE_EMPTY);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loading = false;

                view.reset(MainActivity.STATE_ERROR);
            }
        });

    }
}
