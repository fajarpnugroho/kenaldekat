package id.symphonea.kenaldekat.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import timber.log.Timber;

public class UrlUtils {
    public static void setUrlHandler(Spannable textWithUrls) {

        URLSpan[] spans = textWithUrls.getSpans(0, textWithUrls.length(), URLSpan.class);

        for (URLSpan span : spans) {
            int start = textWithUrls.getSpanStart(span);
            int end = textWithUrls.getSpanEnd(span);

            textWithUrls.removeSpan(span);

            URLSpan newSpan = new URLSpanHandler(span.getURL());

            textWithUrls.setSpan(newSpan, start, end, 0);
        }
    }

    static class URLSpanHandler extends URLSpan {
        private static final String TAG = "URLSpanHandler";

        public URLSpanHandler(String url) {
            super(url);
        }

        @Override
        public void onClick(View widget) {
            Context context = widget.getContext();

            if (!Patterns.WEB_URL.matcher(getURL()).matches()) {
                Toast.makeText(context, "Invalid URL",
                        Toast.LENGTH_SHORT).show();
                Timber.i(TAG, "Invalid URL : " + getURL());
                return;
            }

            Uri uri = Uri.parse(getURL());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }
}
