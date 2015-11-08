package id.symphonea.kenaldekat.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.DukunganEntity;
import id.symphonea.kenaldekat.view.DukunganView;

public class DukunganAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DukunganEntity> dukunganEntityList;

    public void setDukunganEntityList(List<DukunganEntity> dukunganEntityList) {
        this.dukunganEntityList = dukunganEntityList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DukunganViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DukunganViewHolder) {
            DukunganViewHolder viewHolder = (DukunganViewHolder) holder;
            viewHolder.bind(dukunganEntityList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dukunganEntityList.size();
    }

    public void addNewData(DukunganEntity dukunganEntity) {
        dukunganEntityList.add(dukunganEntity);
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@LayoutRes int resId, ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }
    }

    public static class DukunganViewHolder extends Holder {

        @Bind(R.id.nama)
        TextView namaText;
        @Bind(R.id.rating)
        RatingBar ratingUser;
        @Bind(R.id.dukungan_text) TextView dukungan;

        public DukunganViewHolder(ViewGroup parent) {
            super(R.layout.item_view_dukungan, parent);
            ButterKnife.bind(this, itemView);
        }

        public void bind(DukunganEntity entity) {
            namaText.setText(entity.nama);
            ratingUser.setRating(entity.rating);
            ratingUser.setFocusable(false);
            dukungan.setText(entity.dukungan);
        }
    }
}
