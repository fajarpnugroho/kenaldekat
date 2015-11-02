package id.symphonea.kenaldekat.view.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import id.symphonea.kenaldekat.presenter.MainPresenter;

public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private static final int PAGE_MULTIPLIER = 2;

    private MainPresenter presenter;

    private int visibleItemsInOnePage = 0;

    public LoadMoreScrollListener(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (visibleItemsInOnePage == 0) {
            visibleItemsInOnePage = recyclerView.getChildCount();
        }
        if (isStreamEnding((LinearLayoutManager) recyclerView.getLayoutManager())) {
            presenter.onScrollEnds();
        }
    }

    private boolean isStreamEnding(LinearLayoutManager layoutManager) {
        int pageThreshold = visibleItemsInOnePage * PAGE_MULTIPLIER;
        int positionThreshold = layoutManager.getItemCount() - pageThreshold;
        return layoutManager.findLastVisibleItemPosition() > Math.max(0, positionThreshold);
    }
}
