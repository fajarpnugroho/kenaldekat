package id.symphonea.kenaldekat.api.model.response;

public final class NewsEntity {
    public final long id;
    public final Candidate candidate;
    public final Resource resource;
    public final String date;
    public final String link;
    public final String description;

    public NewsEntity(long id, Candidate candidate, Resource resource, String date, String link,
                      String description) {
        this.id = id;
        this.candidate = candidate;
        this.resource = resource;
        this.date = date;
        this.link = link;
        this.description = description;
    }

    public static class Candidate {
        public final long id;
        public final String name;

        public Candidate(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Resource {
        public final long id;
        public final String name;

        public Resource(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
