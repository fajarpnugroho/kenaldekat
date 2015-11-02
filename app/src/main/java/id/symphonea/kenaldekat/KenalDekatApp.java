package id.symphonea.kenaldekat;

import android.app.Application;

public class KenalDekatApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.initializeComponent(this);
    }
}
