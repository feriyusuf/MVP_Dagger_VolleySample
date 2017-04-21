package com.muktolab.mvp_dagger_volleysample.base;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.muktolab.mvp_dagger_volleysample.dagger.component.DaggerNetComponent;
import com.muktolab.mvp_dagger_volleysample.dagger.component.NetComponent;
import com.muktolab.mvp_dagger_volleysample.dagger.module.AppModule;
import com.muktolab.mvp_dagger_volleysample.dagger.module.CorePresenterModule;

/**
 * Created by skarim on 4/22/17.
 */

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    NetComponent mNetComponent;
    private RequestQueue requestQueue;
    private static MyApplication instace;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .corePresenterModule(new CorePresenterModule())
                .build();

        instace = this;
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public static synchronized MyApplication getInstance() {
        return instace;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }
    public <T> void removeFromRequestQue(String tag) {
        getRequestQueue().cancelAll(tag);

    }


}
