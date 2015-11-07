package id.symphonea.kenaldekat.api.model.response;

import java.util.List;

public final class CandidateEntity {
    public final long id;
    public final ProvinsiEntity provinsi;
    public final DaerahEntity daerah;
    public final long id_peserta;
    public final List<PaslonEntity> paslon;
    public final String jenis_dukungan;
    public final String dukungan;
    public final String pilihan_suara;
    public final String status_penerimaan;
    public final String kelengkapan_dokumen;
    public final String hasil_penelitian;
    public final String penerimaan_dokumen_perbaikan;
    public final String jumlah_dukungan_awal;
    public final String jumlah_dukungan_perbaikan;
    public final String jumlah_dukungan_penetapan;
    public final String pemenuhan_syarat_dukungan;
    public final String pemenuhan_syarat_dukungan_perbaikan;
    public final String pertahana;
    public final String dinasti;
    public final String perempuan;
    public final String incumbent;
    public final String visi;
    public final String misi;
    public final String sumber;

    public CandidateEntity(long id, ProvinsiEntity provinsi, DaerahEntity daerah, long id_peserta,
                           List<PaslonEntity> paslon, String jenis_dukungan, String dukungan,
                           String pilihan_suara, String status_penerimaan,
                           String kelengkapan_dokumen, String hasil_penelitian,
                           String penerimaan_dokumen_perbaikan, String jumlah_dukungan_awal,
                           String jumlah_dukungan_perbaikan, String jumlah_dukungan_penetapan,
                           String pemenuhan_syarat_dukungan,
                           String pemenuhan_syarat_dukungan_perbaikan, String pertahana,
                           String dinasti, String perempuan, String incumbent, String visi,
                           String misi, String sumber) {
        this.id = id;
        this.provinsi = provinsi;
        this.daerah = daerah;
        this.id_peserta = id_peserta;
        this.paslon = paslon;
        this.jenis_dukungan = jenis_dukungan;
        this.dukungan = dukungan;
        this.pilihan_suara = pilihan_suara;
        this.status_penerimaan = status_penerimaan;
        this.kelengkapan_dokumen = kelengkapan_dokumen;
        this.hasil_penelitian = hasil_penelitian;
        this.penerimaan_dokumen_perbaikan = penerimaan_dokumen_perbaikan;
        this.jumlah_dukungan_awal = jumlah_dukungan_awal;
        this.jumlah_dukungan_perbaikan = jumlah_dukungan_perbaikan;
        this.jumlah_dukungan_penetapan = jumlah_dukungan_penetapan;
        this.pemenuhan_syarat_dukungan = pemenuhan_syarat_dukungan;
        this.pemenuhan_syarat_dukungan_perbaikan = pemenuhan_syarat_dukungan_perbaikan;
        this.pertahana = pertahana;
        this.dinasti = dinasti;
        this.perempuan = perempuan;
        this.incumbent = incumbent;
        this.visi = visi;
        this.misi = misi;
        this.sumber = sumber;
    }
}
