package id.symphonea.kenaldekat;

import android.app.Application;

public enum Injector {
    INSTANCE;

    private ApplicationComponent applicationComponent;

    Injector(){}

    void initializeComponent(KenalDekatApp application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(application)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
