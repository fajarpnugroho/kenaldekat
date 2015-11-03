package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public final class VisionMissionEntity {
    public final long id;
    public final long candidate_id;
    public final List<PaslonEntity> paslon;
    public final String visi;
    public final String misi;
    public final String sumber;

    public VisionMissionEntity(long id, long candidate_id, List<PaslonEntity> paslon, String visi,
                               String misi, String sumber) {
        this.id = id;
        this.candidate_id = candidate_id;
        this.paslon = paslon;
        this.visi = visi;
        this.misi = misi;
        this.sumber = sumber;
    }
}
