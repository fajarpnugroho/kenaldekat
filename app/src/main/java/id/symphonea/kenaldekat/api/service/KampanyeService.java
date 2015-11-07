package id.symphonea.kenaldekat.api.service;

import id.symphonea.kenaldekat.api.model.response.DanaKampanyeResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface KampanyeService {

    @GET("infokampanye/api/danakampanye")
    Call<DanaKampanyeResponse> getListDanaKampanye(@Query("apiKey") String apiKey,
                             @Query("peserta") String paslonId);
}
