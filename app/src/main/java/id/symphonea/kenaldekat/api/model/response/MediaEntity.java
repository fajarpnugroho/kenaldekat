package id.symphonea.kenaldekat.api.model.response;

public class MediaEntity {
    public final long id;
    public final PaslonInfo paslon;
    public final SumberMedia sumber_media;
    public final String judul;
    public final String link;
    public final String content_media;

    public MediaEntity(long id, PaslonInfo paslon, SumberMedia sumber_media, String judul,
                       String link, String content_media) {
        this.id = id;
        this.paslon = paslon;
        this.sumber_media = sumber_media;
        this.judul = judul;
        this.link = link;
        this.content_media = content_media;
    }

    public static class PaslonInfo {
        public final long id;
        public final String nama_calon;
        public final String nama_wakil_calon;
        public final String qwords;

        public PaslonInfo(long id, String nama_calon, String nama_wakil_calon, String qwords) {
            this.id = id;
            this.nama_calon = nama_calon;
            this.nama_wakil_calon = nama_wakil_calon;
            this.qwords = qwords;
        }
    }

    public static class SumberMedia {
        public final long id;
        public final String link;

        public SumberMedia(long id, String link) {
            this.id = id;
            this.link = link;
        }
    }
}
