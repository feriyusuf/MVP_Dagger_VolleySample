package com.muktolab.mvp_dagger_volleysample.home_module.presenter;

import android.widget.ImageView;

import com.muktolab.mvp_dagger_volleysample.home_module.MainActivity;
import com.muktolab.mvp_dagger_volleysample.home_module.view.IMainView;

/**
 * Created by skarim on 4/22/17.
 */

public interface IMainPresenter {
    void setView(IMainView view, MainActivity context);
    void getYahooMonthWeatherData();
}
