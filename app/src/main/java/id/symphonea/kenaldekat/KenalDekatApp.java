package id.symphonea.kenaldekat;

import android.app.Application;

import com.facebook.FacebookSdk;

public class KenalDekatApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.initializeComponent(this);

        FacebookSdk.sdkInitialize(this);

/*        Parse.enableLocalDatastore(this);
        Parse.initialize(this, ApiConfig.PARSE_KEY,
                ApiConfig.PARSE_CLIENT);

        ParseObject.registerSubclass(DukunganParseObject.class);*/
    }


}
