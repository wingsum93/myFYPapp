package com.ericho.FinalYearProject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ericho.myapi.JSONParser;
import com.ericho.myapi.Web;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewProductActivity extends RxLifecycleAct {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
    @BindView(R.id.inputName)
    EditText inputName;
    @BindView(R.id.inputPrice)
    EditText inputPrice;
    @BindView(R.id.inputDesc)
    EditText inputDesc;
    @BindView(R.id.btnCreateProduct)
    Button btnCreateProduct;
    // JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_product);

        ButterKnife.bind(this);


		// button click event
		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});
		new BeforeCreateNewProduct().execute();
		
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewProductActivity.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String name = inputName.getText().toString();
			String price = inputPrice.getText().toString();
			String description = inputDesc.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("price", price));
			params.add(new BasicNameValuePair("description", description));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(Web.url_create_product,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
	class BeforeCreateNewProduct extends AsyncTask<Void , String, String>{
		
		ProgressDialog myd;
		
		@Override
		public void onPreExecute(){
			super.onPreExecute();
			myd = new ProgressDialog(NewProductActivity.this);
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
