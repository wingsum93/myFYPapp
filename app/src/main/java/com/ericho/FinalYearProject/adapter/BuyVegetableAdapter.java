package com.ericho.FinalYearProject.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericho.FinalYearProject.R;
import com.ericho.datatype.Farm;

/**
 * Created by EricH on 10/4/2017.
 */

public class BuyVegetableAdapter extends BaseAdapter {
    public LayoutInflater myInflater;

    public BuyVegetableAdapter(Context c) {
        myInflater = LayoutInflater.from(c);

    }

    @Override
    public int getCount() {
        return Farm.myFarmProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return Farm.myFarmProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BuyViewHolder holder;
        //String str = dataList.get(position);
        if (convertView == null) {
            holder = new BuyViewHolder();
            convertView = myInflater.inflate(R.layout.list_sell_vegetable, null);
            //
            holder.imgv = (ImageView) convertView.findViewById(R.id.list_sell_vegetable_imgv);
            holder.txv_name = (TextView) convertView.findViewById(R.id.list_sell_vegetable_product_name);
            holder.txv_price = (TextView) convertView.findViewById(R.id.list_sell_vegetable_price);
            holder.edt = (EditText) convertView.findViewById(R.id.list_sell_vegetable_quantity);
            convertView.setTag(holder);
        } else {
            holder = (BuyViewHolder) convertView.getTag();
        }
        // set your text here!!
        if (Farm.myFarmProduct.get(position) != null) {
            String tem_name = Farm.myFarmProduct.get(position).getVegetableName() + "(" + Farm.farmNmae + ")";
            holder.txv_name.setText(tem_name);
            String iii = Integer.toString(Farm.myFarmProduct.get(position).getVegetablePrice());
            String price_and_unit = "Price: " + iii + "/" + Farm.myFarmProduct.get(position).getVegetableUnit();
            holder.txv_price.setText(price_and_unit);
            //add picture
            int iiii = Farm.myFarmProduct.get(position).getImageCode();
            holder.imgv.setImageResource(iiii);
            //holder.edt.setText(Farm.myFarmProduct.get(position).getVegetableName());
        }

        //为editText设置TextChangedListener，每次改变的值设置到hashMap
        //我们要拿到里面的值根据position拿值
        holder.edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable sss) {
                //将editText中改变的值设置的HashMap中
                double i = 0;
                if (!sss.toString().equals("") || !sss.toString().equals(".")) {
                    i = Double.parseDouble(sss.toString());
                    Farm.myFarmProduct.get(position).setQuantity(i);
                }
            }
        });

        return convertView;
    }

    private static class BuyViewHolder {
        ImageView imgv;
        TextView txv_name, txv_price;
        EditText edt;
    }
}
