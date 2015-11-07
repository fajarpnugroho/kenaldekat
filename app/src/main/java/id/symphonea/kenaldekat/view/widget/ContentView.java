package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.CandidateEntity;

public class ContentView extends FrameLayout {

    @Bind(R.id.key) TextView keyText;
    @Bind(R.id.value) TextView valueText;

    public ContentView(Context context) {
        this(context, null);
    }

    public ContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_content, this);

        ButterKnife.bind(this);
    }

    public void bind(String key, String value) {
        keyText.setText(key);

        if (TextUtils.isEmpty(value)) {
            value = "-";
        }

        valueText.setMovementMethod(LinkMovementMethod.getInstance());
        valueText.setText(value);
    }
}
