package id.symphonea.kenaldekat.util;

import android.net.Uri;
import android.text.TextUtils;

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
}
