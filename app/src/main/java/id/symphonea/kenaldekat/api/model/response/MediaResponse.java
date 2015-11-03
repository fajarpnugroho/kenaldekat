package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public class MediaResponse {
    public final Data data;

    public MediaResponse(Data data) {
        this.data = data;
    }

    public static class Data {
        public final Results results;

        public Data(Results results) {
            this.results = results;
        }

        public static class Results {
            public final List<MediaEntity> rekam_jejak;
            public final int count;
            public final int total;

            public Results(List<MediaEntity> rekam_jejak, int count, int total) {
                this.rekam_jejak = rekam_jejak;
                this.count = count;
                this.total = total;
            }
        }
    }
}
