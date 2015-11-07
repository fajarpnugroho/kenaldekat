package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public final class CandidatesResponse {
    public final Data data;

    public CandidatesResponse(Data data) {
        this.data = data;
    }

    public static class Data {

        public final Results results;

        public Data(Results results) {
            this.results = results;
        }

        public static final class Results {
            public final int count;
            public final int total;
            public final List<CandidateEntity> candidates;

            public Results(int count, int total, List<CandidateEntity> candidates) {
                this.count = count;
                this.total = total;
                this.candidates = candidates;
            }
        }
    }
}
