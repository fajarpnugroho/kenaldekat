package id.symphonea.kenaldekat.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.NewsEntity;
import id.symphonea.kenaldekat.api.model.response.NewsResponse;
import id.symphonea.kenaldekat.util.StringUtils;
import id.symphonea.kenaldekat.view.widget.MetaSourceView;

public class BeritaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsEntity> newsEntities;
    private Listener listener;

    public BeritaAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setNewsEntities(List<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeritaViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BeritaViewHolder) {
            BeritaViewHolder viewHolder = (BeritaViewHolder) holder;
            viewHolder.bind(newsEntities.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return newsEntities.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@LayoutRes int resId, ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }
    }

    public static class BeritaViewHolder extends Holder implements View.OnClickListener {

        @Bind(R.id.thumbnail) ImageView thumbnail;
        @Bind(R.id.description) TextView descText;
        @Bind(R.id.meta_source) MetaSourceView sourceView;

        private Listener listener;

        public BeritaViewHolder(ViewGroup parent, Listener listener) {
            super(R.layout.item_view_berita, parent);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
        }

        public void bind(NewsEntity newsEntity) {
            String imageUrl = StringUtils.getYoutubeThumbnail(newsEntity.link);

            // quick return
            if (imageUrl == null) {
                itemView.setVisibility(View.GONE);
                return;
            }

            Picasso.with(itemView.getContext())
                    .load(imageUrl)
                    .into(thumbnail);


            descText.setText(newsEntity.description);
            sourceView.bind(newsEntity.resource.name, newsEntity.date);

            itemView.setTag(newsEntity.link);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String link = (String) v.getTag();
            listener.onItemClickListener(link);
        }
    }

    public interface Listener {
        void onItemClickListener(String link);
    }
}
