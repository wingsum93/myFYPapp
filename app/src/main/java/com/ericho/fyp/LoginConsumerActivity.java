package com.ericho.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ericho.fyp.datatype.User;
import com.ericho.myapi.Login;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LoginConsumerActivity extends RxLifecycleAct {
    private static final String tag = "LoginConsumerActivity";
    @BindView(R.id.activity_login_consumer_bt)
    Button bt1;
    @BindView(R.id.activity_login_consumer_field_id)
    EditText edt_id;
    @BindView(R.id.activity_login_consumer_field_pw)
    EditText edt_pw;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_consumer);
        ButterKnife.bind(this);
        setObservable();

    }

    private void setObservable() {
        Observable<String> usernameObservable =
                RxTextView.afterTextChangeEvents(edt_id).map(ev -> ev.editable().toString());
        Observable<String> passwordObservable =
                RxTextView.afterTextChangeEvents(edt_pw).map(ev -> ev.editable().toString());

        Observable.zip(usernameObservable, passwordObservable,
                (user, pw) -> user.length() > 0 && pw.length() > 0)
                .compose(this.bindToLifeCycle())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        bt1.setEnabled(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginConsumerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(tag, "cc complete");
                    }
                });

        //login api
        RxView.clicks(bt1)
                .map(o -> {
                    progressBar.setVisibility(View.VISIBLE);
                    return o;
                })
                .map(obj -> Login.checkAuthentication(edt_id.getText().toString(), edt_pw.getText().toString()))
                .debounce(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifeCycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    progressBar.setVisibility(View.INVISIBLE);
                    if (aBoolean) {
                        User.setUserName(edt_id.getText().toString());
                        startActivity(new Intent(this, CustomerMainActivity.class));
                    } else {
                        Toast.makeText(this, "you password is wrong", Toast.LENGTH_LONG).show();
                    }
                }, err -> {
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d(tag, "error");
                });

    }


}

