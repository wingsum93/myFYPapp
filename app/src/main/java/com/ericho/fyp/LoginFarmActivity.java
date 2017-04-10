package com.ericho.fyp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ericho.myapi.Login;

import java.util.concurrent.ExecutionException;

public class LoginFarmActivity extends Activity implements OnClickListener{
	EditText edt_id,edt_pw;
	Button bt1;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_farm);
		assignC();
	}
	
	public void assignC(){
		//add some com.
		edt_id = (EditText)findViewById(R.id.activity_farm_login_field_id);
		edt_pw = (EditText)findViewById(R.id.activity_farm_login_field_pw);
		bt1 = (Button)findViewById(R.id.activity_farm_login_field_bt);
		// add listener
		bt1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		switch(v.getId()){
		case R.id.activity_farm_login_field_bt:
			boolean bb = false;
			try {
				bb = new CheckFarmLoginTask().execute().get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// check login good/ wrong
			if(bb){
				i.setClass(LoginFarmActivity.this, FarmMainActivity.class);
				startActivity(i);
			}else{
				//do when id pw false
				Toast.makeText(this, "you password is wrong", Toast.LENGTH_LONG).show();
			}
			break;
		default:break;
		}
	}
	class CheckFarmLoginTask extends AsyncTask<Void,Void,Boolean>{
		ProgressDialog pDialog;
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(LoginFarmActivity.this);
			pDialog.setMessage("Saving product ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			
		}
		
		
		@Override
		protected Boolean doInBackground(Void... sth) {
			// check farm user login process
			
			boolean b=Login.checkAuthentication(edt_id.getText().toString(),edt_pw.getText().toString());
			
			// return to button listener to change page/ not
			return b;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			// dismiss the dialog once product uupdated
			
			pDialog.dismiss();
			super.onPostExecute(result);
		}
		
	}

}
