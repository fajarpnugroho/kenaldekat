package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.PaslonEntity;

public class CandidateProfileView extends FrameLayout {

    @Bind(R.id.header) TextView header;
    @Bind(R.id.nama_capil) EditText namaCapil;
    @Bind(R.id.bod) EditText bod;
    @Bind(R.id.job) EditText job;
    @Bind(R.id.address) EditText address;

    public CandidateProfileView(Context context) {
        this(context, null);
    }

    public CandidateProfileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CandidateProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_profile_capil, this);

        ButterKnife.bind(this);
    }

    public void bind(PaslonEntity paslonEntity) {
        header.setText(paslonEntity.kind.toUpperCase());
        namaCapil.setText(paslonEntity.nama);
        bod.setText(paslonEntity.pob + " / " + paslonEntity.dob);
        job.setText(paslonEntity.pekerjaan);
        address.setText(paslonEntity.alamat);
    }
}
