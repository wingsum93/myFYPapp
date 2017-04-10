package com.ericho.FinalYearProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ericho.datatype.Farm;
import com.ericho.datatype.FarmVegetable;
import com.ericho.datatype.FarmInTheSystem;
import com.ericho.myapi.JSONParser;
import com.ericho.myapi.Web;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConsumerChooseFarmActivity extends RxLifecycleAct implements OnItemClickListener{
	ListView listV;
	String[] farm_name = new String[]{"happy farm", "ban farm"};
	Integer[] farm_icon = new Integer[]{R.drawable.vegetable,R.drawable.banana};
	private ProgressDialog pDialog;JSONParser jParser;int productListSize;
	// JSON Node names
	public static final String TAG_SUCCESS = "success";
	public static final String TAG_PRODUCTS = "products";
	public static final String TAG_FID = "fid";
	public static final String TAG_ProductID = "product_id";
	public static final String TAG_VegetableName = "name";
	public static final String TAG_Description = "description";
	public static final String TAG_Price = "price";
	public static final String TAG_Quantity = "quantity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_choose_farm);
		assignC();initialZ();
		
	}
	private void initialZ() {
		// TODO Auto-generated method stub
		jParser=new JSONParser();
	}
	private void assignC(){
		listV = (ListView)findViewById(R.id.activity_consumer_choose_farm_list_view);
		
		//listener
		
		// do sothing
		
		GoodListViewAdapter adpt=new GoodListViewAdapter(this);
		listV.setAdapter(adpt);
		listV.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		Intent i =new Intent();
		i.setClass(this, ConsumerBuyProductActivity.class);
		Farm.farmNmae = FarmInTheSystem.getFarmObject(pos).getName();
		Farm.farmCode = Integer.toString(pos+1);
		String qq=null;
		try {
			qq=new getOneFarmAllVegetableAsyncTask().execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(getApplicationContext(), "move to next..", Toast.LENGTH_SHORT).show();
		if(qq!=null)startActivity(i);
	}
	class GoodListViewAdapter extends BaseAdapter{
		private Context ccon;// use for start 
		private LayoutInflater mInflator;// use to draw view
		GoodListViewAdapter(Context c){
			// constructor
			this.ccon=c;
			mInflator=LayoutInflater.from(ccon);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return farm_icon.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}
		@Override
		public View getView(int position, View conVertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHolder holder ;
			if(conVertView==null){
				holder = new  ViewHolder();
				conVertView= mInflator.inflate(R.layout.list_view_choose_farm,null);
				holder.txv=(TextView)conVertView.findViewById(R.id.list_view_choose_farm_txv);
				holder.img=(ImageView)conVertView.findViewById(R.id.list_view_choose_farm_imgv);
				conVertView.setTag(holder);
			}else{
				holder=(ViewHolder)conVertView.getTag();
			}
		    // set item content
			holder.txv.setText(FarmInTheSystem.getFarmObject(position).getName());
			holder.img.setImageResource(FarmInTheSystem.getFarmObject(position).getPictureCode());
		    
			return conVertView;
		}
		class ViewHolder{
			TextView txv;
			ImageView img;
		}
		

		
	}
	class getOneFarmAllVegetableAsyncTask extends AsyncTask<Void, String,String>{
		//this is a class to load the farm product thought internet
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ConsumerChooseFarmActivity.this);
			pDialog.setMessage("Loading vegetables. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(Void... arg0) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			//send farm id // it is the way to send post
			params.add(new BasicNameValuePair("fid", "1"));
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(Web.url_get_farm_all_product, "POST", params);
			
			// Check your log cat for JSON response
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					JSONArray products = json.getJSONArray(TAG_PRODUCTS);
					Farm.clearVegetableProducts();
					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String name = c.getString(TAG_VegetableName);
						String product_id = c.getString(TAG_ProductID);
						int price = c.getInt(TAG_Price);
						String unit = c.getString("unit");
						String temG=c.getString("img_name");// return the name of picture of vegetables in app
						int imgId = ConsumerChooseFarmActivity.this.getResources().getIdentifier(temG, "drawable", ConsumerChooseFarmActivity.this.getPackageName());

						

						// add each product info into a arraylist<FarmVegetable>
						FarmVegetable t=new FarmVegetable();
						t.setVegetableID(product_id);
						t.setVegetableName(name);
						t.setVegetablePrice(price);
						t.setVegetableUnit(unit);
						t.setImageCode(imgId);
						// adding HashList to ArrayList
						Farm.myFarmProduct.add(t);
					}
					productListSize = Farm.myFarmProduct.size();
				} else {
					// no products found
					// Launch Add New product Activity
					Toast.makeText(getApplication(), "fail to load product list", Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return "finish";			
		}
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			

		}
	
	}
}
