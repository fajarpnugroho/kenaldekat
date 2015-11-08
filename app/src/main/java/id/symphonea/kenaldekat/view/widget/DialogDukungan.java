package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;

public class DialogDukungan extends FrameLayout {

    @Bind(R.id.rating) RatingBar rating;
    @Bind(R.id.dukungan) EditText dukungan;

    private Listener listener;

    public DialogDukungan(Context context) {
        this(context, null);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public DialogDukungan(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogDukungan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.dialog_dukungan, this);

        ButterKnife.bind(this);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int ratingNum = (int) rating;

                if (listener == null) return;

                listener.setMyRating(ratingNum);
            }
        });

        dukungan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (listener == null) return;

                listener.setMyDukungan(dukungan.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public interface Listener {
        void setMyRating(int ratingNum);

        void setMyDukungan(String dukungan);
    }
}
