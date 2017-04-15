package com.ericho.fyp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericho.fyp.R;
import com.ericho.fyp.datatype.ServerBean;
import com.ericho.util.DrawableUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by EricH on 2/12/2016.
 */

public class ServerUrlAdapter extends BaseObjectAdapter<ServerBean> {
    public ServerUrlAdapter(Context context, List<ServerBean> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ServerBean i = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_change_server, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Drawable drawable = DrawableUtil.getDrawble(getContext(), i.getResourceInteger());
        holder.imageView.setImageDrawable(drawable);
        holder.text1.setText(i.getDisplayName());


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        protected ImageView imageView;
        @BindView(R.id.text1)
        protected TextView text1;

        ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
