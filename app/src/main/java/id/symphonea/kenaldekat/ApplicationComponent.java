package id.symphonea.kenaldekat;

import javax.inject.Singleton;

import dagger.Component;
import id.symphonea.kenaldekat.api.ApiModule;
import id.symphonea.kenaldekat.view.MainActivity;

@Singleton
@Component(modules = {ApplicationContextModule.class, ApiModule.class})
public interface ApplicationComponent extends ApplicationContextComponent {
    void inject(MainActivity mainActivity);
}
