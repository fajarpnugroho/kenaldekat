package id.symphonea.kenaldekat;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class KenalDekatApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.initializeComponent(this);

        Fabric.with(this, new Crashlytics());

        FacebookSdk.sdkInitialize(this);

/*        Parse.enableLocalDatastore(this);
        Parse.initialize(this, ApiConfig.PARSE_KEY,
                ApiConfig.PARSE_CLIENT);

        ParseObject.registerSubclass(DukunganParseObject.class);*/
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
