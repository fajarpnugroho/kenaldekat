package id.symphonea.kenaldekat.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiConfig {

    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public static final String API_KEY = "32c16ca8817562a01035d537423a9dbc";

    public static final String PARSE_KEY = "t8EnM2Q6f99rCFLZtMezSHc59TLX32oymNOFPDmg";

    public static final String PARSE_CLIENT = "kpEJlwwacprGQSdP51CHUCBEspgrSOP5DSc2yDai";
}
