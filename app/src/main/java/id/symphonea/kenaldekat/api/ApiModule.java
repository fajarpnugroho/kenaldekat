package id.symphonea.kenaldekat.api;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.symphonea.kenaldekat.api.service.CandidateService;
import id.symphonea.kenaldekat.api.service.MediaService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class ApiModule {
    @Provides
    Endpoint provideEndpoint() {
        return Endpoint.PRODUCTION;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor());
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Endpoint endpoint) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(endpoint.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    CandidateService provideCandidateService(Retrofit retrofit){
        return retrofit.create(CandidateService.class);
    }

    @Provides
    @Singleton
    MediaService provideMediaService(Retrofit retrofit) {
        return retrofit.create(MediaService.class);
    }
}
