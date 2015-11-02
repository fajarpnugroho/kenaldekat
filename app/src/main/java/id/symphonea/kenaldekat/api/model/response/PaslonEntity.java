package id.symphonea.kenaldekat.api.model.response;

public final class PaslonEntity {
    public final String kind;
    public final String nama;
    public final String jk;
    public final String pob;
    public final String dob;
    public final String alamat;
    public final String pekerjaan;
    public final String status;

    public PaslonEntity(String kind, String nama, String jk, String pob, String dob, String alamat, String pekerjaan, String status) {
        this.kind = kind;
        this.nama = nama;
        this.jk = jk;
        this.pob = pob;
        this.dob = dob;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.status = status;
    }
}
