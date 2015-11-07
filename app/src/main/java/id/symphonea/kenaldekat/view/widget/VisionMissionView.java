package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;

public class VisionMissionView extends FrameLayout {

    public static final String TIDAK_TERCANTUM = "Tidak tercantum";
    @Bind(R.id.visi) EditText vision;
    @Bind(R.id.misi) EditText mission;

    public VisionMissionView(Context context) {
        this(context, null);
    }

    public VisionMissionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VisionMissionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_visi_misi, this);
        ButterKnife.bind(this);
    }

    public void bind(String visiString, String misiString) {
        if (visiString == null) {
            vision.setTextColor(ContextCompat.getColor(getContext(), android.R.color.darker_gray));
            vision.setText(TIDAK_TERCANTUM);
        } else {
            vision.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
            vision.setText(visiString);
        }

        if (misiString == null) {
            mission.setTextColor(ContextCompat.getColor(getContext(), android.R.color.darker_gray));
            mission.setText(TIDAK_TERCANTUM);
        } else {
            mission.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
            mission.setText(misiString);
        }
    }
}
