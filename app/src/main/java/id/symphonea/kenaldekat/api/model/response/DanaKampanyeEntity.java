package id.symphonea.kenaldekat.api.model.response;

public final class DanaKampanyeEntity {
    public final long id;
    public final long id_participant;
    public final String nama_calon;
    public final String penerimaan;
    public final String pengeluaran_operasi;
    public final String pengeluaran_modal;
    public final String pengeluaran_lain;
    public final String saldo;
    public final String kas_rekening_khusus;
    public final String kas_bendahara;
    public final String barang;


    public DanaKampanyeEntity(long id, long id_participant, String nama_calon, String penerimaan,
                              String pengeluaran_operasi, String pengeluaran_modal,
                              String pengeluaran_lain, String saldo, String kas_rekening_khusus,
                              String kas_bendahara, String barang) {
        this.id = id;
        this.id_participant = id_participant;
        this.nama_calon = nama_calon;
        this.penerimaan = penerimaan;
        this.pengeluaran_operasi = pengeluaran_operasi;
        this.pengeluaran_modal = pengeluaran_modal;
        this.pengeluaran_lain = pengeluaran_lain;
        this.saldo = saldo;
        this.kas_rekening_khusus = kas_rekening_khusus;
        this.kas_bendahara = kas_bendahara;
        this.barang = barang;
    }
}
