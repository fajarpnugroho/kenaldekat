package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public final class NewsResponse {
    public final Data data;

    public NewsResponse(Data data) {
        this.data = data;
    }

    public static class Data {
        public final Results results;

        public Data(Results results) {
            this.results = results;
        }
    }

    public static class Results {
        public final List<NewsEntity> news;

        public Results(List<NewsEntity> news) {
            this.news = news;
        }
    }
}
