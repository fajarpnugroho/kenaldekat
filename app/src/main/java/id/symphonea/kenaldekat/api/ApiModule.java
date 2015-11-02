package id.symphonea.kenaldekat.api;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.symphonea.kenaldekat.api.service.CandidateService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class ApiModule {
    @Provides
    Endpoint provideEndpoint() {
        return Endpoint.PRODUCTION;
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
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
    CandidateService provideCandidateService(Retrofit retrofit){
        return retrofit.create(CandidateService.class);
    }
}
