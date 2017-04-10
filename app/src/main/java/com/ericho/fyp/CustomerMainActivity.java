package com.ericho.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ericho.fyp.datatype.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CustomerMainActivity extends RxLifecycleAct implements OnClickListener{
    @BindView(R.id.activity_customer_main_interface_buy)
    Button bt1;
    @BindView(R.id.activity_customer_main_interface_upload_photo)
    Button bt2;
    @BindView(R.id.activity_customer_main_interface_txv)
    TextView txv;

	private Observer<Integer> observer ;
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_main);
		assignC();
		
	}
	private void assignC(){
        ButterKnife.bind(this);
        //add listener
//		RxView.clicks(bt1)
//				.debounce(1000, TimeUnit.MILLISECONDS)
//				.map(o -> (View) o)
//				.map(view -> view.getId())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribeOn(AndroidSchedulers.mainThread())
//				.compose(bindToLifeCycle())
//				.subscribe(getViewObserver());
//				;
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
		txv.setText("Welcome! "+User.getUserName()+"..");
	}

	private Observer<Integer> getViewObserver() {
		if(observer == null){
			observer = new Observer<Integer>() {
				@Override
				public void onSubscribe(Disposable d) {
				}

				@Override
				public void onNext(Integer integer) {

				}

				@Override
				public void onError(Throwable e) {

				}

				@Override
				public void onComplete() {

				}
			};
		}
		return observer;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		switch(v.getId()){
		case R.id.activity_customer_main_interface_buy:
			i.setClass(getApplicationContext(), ConsumerChooseFarmActivity.class);
			startActivity(i);
			break;
		case R.id.activity_customer_main_interface_upload_photo:
			break;
		}
	} 
}