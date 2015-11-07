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
import id.symphonea.kenaldekat.api.model.response.MediaEntity;

public class MediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MediaEntity> mediaEntityList;
    private Listener listener;

    public MediaAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setMediaEntityList(List<MediaEntity> mediaEntityList) {
        this.mediaEntityList = mediaEntityList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleViewHolder) {
            ArticleViewHolder viewHolder = (ArticleViewHolder) holder;
            viewHolder.bind(mediaEntityList.get(position));
        }
    }

    public void addMoreList(List<MediaEntity> mediaEntities) {
        mediaEntityList.addAll(mediaEntities);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mediaEntityList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@LayoutRes int resId, ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }
    }

    public static class ArticleViewHolder extends Holder implements View.OnClickListener {

        @Bind(R.id.sumber) TextView sumberText;
        @Bind(R.id.judul) TextView judulText;
        @Bind(R.id.excerpt) TextView excerptText;

        private Listener listener;

        public ArticleViewHolder(ViewGroup parent, Listener listener) {
            super(R.layout.item_view_media, parent);

            ButterKnife.bind(this, itemView);

            this.listener = listener;
        }

        public void bind(MediaEntity mediaEntity) {
            sumberText.setText(mediaEntity.sumber_media.link);
            judulText.setText(mediaEntity.judul);
            excerptText.setText(mediaEntity.content_media);

            itemView.setOnClickListener(this);
            itemView.setTag(mediaEntity.link);
        }

        @Override
        public void onClick(View v) {
            String url = (String) v.getTag();
            listener.openUrlToBrowser(url);
        }
    }

    public interface Listener {
        void openUrlToBrowser(String url);
    }
}
