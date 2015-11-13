package id.symphonea.kenaldekat.analytic;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.symphonea.kenaldekat.KenalDekatApp;

@Module
public class AnalyticModule {

    @Provides
    @Singleton
    AnalyticProperty provideAnalyticProperty() {
        return AnalyticProperty.PRODUCTION;
    }

    @Provides
    @Singleton
    GoogleAnalytics provideGoogleAnalytics(KenalDekatApp application) {
        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(application);
        googleAnalytics.setLocalDispatchPeriod(1800);
        return googleAnalytics;
    }

    @Provides
    @Singleton
    Tracker provideTracker(GoogleAnalytics googleAnalytics, AnalyticProperty property) {
        Tracker tracker = googleAnalytics.newTracker(property.getValue());
        tracker.enableExceptionReporting(true);
        tracker.enableAutoActivityTracking(false);
        return tracker;
    }
}
