package id.symphonea.kenaldekat.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.ProvincesResponse;
import id.symphonea.kenaldekat.api.model.response.ProvinsiEntity;

public class ProvincesAdapter extends BaseAdapter {

    private ProvincesResponse response;
    private LayoutInflater layoutInflater;

    public ProvincesAdapter(Context context, ProvincesResponse response) {
        this.response = response;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return response.data.size() + 1;
    }

    @Override
    public ProvinsiEntity getItem(int position) {
        return position == 0 ? null : response.data.get(position - 1);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.item_view_provinces, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bind(getItem(position));

        return convertView;
    }



    public class ViewHolder {

        @Bind(R.id.label) TextView label;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bind(ProvinsiEntity entity) {
            if (entity == null) {
                label.setText(R.string.label_default_provinsi);
            } else {
                label.setText(entity.nama);
            }

            label.setTag(entity);
        }
    }
}
