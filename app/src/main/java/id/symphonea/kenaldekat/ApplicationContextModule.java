package id.symphonea.kenaldekat;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {
    private final KenalDekatApp application;

    public ApplicationContextModule(KenalDekatApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    KenalDekatApp provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }


}
