package id.symphonea.kenaldekat.presenter;

import android.app.Application;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.symphonea.kenaldekat.KenalDekatApp;
import id.symphonea.kenaldekat.api.model.response.DukunganEntity;
import id.symphonea.kenaldekat.api.model.response.DukunganResponse;
import id.symphonea.kenaldekat.util.AssetUtils;
import id.symphonea.kenaldekat.view.DukunganView;
import timber.log.Timber;

@Singleton
public class DukunganPresenter {

    public static final String UNEXPECTED_ERROR = "Unexpected error";
    public static final String READER_ALREADY_BEEN_CLOSED = "Reader already been closed";

    private DukunganView view;
    private KenalDekatApp application;

    @Inject
    public DukunganPresenter(KenalDekatApp application) {
        this.application = application;
    }


    public void initView(DukunganView view) {
        this.view = view;
    }

    public void loadDukunganJson() {
        // read list provinces from assets

        Reader reader = null;
        try {
            reader = AssetUtils.readFile(application, "dukungan.json");
            DukunganResponse response =
                    new Gson().fromJson(
                            reader,
                            DukunganResponse.class);
            view.showListDukungan(response);

        } catch (IOException e) {
            throw new RuntimeException(UNEXPECTED_ERROR);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Timber.e(READER_ALREADY_BEEN_CLOSED);
            }
        }
    }

    public void postDukungan(int ratingNum, String dukungan) {
        DukunganEntity dukunganEntity = new DukunganEntity(3, "Test User", ratingNum, dukungan, "2015-07-9");
        view.addedDukungan(dukunganEntity);
    }
}
