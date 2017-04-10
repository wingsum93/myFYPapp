package com.ericho.FinalYearProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FarmMainActivity extends Activity implements OnClickListener{
	Button bt1,bt2,bt3,bt4;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_farm_main);
		assignC();
	}

	private void assignC() {
		// TODO Auto-generated method stub
		bt1 = (Button)findViewById(R.id.activity_farm_main_interface_prepare);
		bt2 = (Button)findViewById(R.id.activity_farm_main_interface_validate);
		bt3 = (Button)findViewById(R.id.activity_farm_main_interface_get_package);
		bt4 = (Button)findViewById(R.id.activity_farm_main_interface_statistic);
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
		switch(v.getId()){
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
		
			default:break;
		}
	}
}
