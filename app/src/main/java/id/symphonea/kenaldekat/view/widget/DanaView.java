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
import id.symphonea.kenaldekat.api.model.response.DanaKampanyeEntity;
import id.symphonea.kenaldekat.util.StringUtils;

public class DanaView extends FrameLayout {

    public static final String TIDAK_TERCANTUM = "Tidak tercantum";
    @Bind(R.id.penerimaan) EditText penerimaan;
    @Bind(R.id.operasi) EditText operasi;
    @Bind(R.id.modal) EditText modal;
    @Bind(R.id.lain) EditText lain;
    @Bind(R.id.saldo) EditText saldo;
    @Bind(R.id.kas_rek_khusus) EditText kasKhusus;
    @Bind(R.id.kas_bendahara) EditText kasBendahara;
    @Bind(R.id.barang) EditText barang;

    public DanaView(Context context) {
        this(context, null);
    }

    public DanaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_dana, this);
        ButterKnife.bind(this);
    }

    public void bind(DanaKampanyeEntity danaKampanyeEntity) {
        setPenerimaan(danaKampanyeEntity.penerimaan);
        setOperasi(danaKampanyeEntity.pengeluaran_operasi);
        setModal(danaKampanyeEntity.pengeluaran_modal);
        setLain(danaKampanyeEntity.pengeluaran_lain);
        setSaldo(danaKampanyeEntity.saldo);
        setKasKhusus(danaKampanyeEntity.kas_rekening_khusus);
        setKasBendahara(danaKampanyeEntity.kas_bendahara);
        setBarang(danaKampanyeEntity.barang);
    }

    private void setBarang(String barangString) {
        if (barangString == null) {
            barang.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            barang.setText(TIDAK_TERCANTUM);
        } else {
            barang.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            barang.setText(barangString);
        }
    }

    private void setKasBendahara(String kas_bendahara) {
        if (kas_bendahara == null) {
            kasBendahara.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            kasBendahara.setText(TIDAK_TERCANTUM);
        } else {
            kasBendahara.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            kasBendahara.setText(StringUtils.currencyFormat(kas_bendahara));
        }
    }

    private void setKasKhusus(String kas_rekening_khusus) {
        if (kas_rekening_khusus == null) {
            kasKhusus.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            kasKhusus.setText(TIDAK_TERCANTUM);
        } else {
            kasKhusus.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            kasKhusus.setText(StringUtils.currencyFormat(kas_rekening_khusus));
        }
    }

    private void setSaldo(String saldoString) {
        if (saldoString == null) {
            saldo.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            saldo.setText(TIDAK_TERCANTUM);
        } else {
            saldo.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            saldo.setText(StringUtils.currencyFormat(saldoString));
        }
    }

    private void setLain(String pengeluaran_lain) {
        if (pengeluaran_lain == null) {
            lain.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            lain.setText(TIDAK_TERCANTUM);
        } else {
            lain.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            lain.setText(StringUtils.currencyFormat(pengeluaran_lain));
        }
    }

    private void setModal(String pengeluaran_modal) {
        if (pengeluaran_modal == null) {
            modal.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            modal.setText(TIDAK_TERCANTUM);
        } else {
            modal.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            modal.setText(StringUtils.currencyFormat(pengeluaran_modal));
        }
    }

    private void setOperasi(String pengeluaran_operasi) {
        if (pengeluaran_operasi == null) {
            operasi.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            operasi.setText(TIDAK_TERCANTUM);
        } else {
            operasi.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            operasi.setText(StringUtils.currencyFormat(pengeluaran_operasi));
        }
    }

    public void setPenerimaan(String penerimaanString) {
        if (penerimaanString == null) {
            penerimaan.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.darker_gray));
            penerimaan.setText(TIDAK_TERCANTUM);
        } else {
            penerimaan.setTextColor(ContextCompat.getColor(getContext(),
                    android.R.color.black));
            penerimaan.setText(StringUtils.currencyFormat(penerimaanString));
        }
    }
}
