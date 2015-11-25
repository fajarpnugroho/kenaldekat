package id.symphonea.kenaldekat.analytic;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

public class AnalyticManager {
    private Tracker tracker;

    @Inject
    public AnalyticManager(Tracker tracker) {
        this.tracker = tracker;
    }

    public void sendEvent(String categoryName, String categoryAction, String categoryLabel) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(categoryName)
                .setAction(categoryAction)
                .setLabel(categoryLabel)
                .build());
    }

    public void sendScreen(String screenName) {
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
