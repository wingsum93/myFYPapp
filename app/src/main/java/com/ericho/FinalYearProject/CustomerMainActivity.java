package com.ericho.FinalYearProject;

import com.ericho.datatype.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CustomerMainActivity extends Activity implements OnClickListener{
	Button bt1,bt2;TextView txv;
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_main);
		assignC();
		
	}
	private void assignC(){
		bt1 = (Button)findViewById(R.id.activity_customer_main_interface_buy);
		bt2 = (Button)findViewById(R.id.activity_customer_main_interface_upload_photo);
		txv = (TextView)findViewById(R.id.activity_customer_main_interface_txv);
		//add listener
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		txv.setText("Welcome! "+User.getUserName()+"..");
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		switch(v.getId()){
		case R.id.activity_customer_main_interface_buy:
			i.setClass(getApplicationContext(), ConsumerChooseFarmActivity.class);
			startActivity(i);
			break;
		case R.id.activity_customer_main_interface_upload_photo:
			break;
		}
	} 
}
