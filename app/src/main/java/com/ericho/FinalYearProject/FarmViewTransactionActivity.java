package com.ericho.FinalYearProject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ericho.datatype.Transaction;
import com.ericho.datatype.TransactionDetail;
import com.ericho.datatype.TransactionOfFarm;
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

public class FarmViewTransactionActivity extends Activity implements OnItemClickListener,OnClickListener{
    @BindView(R.id.Activity_farm_view_transaction_list)
    ListView list;
    @BindView(R.id.Activity_farm_view_transaction_bt_refresh)
    Button bt_refresh;
    private ProgressDialog pDialog;
	JSONParser jsonParser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_farm_view_transaction);
        ButterKnife.bind(this);
        bt_refresh.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		// TODO Auto-generated method stub
		
		Intent i = new Intent();
		i.setClass(FarmViewTransactionActivity.this, FarmViewTransactionDetailsActivity.class);
		int tid = TransactionOfFarm.t.get(pos).tid;
		i.putExtra("transactionId", tid);
		startActivity(i);
	}
	class getFarmAllTransactionAsyncTask extends AsyncTask<Void, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FarmViewTransactionActivity.this);
			pDialog.setMessage("loading transacrtion ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			int success=0;
			jsonParser = new JSONParser();
			TransactionOfFarm.flushTheTransaction();
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("fid", "1"));

				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						Web.farm_admin, "POST", params);

				// check your log for json response
				//Log.d("receive trans + details", json.toString());
				
				// json success tag
				success = json.getInt("success");
				if (success == 1) {
					// product successfully get
					// store it in memory
					JSONArray trans = json.getJSONArray("transaction");
					for (int i = 0; i < trans.length(); i++) {
						Transaction tt = new Transaction();
						
						// this is the outer layer / store trans info
						JSONObject outer = trans.getJSONObject(i);
						tt.tid = outer.getInt("tid");
						tt.transactionDate = outer.getString("date");
						tt.status = outer.getString("status");
						tt.total = outer.getDouble("total");
						tt.consumer = outer.getString("name");
						String temp1 = outer.getString("final_total");
						if(!temp1.equals("null")){// set it to o if null value
							tt.final_total = Double.parseDouble(temp1);
						}else{
							tt.final_total = 0d ;
						}
						
						JSONArray inner = outer.getJSONArray("detail");
						for (int j = 0; j < inner.length(); j++) {
							TransactionDetail td = new TransactionDetail();
							// this is detail
							JSONObject cc = inner.getJSONObject(j);
							td.productName = cc.getString("product_name");
							td.unit = cc.getString("unit");
							td.Price = cc.getInt("product_price");
							td.Description = cc.getString("product_description");
							td.Quantity = cc.getDouble("product_quantity");
							String temp2 = cc.getString("actual_quantity");
							if(!temp2.equals("null")){
								td.actual_quantity = Double.parseDouble(temp2);
							}else{
								td.actual_quantity = 0d ;
							}
							tt.theDetail.add(td);// addd detail in each time
						}
						TransactionOfFarm.t.add(tt);
						
					}
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product uupdated
			pDialog.dismiss();
			runOnUiThread(new Runnable(){

				@Override
				public void run() {
					// update the farm
					ViewAllTransactionAdapter adapter = new ViewAllTransactionAdapter(FarmViewTransactionActivity.this);
					list = (ListView)findViewById(R.id.Activity_farm_view_transaction_list);
					list.setAdapter(adapter);
					list.setOnItemClickListener(FarmViewTransactionActivity.this);
					adapter.notifyDataSetChanged();
				}
				
			});
				
			
		}
		
	}
	class ViewAllTransactionAdapter extends BaseAdapter{
		private Context ccon;// use for start 
		private LayoutInflater mInflator;// use to draw view
		ViewAllTransactionAdapter(Context cc){
			this.ccon=cc;
			mInflator=LayoutInflater.from(ccon);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return TransactionOfFarm.t.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return TransactionOfFarm.t.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder ;
			if(convertView==null){
				holder = new  ViewHolder();
				convertView= mInflator.inflate(R.layout.list_view_transaction, null);
				holder.txv_name=(TextView)convertView.findViewById(R.id.list_view_transaction_txv_consumer_name);
				holder.txv_date=(TextView)convertView.findViewById(R.id.list_view_transaction_txv_date);
				holder.txv_status=(TextView)convertView.findViewById(R.id.list_view_transaction_txv_status);
				holder.txv_total=(TextView)convertView.findViewById(R.id.list_view_transaction_txv_total);
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder)convertView.getTag();
			}
		    // set item content
			holder.txv_name.setText(TransactionOfFarm.t.get(position).consumer);
			holder.txv_date.setText(TransactionOfFarm.t.get(position).transactionDate);
			holder.txv_status.setText(TransactionOfFarm.t.get(position).status);
			String temp = Double.toString(TransactionOfFarm.t.get(position).total);
			holder.txv_total.setText(temp);
		    
			return convertView;
		}
		class ViewHolder{
			TextView txv_name,txv_status,txv_date,txv_total;
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.Activity_farm_view_transaction_bt_refresh:
			new getFarmAllTransactionAsyncTask().execute();
			break;
		
		
		}
	}


	
}
