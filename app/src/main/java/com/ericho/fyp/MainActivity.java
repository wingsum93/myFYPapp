package com.ericho.fyp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	Button bt1,bt2,bt3,bt4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		assignC();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}
	private void assignC(){
		//assign four button
		bt1 = (Button)findViewById(R.id.activity_main_bt_customer);
		bt2 = (Button)findViewById(R.id.activity_main_bt_farmer);
		bt3 = (Button)findViewById(R.id.activity_main_bt_about);
		bt4 = (Button)findViewById(R.id.activity_main_bt_exit);
		// add listener
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
		case R.id.activity_main_bt_customer:
			i.setClass(MainActivity.this, LoginConsumerActivity.class);
			startActivity(i);
			break;
		case R.id.activity_main_bt_farmer:
			i.setClass(MainActivity.this, LoginFarmActivity.class);
			startActivity(i);
			break;
		case R.id.activity_main_bt_about:
			i.setClass(MainActivity.this, AboutActivity.class);
			startActivity(i);
			break;
		case R.id.activity_main_bt_exit:
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain);
			break;
		}
	}

}
