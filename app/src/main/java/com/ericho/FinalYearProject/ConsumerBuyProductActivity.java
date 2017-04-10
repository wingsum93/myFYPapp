package com.ericho.FinalYearProject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ericho.FinalYearProject.adapter.BuyVegetableAdapter;
import com.ericho.datatype.Farm;
import com.ericho.datatype.User;
import com.ericho.myapi.JSONParser;
import com.ericho.myapi.Web;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsumerBuyProductActivity extends Activity implements OnClickListener{
    @BindView(R.id.activity_consumer_buy_product_bt1)
    Button bt1;
    @BindView(R.id.activity_consumer_buy_product_bt2)
    Button bt2;
    @BindView(R.id.activity_consumer_buy_product_bt3)
    Button bt3;
    @BindView(R.id.activity_consumer_buy_product_listview)
    ListView mList;
    TextView mTxv;
    @BindView(R.id.activity_consumer_buy_product_txv_consumer_name)
    TextView txv_name;
    //String farm_id;//this is to identify the farm no. start from 1-n.
	ProgressDialog pDialog;BaseAdapter adapter;
	JSONArray products;
	private String P_fid ="f_id",P_cid="c_id",P_total="t_total",P_pro1="pro1",P_pro2="pro2",P_pro3="pro3",P_pro4="pro4",P_pro5="pro5",P_pro6="pro6";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_buy_product);
        ButterKnife.bind(this);
        assignC();
	}

	private void assignC() {
		setTitle("Buy Vegetables Pages");

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

