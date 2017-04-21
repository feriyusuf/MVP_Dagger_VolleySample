package com.muktolab.mvp_dagger_volleysample.dagger.module;

import com.muktolab.mvp_dagger_volleysample.home_module.presenter.IMainPresenter;
import com.muktolab.mvp_dagger_volleysample.home_module.presenter.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by skarim on 4/9/17.
 */

@Module
public class CorePresenterModule {
    @Provides
    @Singleton
    public IMainPresenter provideLoginPresentor() {
        return new MainPresenterImpl() ;
    }

}
