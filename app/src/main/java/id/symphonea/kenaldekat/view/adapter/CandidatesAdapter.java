package id.symphonea.kenaldekat.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.CandidateEntity;
import id.symphonea.kenaldekat.api.model.response.PaslonEntity;

public class CandidatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CandidateEntity> candidates;
    private Listener listener;

    public CandidatesAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setCandidatesResponse(List<CandidateEntity> candidates) {
        this.candidates = candidates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            TextViewHolder viewHolder = (TextViewHolder) holder;
            viewHolder.bind(candidates.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public void addedMoreList(List<CandidateEntity> candidates) {
        this.candidates.addAll(candidates);
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@LayoutRes int resId, ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }
    }

    public static class TextViewHolder extends Holder implements View.OnClickListener {

        @Bind(R.id.provinsi) TextView provinsi;
        @Bind(R.id.label_name_capil) TextView nameCapil;
        @Bind(R.id.label_name_wapil) TextView nameWapil;
        @Bind(R.id.dukungan) TextView dukungan;

        private Listener listener;

        public TextViewHolder(ViewGroup parent, Listener listener) {
            super(R.layout.item_view_candidates, parent);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
        }

        public void bind(CandidateEntity candidateEntity) {
            PaslonEntity capil = candidateEntity.paslon.get(0);
            PaslonEntity wapil = candidateEntity.paslon.get(1);

            provinsi.setText(candidateEntity.provinsi.nama + " / " + candidateEntity.daerah.nama);

            nameCapil.setText(capil.nama);
            nameWapil.setText(wapil.nama);

            dukungan.setText(candidateEntity.dukungan);

            itemView.setTag(candidateEntity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CandidateEntity candidateEntity = (CandidateEntity) v.getTag();
            listener.onItemClickListener(String.valueOf(candidateEntity.id_peserta),
                    candidateEntity.sumber, candidateEntity.provinsi.nama);
        }
    }

    public interface Listener {
        void onItemClickListener(String pesertaId, String url, String provinsi);
    }
}
