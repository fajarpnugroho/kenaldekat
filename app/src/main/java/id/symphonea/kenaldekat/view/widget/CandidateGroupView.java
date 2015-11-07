package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;

public class CandidateGroupView extends FrameLayout {

    @Bind(R.id.header) TextView headerText;
    @Bind(R.id.info) LinearLayout infoRoot;

    public CandidateGroupView(Context context) {
        this(context, null);
    }

    public CandidateGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CandidateGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_candidate_group, this);

        ButterKnife.bind(this);
    }

    public void bind(String header, List<CandidateContentView> child) {
        headerText.setText(header);

        for (CandidateContentView view : child) {
            infoRoot.addView(view);
        }

    }
}
