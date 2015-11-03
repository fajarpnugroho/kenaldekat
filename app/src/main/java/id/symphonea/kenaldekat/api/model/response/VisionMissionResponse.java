package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public final class VisionMissionResponse {
    public final Data data;

    public VisionMissionResponse(Data data) {
        this.data = data;
    }

    public static class Data {
        public final Results results;

        public Data(Results results) {
            this.results = results;
        }

        public static class Results {
            public final int count;
            public final int total;
            public final List<VisionMissionEntity> vision_missions;

            public Results(int count, int total, List<VisionMissionEntity> vision_missions) {
                this.count = count;
                this.total = total;
                this.vision_missions = vision_missions;
            }
        }
    }
}
