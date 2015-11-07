package id.symphonea.kenaldekat.util;

import android.net.Uri;
import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.Locale;

public final class StringUtils {
    public StringUtils() {}

    public static String getYoutubeThumbnail(String url) {
        if (!url.toLowerCase().contains("youtube")) {
            return null;
        }

        Uri convUrl = Uri.parse(url);

        String youtubeId = convUrl.getQueryParameter("v");

        if (TextUtils.isEmpty(youtubeId)) {
            return null;
        }

        return "http://img.youtube.com/vi/" + youtubeId + "/sddefault.jpg";
    }

    public static String currencyFormat(String nominal) {
        if (nominal.equals("Tidak diset")) {
            nominal = "0";
        }

        String rupiah = "";

        NumberFormat rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);

        rupiah = rupiahFormat.format(Double.parseDouble(nominal));

        return "Rp " + rupiah + ", -";
    }
}
