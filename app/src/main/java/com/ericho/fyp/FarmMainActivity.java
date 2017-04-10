package com.ericho.fyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FarmMainActivity extends Activity implements OnClickListener {
    @BindView(R.id.activity_farm_main_interface_prepare)
    Button bt1;
    @BindView(R.id.activity_farm_main_interface_validate)
    Button bt2;
    @BindView(R.id.activity_farm_main_interface_get_package)
    Button bt3;
    @BindView(R.id.activity_farm_main_interface_statistic)
    Button bt4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_main);
        assignC();
    }

    private void assignC() {
        ButterKnife.bind(this);
        //add lsistener
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.activity_farm_main_interface_prepare:
                break;
            case R.id.activity_farm_main_interface_validate:
                i.setClass(FarmMainActivity.this, FarmViewTransactionActivity.class);
                startActivity(i);
                break;
            case R.id.activity_farm_main_interface_get_package:
                break;
            case R.id.activity_farm_main_interface_statistic:
                break;

            default:
                break;
        }
    }
}
