package com.ericho.FinalYearProject;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AboutActivity extends Activity implements OnClickListener{
	Button bt1;
	EditText edt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		assignC();
	}
	private void assignC(){
		// find view
		bt1 = (Button)findViewById(R.id.activity_about_bt1);
		edt1 = (EditText)findViewById(R.id.activity_about_edt1);
		// set listener
		bt1.setOnClickListener(this);
		edt1.setEnabled(false);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.activity_about_bt1:
			AboutActivity.this.finish();
			break;
		}
	}
}
