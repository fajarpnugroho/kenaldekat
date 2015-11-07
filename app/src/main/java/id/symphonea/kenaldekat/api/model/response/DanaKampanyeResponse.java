package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public final class DanaKampanyeResponse {
    public final Data data;

    public DanaKampanyeResponse(Data data) {
        this.data = data;
    }

    public static class Data {
        public Results results;

        public Data(Results results) {
            this.results = results;
        }

        public static class Results {
            public final int count;
            public final int total;
            public final List<DanaKampanyeEntity> danakampanye;

            public Results(int count, int total, List<DanaKampanyeEntity> danakampanye) {
                this.count = count;
                this.total = total;
                this.danakampanye = danakampanye;
            }
        }
    }
}
