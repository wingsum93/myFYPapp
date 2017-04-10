package com.ericho.fyp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericho.fyp.R;
import com.ericho.fyp.datatype.FarmInTheSystem;

/**
 * Created by EricH on 10/4/2017.
 */

public class GoodListViewAdapter extends BaseAdapter {
    private Context ccon;// use for start
    private LayoutInflater mInflator;// use to draw view

    public GoodListViewAdapter(Context c) {
        // constructor
        this.ccon = c;
        mInflator = LayoutInflater.from(ccon);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View conVertView, ViewGroup arg2) {
        ViewHolder holder;
        if (conVertView == null) {
            holder = new ViewHolder();
            conVertView = mInflator.inflate(R.layout.list_view_choose_farm, null);
            holder.txv = (TextView) conVertView.findViewById(R.id.list_view_choose_farm_txv);
            holder.img = (ImageView) conVertView.findViewById(R.id.list_view_choose_farm_imgv);
            conVertView.setTag(holder);
        } else {
            holder = (ViewHolder) conVertView.getTag();
        }
        // set item content
        holder.txv.setText(FarmInTheSystem.getFarmObject(position).getName());
        holder.img.setImageResource(FarmInTheSystem.getFarmObject(position).getPictureCode());

        return conVertView;
    }

    class ViewHolder {
        TextView txv;
        ImageView img;
    }
}
