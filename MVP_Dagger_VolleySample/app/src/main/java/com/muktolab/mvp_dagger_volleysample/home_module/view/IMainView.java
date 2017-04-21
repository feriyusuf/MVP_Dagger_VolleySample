package com.muktolab.mvp_dagger_volleysample.home_module.view;

import com.android.volley.VolleyError;
import com.muktolab.mvp_dagger_volleysample.entities.DayWiseDataEntity;

import java.util.List;

/**
 * Created by skarim on 4/22/17.
 */

public interface IMainView {
    void controlProgressBar(boolean isShowProgressBar);
    void onSuccess(List<DayWiseDataEntity> dayWiseDataList);
    void onFaillure(VolleyError error);

    void showErrorMessage(String errorMessage);
}
