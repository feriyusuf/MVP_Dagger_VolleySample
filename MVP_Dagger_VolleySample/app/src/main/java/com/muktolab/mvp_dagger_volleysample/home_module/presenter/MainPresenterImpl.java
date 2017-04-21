package com.muktolab.mvp_dagger_volleysample.home_module.presenter;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.muktolab.mvp_dagger_volleysample.base.MyApplication;
import com.muktolab.mvp_dagger_volleysample.entities.DayWiseDataEntity;
import com.muktolab.mvp_dagger_volleysample.home_module.MainActivity;
import com.muktolab.mvp_dagger_volleysample.home_module.view.IMainView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by skarim on 4/22/17.
 */

public class MainPresenterImpl implements IMainPresenter {

    @Inject
    IMainView view;

    MainActivity context;


    @Override
    public void setView(IMainView view, MainActivity context) {
        this.view=view;
        this.context=context;
    }

    @Override
    public void getYahooMonthWeatherData() {

        String weatherURL="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        try {
            view.controlProgressBar(true);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    weatherURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            view.controlProgressBar(false);
                            if(response!=null){
                                showLog(response.toString());
                                try{

                                    JSONObject queryObject=response.getJSONObject("query");
                                    JSONObject resultsObject=queryObject.getJSONObject("results");
                                    JSONObject channelObject=resultsObject.getJSONObject("channel");
                                    JSONObject itemObject=channelObject.getJSONObject("item");


                                    JSONArray foreCastArray=itemObject.getJSONArray("forecast");

                                    if(foreCastArray!=null && foreCastArray.length()>0){
                                        showLog("Data Size : "+foreCastArray.length());

                                        List<DayWiseDataEntity> dayWiseDataList=new ArrayList<>();
                                        DayWiseDataEntity dataEntity;
                                        JSONObject jsonObject;
                                        for(int index=0;index<foreCastArray.length();index++){
                                            jsonObject=foreCastArray.getJSONObject(index);
                                            dataEntity=new DayWiseDataEntity();

                                            dataEntity.code=jsonObject.getString("code");
                                            dataEntity.date=jsonObject.getString("date");
                                            dataEntity.day=jsonObject.getString("day");
                                            dataEntity.high=jsonObject.getString("high");
                                            dataEntity.low=jsonObject.getString("low");
                                            dataEntity.text=jsonObject.getString("text");
                                            dayWiseDataList.add(dataEntity);
                                        }

                                        view.onSuccess(dayWiseDataList);



                                    }else{
                                        view.showErrorMessage("No Data Found");
                                    }

                                }catch (Exception e){
                                    view.showErrorMessage(e.getMessage());
                                }

                            }else{
                                view.showErrorMessage("No Data Found");
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    view.controlProgressBar(false);

                    if(error!=null && error.networkResponse!=null){
                        view.onFaillure(error);
                    }else{
                        view.showErrorMessage("An unexpected Error occured");
                    }

                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().addToRequestQueue(request, "GET_WEATHER_DATA");
        } catch (Exception e) {
            view.controlProgressBar(false);
            view.showErrorMessage(e.getMessage());
        }

    }

    private void showLog(String message){
        Log.d("skm",message==null?"":message);
    }
    private void showToastMessage(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

}
