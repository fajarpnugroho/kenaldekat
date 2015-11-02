package id.symphonea.kenaldekat.view;

import id.symphonea.kenaldekat.api.model.response.CandidatesResponse;
import id.symphonea.kenaldekat.api.model.response.ProvincesResponse;

public interface MainView {
    void initToolbar();

    void initRecyclerView();

    void showListProvince(ProvincesResponse response);

    void showListCandidates(CandidatesResponse candidatesResponse);

    void showList();

    void hideList();

    void showLoadingView();

    void hideLoadingView();

    void showErrorView();

    void hideErrorView();

    void showEmptyView();

    void hideEmptyView();

    void reset(int state);

    void showMoreList(CandidatesResponse candidatesResponse);
}
