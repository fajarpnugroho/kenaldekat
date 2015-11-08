package id.symphonea.kenaldekat.api.model.response;

public final class DukunganEntity {
    public final long id;
    public final String nama;
    public final int rating;
    public final String dukungan;
    public final String createAt;

    public DukunganEntity(long id, String nama, int rating, String dukungan, String createAt) {
        this.id = id;
        this.nama = nama;
        this.rating = rating;
        this.dukungan = dukungan;
        this.createAt = createAt;
    }
}
