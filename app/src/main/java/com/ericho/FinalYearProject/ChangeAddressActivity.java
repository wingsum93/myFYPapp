package com.ericho.FinalYearProject;



import com.ericho.myapi.Web;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeAddressActivity extends Activity implements OnClickListener{
	Button makeSure;
	EditText add,portN;
	TextView t1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_address);
		makeSure = (Button) findViewById(R.id.ch_btn1);
		add = (EditText) findViewById(R.id.ch_edt1);
		portN = (EditText) findViewById(R.id.ch_edt2);
		// set listener
		
		makeSure.setOnClickListener(this);
		new BeforeCreateNewProduct().execute();
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String temp = "";
		temp+=add.getText().toString();
		temp+=":";
		temp+=portN.getText().toString();
		// stage 2
		try{	
			Web.setAddress(temp);
			Toast.makeText(getApplication(), "change succes", Toast.LENGTH_SHORT).show();
		}catch (Exception ee){
			Log.e("change server address", "wrong");
			Toast.makeText(getApplication(), "wrong", Toast.LENGTH_SHORT).show();
		}
		
	}
	class BeforeCreateNewProduct extends AsyncTask<Void , String, String>{
		ProgressDialog myd;
		
		@Override
		public void onPreExecute(){
			super.onPreExecute();
			myd = new ProgressDialog(ChangeAddressActivity.this);
			myd.setTitle("you good guy!");
			myd.setMessage("in betwween.");
			myd.setCancelable(false);
			myd.setIndeterminate(false);
			myd.show();
			
		}
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				Log.e("error message", "sth wrong.");
			}
			
			return null;
			
		}
		@Override
		protected void onPostExecute(String aa){
			super.onPostExecute(aa);
			myd.dismiss(); 

		}
		
		
	}
	
	
	
}