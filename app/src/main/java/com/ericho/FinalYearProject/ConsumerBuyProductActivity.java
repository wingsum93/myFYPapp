package com.ericho.FinalYearProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ericho.datatype.Farm;
import com.ericho.datatype.User;
import com.ericho.myapi.JSONParser;
import com.ericho.myapi.Web;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ConsumerBuyProductActivity extends Activity implements OnClickListener{
	Button bt1,bt2,bt3;ListView mList;TextView mTxv;TextView txv_name;
	//String farm_id;//this is to identify the farm no. start from 1-n.
	ProgressDialog pDialog;BaseAdapter adapter;
	JSONArray products;
	private String P_fid ="f_id",P_cid="c_id",P_total="t_total",P_pro1="pro1",P_pro2="pro2",P_pro3="pro3",P_pro4="pro4",P_pro5="pro5",P_pro6="pro6";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_buy_product);
		
		assignC();
	}

	private void assignC() {
		setTitle("Buy Vegetables Pages");
		// TODO Auto-generated method stub
		txv_name = (TextView)findViewById(R.id.activity_consumer_buy_product_txv_consumer_name);
		bt1 = (Button)findViewById(R.id.activity_consumer_buy_product_bt1);
		bt2 = (Button)findViewById(R.id.activity_consumer_buy_product_bt2);
		bt3 = (Button)findViewById(R.id.activity_consumer_buy_product_bt3);
		mList = (ListView)findViewById(R.id.activity_consumer_buy_product_listview);
		
		//add lsitener
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		//set text of textview 
		txv_name.setText(User.getUserName());
		String tet = Farm.farmNmae;
		String farm_id = Farm.farmCode;
				
		// first get productlist then set adapter			
		
		adapter = new BuyVegetableAdapter(ConsumerBuyProductActivity.this);
		mList.setAdapter(adapter);
		mList.requestFocus();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.activity_consumer_buy_product_bt1:
			// when user do transaction **
			new sendOrderAsyncTask().execute();
			break;
		case R.id.activity_consumer_buy_product_bt2:
			Farm.setAllQuantityToZero();
			mList.setAdapter(new BuyVegetableAdapter(ConsumerBuyProductActivity.this));
			break;
		case R.id.activity_consumer_buy_product_bt3:
			//this is a cancel button
			this.finish();
			break;
		default:break;
		}
	}

	
	
	
	class BuyVegetableAdapter extends BaseAdapter{
		public LayoutInflater myInflater;
		BuyVegetableAdapter(Context c){
			myInflater=LayoutInflater.from(c);
			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Farm.myFarmProduct.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return Farm.myFarmProduct.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			BuyViewHolder holder;
			//String str = dataList.get(position);  
            if(convertView==null){
            	holder = new BuyViewHolder();
            	convertView = myInflater.inflate(R.layout.list_sell_vegetable, null);  
            	//
            	holder.imgv = (ImageView)convertView.findViewById(R.id.list_sell_vegetable_imgv);
            	holder.txv_name = (TextView)convertView.findViewById(R.id.list_sell_vegetable_product_name);
            	holder.txv_price = (TextView)convertView.findViewById(R.id.list_sell_vegetable_price);
            	holder.edt = (EditText)convertView.findViewById(R.id.list_sell_vegetable_quantity);  
            	convertView.setTag(holder);
            }else{
            	holder = (BuyViewHolder)convertView.getTag();
            }
            // set your text here!!
            if(Farm.myFarmProduct.get(position) != null){  
            	String tem_name = Farm.myFarmProduct.get(position).getVegetableName() + "(" + Farm.farmNmae +")";
            	holder.txv_name.setText(tem_name);  
            	String iii = Integer.toString(Farm.myFarmProduct.get(position).getVegetablePrice());
            	String price_and_unit = "Price: "+iii + "/"+Farm.myFarmProduct.get(position).getVegetableUnit();
            	holder.txv_price.setText(price_and_unit);
            	//add picture
            	int iiii= Farm.myFarmProduct.get(position).getImageCode();
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
                        int count,int after) {  
                      
                }  
                  
                @Override  
                public void afterTextChanged(Editable sss) {  
                    //将editText中改变的值设置的HashMap中  
                    double i = 0;
                    if (!sss.toString().equals("")||!sss.toString().equals(".")){
                    	i = Double.parseDouble(sss.toString());
                		Farm.myFarmProduct.get(position).setQuantity(i);
                    }
                }  
            }); 
            
			return convertView;
		}
		
	}class BuyViewHolder{
		ImageView imgv;
		TextView txv_name,txv_price;
		EditText edt;
	}
	class sendOrderAsyncTask extends AsyncTask<Void,String,String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ConsumerBuyProductActivity.this);
			pDialog.setMessage("sending order in josn. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(Void... abs) {
			// get the order 
			
			
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(P_fid, "1"));
			
			params.add(new BasicNameValuePair(P_cid, Integer.toString(User.cid)));
			double total =0;
			for(int i =0;i < Farm.myFarmProduct.size();i++){
						
				if(Farm.myFarmProduct.get(i).getQuantity()!=0){
					int tempPrice = Farm.myFarmProduct.get(i).getVegetablePrice();
					double tempQ = Farm.myFarmProduct.get(i).getQuantity();
					total += tempPrice * tempQ;
					params.add(new BasicNameValuePair("pro"+Integer.toString(i+1), 
							Double.toString(tempQ)));// to extrcat the q	
				}
			}
			params.add(new BasicNameValuePair(P_total,Double.toString(total) ));
			JSONParser jsonParser = new JSONParser();
			// finish add parm then post it 
			JSONObject json = jsonParser.makeHttpRequest(Web.url_receive_order,
					"POST", params);

			// check json success tag
			try {
				int success = json.getInt("success");
				
				if (success == 1) {
					
				} else {
					// failed to update product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			
			return "finish";
		}
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			Toast.makeText(ConsumerBuyProductActivity.this,"finish",Toast.LENGTH_SHORT).show();
			
		}
	}
}

