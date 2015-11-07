package id.symphonea.kenaldekat.api.service;

import id.symphonea.kenaldekat.api.model.response.CandidatesResponse;
import id.symphonea.kenaldekat.api.model.response.VisionMissionResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CandidateService {

    @GET("calonpilkada/api/candidates")
    Call<CandidatesResponse> listOfCandidates(@Query("offset") int offset,
                                              @Query("limit") int limit,
                                              @Query("provinsi") String provinsiId,
                                              @Query("daerah") String daerahId,
                                              @Query("dukungan") String dukungan,
                                              @Query("suara") String suara,
                                              @Query("incumbent") String incumbent,
                                              @Query("apiKey") String apiKey);

    @GET("calonpilkada/api/vision_missions")
    Call<VisionMissionResponse> listOfVisionMissions(@Query("offset") int offset,
                                                     @Query("limit") int limit,
                                                     @Query("peserta") String pesertaId,
                                                     @Query("apiKey") String apiKey);
}
