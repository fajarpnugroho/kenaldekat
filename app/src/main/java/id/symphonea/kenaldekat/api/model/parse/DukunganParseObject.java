/*package id.symphonea.kenaldekat.api.model.parse;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class DukunganParseObject extends ParseObject {
    public String KEY_USER_ID = "userId";
    public String KEY_CREATED_AT = "createdAt";
    public String KEY_KOMENTAR = "komentar";
    public String KEY_RATING = "rating";

    public DukunganParseObject() {}

    public ParseQuery<DukunganParseObject> getQuery() {
        return ParseQuery.getQuery(DukunganParseObject.class);
    }

    public void setUserId(ParseUser user) {
        put(KEY_USER_ID, user);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER_ID);
    }

    public void setKomentar(String komentar) {
        put(KEY_KOMENTAR, komentar);
    }

    public String getKomentar() {
        return getString(KEY_KOMENTAR);
    }

    public void setRating(int rating) {
        put(KEY_RATING, rating);
    }

    public int getRating() {
        return getInt(KEY_RATING);
    }
} */
