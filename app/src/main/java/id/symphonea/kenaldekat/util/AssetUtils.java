package id.symphonea.kenaldekat.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class AssetUtils {

    private AssetUtils() {}

    public static Reader readFile(Context context, String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        return new InputStreamReader(inputStream, "utf-8");
    }

}
