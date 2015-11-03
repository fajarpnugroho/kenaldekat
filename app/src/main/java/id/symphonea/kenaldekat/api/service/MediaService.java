package id.symphonea.kenaldekat.api.service;

import id.symphonea.kenaldekat.api.model.response.MediaResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MediaService {

    @GET("rekam-jejak-media/api/rekam_jejak")
    Call<MediaResponse> rekamJejakMedia(@Query("offset") int offet,
                                        @Query("limit") int limit,
                                        @Query("paslon_id") String paslon_id,
                                        @Query("apiKey") String apiKey);
}
