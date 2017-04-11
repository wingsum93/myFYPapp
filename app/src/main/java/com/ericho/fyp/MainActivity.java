package com.ericho.fyp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ericho.fyp.datatype.FarmVegetable;
import com.ericho.fyp.http.FarmApi;
import com.ericho.fyp.http.ProductApi;
import com.ericho.myapi.Web;
import com.ericho.util.MGson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends RxLifecycleAct implements OnClickListener {
    private static final String tag = "MainActivity";
    @BindView(R.id.activity_main_bt_customer)
	Button bt1;
	@BindView(R.id.activity_main_bt_farmer)
	Button bt2;
	@BindView(R.id.activity_main_bt_about)
	Button bt3;
	@BindView(R.id.activity_main_bt_exit)
	Button bt4;

    ProductApi productApi;
    FarmApi farmApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		assignC();

		Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Web.getHttpUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        productApi = retrofit.create(ProductApi.class);
        farmApi = retrofit.create(FarmApi.class);
        farmApi.getAllProduct()
                .filter(bas -> bas.isSuccess())
                .map(res -> res.getProducts())
                .compose(this.bindToLifeCycle())
                .subscribe(new Observer<List<FarmVegetable>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<FarmVegetable> farmVegetables) {
                        Log.d(tag, MGson.getInstance().toJson(farmVegetables));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(tag, e);
                    }

                    @Override
                    public void onComplete() {
                        Log.w(tag, "onComplete");
                    }
                });

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

		// add listener
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
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
