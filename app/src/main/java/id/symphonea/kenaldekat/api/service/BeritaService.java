package id.symphonea.kenaldekat.api.service;

import id.symphonea.kenaldekat.api.model.response.NewsResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface BeritaService {

    @GET("berita-pilkada/api/news")
    Call<NewsResponse> getListBeritaCandidate(@Query("candidate_id") String paslonId,
                                              @Query("apiKey") String apiKey,
                                              @Query("offset") Integer offset,
                                              @Query("limit") Integer limit);
}
