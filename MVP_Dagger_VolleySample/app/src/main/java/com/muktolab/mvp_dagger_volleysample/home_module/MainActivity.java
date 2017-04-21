package com.muktolab.mvp_dagger_volleysample.home_module;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.muktolab.mvp_dagger_volleysample.R;
import com.muktolab.mvp_dagger_volleysample.base.MyApplication;
import com.muktolab.mvp_dagger_volleysample.entities.DayWiseDataEntity;
import com.muktolab.mvp_dagger_volleysample.home_module.presenter.IMainPresenter;
import com.muktolab.mvp_dagger_volleysample.home_module.view.IMainView;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainView{

    @Inject
    IMainPresenter presenter;
    Toolbar toolbar;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication.getInstance().getNetComponent().inject(this);
        initToolBar();
        initViews();
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews(){
        presenter.setView(this,this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.getYahooMonthWeatherData();
    }

    @Override
    public void controlProgressBar(boolean isShowProgressBar) {
        if(isShowProgressBar){
            if(progressDialog!=null){
                progressDialog.dismiss();
            }
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading, Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }else{
            if(progressDialog!=null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void onSuccess(List<DayWiseDataEntity> dayWiseDataList) {
        if(dayWiseDataList!=null && dayWiseDataList.size()>0){
            showToastMessage("Data Size :"+dayWiseDataList.size());
        }else{
            showToastMessage("No Data Found");
        }

    }

    @Override
    public void onFaillure(VolleyError error) {
        showToastMessage(error.getMessage());
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        showToastMessage(errorMessage);

    }

    private void showToastMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
