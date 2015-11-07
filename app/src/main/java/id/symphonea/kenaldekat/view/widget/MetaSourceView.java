package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;

public class MetaSourceView extends FrameLayout {

    @Bind(R.id.source) TextView sourceText;
    @Bind(R.id.date) TextView dateText;

    public MetaSourceView(Context context) {
        this(context, null);
    }

    public MetaSourceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetaSourceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_meta_source, this);

        ButterKnife.bind(this);
    }

    public void bind(String souce, String date) {
        sourceText.setText(souce);
        dateText.setText(date);
    }
}
