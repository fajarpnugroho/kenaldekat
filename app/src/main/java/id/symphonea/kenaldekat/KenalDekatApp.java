package id.symphonea.kenaldekat;

import android.app.Application;

import com.facebook.FacebookSdk;

public class KenalDekatApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.initializeComponent(this);

        FacebookSdk.sdkInitialize(this);
    }


}
