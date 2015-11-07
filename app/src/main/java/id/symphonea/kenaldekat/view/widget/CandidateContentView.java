package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;

public class CandidateContentView extends FrameLayout {

    @Bind(R.id.key) TextView keyText;
    @Bind(R.id.value) TextView valueText;

    public CandidateContentView(Context context) {
        this(context, null);
    }

    public CandidateContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CandidateContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_candidate, this);

        ButterKnife.bind(this);
    }

    public void bind(String key, String value) {
        keyText.setText(key);
        valueText.setText(value);
    }
}
