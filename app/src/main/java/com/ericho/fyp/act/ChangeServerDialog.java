package com.ericho.fyp.act;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.ericho.fyp.R;
import com.ericho.fyp.RxLifecycleAct;
import com.ericho.fyp.adapter.ServerUrlAdapter;
import com.ericho.fyp.constant.ServerAddress;
import com.ericho.fyp.datatype.ServerBean;
import com.ericho.fyp.datatype.internal.StorePref;
import com.ericho.util.StoreUtil;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by EricH on 4/3/2016.
 */
public class ChangeServerDialog extends RxLifecycleAct implements View.OnClickListener,
        View.OnFocusChangeListener, AdapterView.OnItemClickListener {


    @BindView(R.id.a_server)
    EditText a_server;


    @BindView(R.id.gridView)
    protected GridView gridView;
    @BindView(R.id.btn_ok)
    Button btn_ok;
    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    final String t = "ChangeServerDialog";
    private SharedPreferences preferences;
    private List<ServerBean> serverBeen;
    private ServerUrlAdapter adapter;
    private StorePref storePref = StorePref.Server;
    @Nullable
    private EditText targentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyView();
    }

    private void setMyView() {
        setContentView(R.layout.act_dialog_change_server);
        ButterKnife.bind(this);
        setTitle("Enter url and port number");
        initializeUiState();
        setListener();
        setObservable();
        //
        String s = StoreUtil.getValue(this, storePref);
        a_server.setText(s);

    }

    private void initializeUiState() {
        //bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //view
        btn_ok.setEnabled(false);
        serverBeen = prepareUrlObject();
        adapter = new ServerUrlAdapter(this, serverBeen);
        gridView.setAdapter(adapter);
    }

    private void setListener() {
        //listener

        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
        //focus ch listener
        a_server.setOnFocusChangeListener(this);
    }

    private void setObservable() {
        Observable<Boolean> observable = RxTextView.afterTextChangeEvents(a_server)
                .map(event -> event.editable().toString())
                .map(s -> s != null && s.length() > 0)
                .debounce(300, TimeUnit.MILLISECONDS);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifeCycle())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        btn_ok.setEnabled(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ChangeServerDialog.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_ok:
                saveServerUrl(a_server.getText().toString());
                break;
            case R.id.btn_cancel:
                finish();
                break;
            default:
        }
    }

    private void saveServerUrl(String serverUrl) {
        //prepare


        //logic judge

        //set data
        StoreUtil.setValue(this, storePref, serverUrl);
        //set redownload location
        finish();
    }

    //-------------------------------  target --------------------------------------------**//
    private void setTragetText(String s) {
        if (targentEditText == null) return;
        targentEditText.setText(s);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        boolean isEditText = v instanceof EditText;
        if (!isEditText) return;
        if (hasFocus) {
            targentEditText = (EditText) v;
        } else {
            targentEditText = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ServerBean bean = adapter.getItem(position);
        setTragetText(bean.getUrl());
    }


    private List<ServerBean> prepareUrlObject() {
        List<ServerBean> res = new ArrayList<>();

        res.add(new ServerBean("default", ServerAddress.DEFAULT, R.drawable.banana));
        res.addAll(getCustomServerAddress());
        return res;
    }

    @NonNull
    private List<ServerBean> getCustomServerAddress() {
//        List<ServerBean> res = new ArrayList<>();
//        for (int i = 0; i < BuildConfig.URL_ARRAY.length; i++) {
//            res.add(new ServerBean(String.valueOf(i), BuildConfig.URL_ARRAY[i], R.drawable.fire_orange_64));
//        }
//        return res;
        return Collections.emptyList();
    }
}
