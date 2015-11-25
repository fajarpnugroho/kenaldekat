package id.symphonea.kenaldekat;

import javax.inject.Singleton;

import dagger.Component;
import id.symphonea.kenaldekat.analytic.AnalyticModule;
import id.symphonea.kenaldekat.api.ApiModule;
import id.symphonea.kenaldekat.view.DetailActivity;
import id.symphonea.kenaldekat.view.MainActivity;
import id.symphonea.kenaldekat.view.fragment.DukunganFragment;
import id.symphonea.kenaldekat.view.fragment.MediaFragment;
import id.symphonea.kenaldekat.view.fragment.ProfileFragment;
import id.symphonea.kenaldekat.view.fragment.VideoFragment;

@Singleton
@Component(modules = {ApplicationContextModule.class, ApiModule.class, AnalyticModule.class})
public interface ApplicationComponent extends ApplicationContextComponent {
    void inject(MainActivity mainActivity);

    void inject(ProfileFragment profileFragment);

    void inject(MediaFragment mediaFragment);

    void inject(VideoFragment videoFragment);

    void inject(DukunganFragment dukunganFragment);

    void inject(DetailActivity detailActivity);
}
