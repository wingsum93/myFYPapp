package com.ericho.fyp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ericho.fyp.datatype.Transaction;
import com.ericho.fyp.datatype.TransactionOfFarm;
import com.ericho.myapi.JSONParser;

public class FarmViewTransactionDetailsActivity extends Activity implements OnClickListener{
	ListView list;
	Button bt_confirm,bt_reset,bt_cancel;
	int TransactionId;
	ProgressDialog pDialog;
	JSONParser jsonParser;
	Transaction thisTransaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_farm_view_transaction_details);
		assgnC();
		new getFarmTransactionDetailsAsyncTask().execute();
		
		
	}
	private void assgnC() {
		bt_confirm = (Button)findViewById(R.id.Activity_farm_view_transaction_detail_bt_confirm);
		bt_reset = (Button)findViewById(R.id.Activity_farm_view_transaction_detail_bt_reset);
		bt_cancel = (Button)findViewById(R.id.Activity_farm_view_transaction_detail_bt_cancel);
		list = (ListView)findViewById(R.id.Activity_farm_view_transaction_detail_list);
		bt_confirm.setOnClickListener(this);
		bt_reset.setOnClickListener(this);
		bt_cancel.setOnClickListener(this);
		// get inteent to load the detail of one transaction
		Intent i = getIntent();
		TransactionId = 0;
		TransactionId = i.getIntExtra("transactionId", 0);
		thisTransaction = TransactionOfFarm.getTransactionByTID(TransactionId);
		
		
	}
	class getFarmTransactionDetailsAsyncTask extends AsyncTask<Void,String,String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FarmViewTransactionDetailsActivity.this);
			pDialog.setMessage("loading details ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			try{
				Thread.sleep(2000);
			}catch(Exception e){
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
					getFarmTrasactionDetailsAdapter adapter = new getFarmTrasactionDetailsAdapter(FarmViewTransactionDetailsActivity.this);
					
					list.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
				
			});
				
			
		}
	}
	class getFarmTrasactionDetailsAdapter extends BaseAdapter{
		private LayoutInflater mInflator;
		getFarmTrasactionDetailsAdapter(Context c){
			mInflator = LayoutInflater.from(c);
		}
		@Override
		public int getCount() {
			// get the transaction id and 
			return thisTransaction.theDetail.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return thisTransaction.theDetail.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder ;
			if(convertView==null){
				holder = new  ViewHolder();
				convertView= mInflator.inflate(R.layout.list_view_transaction_details, null);
				holder.name=(TextView)convertView.findViewById(R.id.list_view_transaction_details_p_name);
				holder.priceAndUnit=(TextView)convertView.findViewById(R.id.list_view_transaction_details_price_unit);
				holder.expectedQuantity=(TextView)convertView.findViewById(R.id.list_view_transaction_details_txv_expected_quantity);
				holder.edt_actual_quantity=(EditText)convertView.findViewById(R.id.list_view_transaction_details_edt_actual_quantity);
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder)convertView.getTag();
			}
		    // set item content// to be continous
			holder.name.setText(thisTransaction.theDetail.get(position).productName);
			String temp = thisTransaction.theDetail.get(position).Price+ " / "+thisTransaction.theDetail.get(position).unit;
			holder.priceAndUnit.setText(temp);
			holder.expectedQuantity.setText(Double.toString(thisTransaction.theDetail.get(position).Quantity));
			if(thisTransaction.theDetail.get(position).Quantity==0d){
				holder.edt_actual_quantity.setText("0");
			}
			else{
				holder.edt_actual_quantity.setText(Double.toString(thisTransaction.theDetail.get(position).actual_quantity));
			}
			
		    /// set listener for enter the real quantity
			holder.edt_actual_quantity.addTextChangedListener(new TextWatcher() {  
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
                    	
                    }
                }  
            }); 
			return convertView;
		}
		class ViewHolder{

			public EditText edt_actual_quantity;
			public TextView expectedQuantity;
			public TextView priceAndUnit;
			public TextView name;
			
			
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.Activity_farm_view_transaction_detail_bt_confirm:
			break;
		case R.id.Activity_farm_view_transaction_detail_bt_reset:
			new getFarmTransactionDetailsAsyncTask().execute();
			break;
		case R.id.Activity_farm_view_transaction_detail_bt_cancel:
			this.finish();
			break;
		default:break;
		}
	}
}
