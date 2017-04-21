package com.muktolab.mvp_dagger_volleysample.dagger.component;

import com.muktolab.mvp_dagger_volleysample.dagger.module.AppModule;
import com.muktolab.mvp_dagger_volleysample.dagger.module.CorePresenterModule;
import com.muktolab.mvp_dagger_volleysample.home_module.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by skarim on 4/8/17.
 */

@Singleton
@Component(modules = {AppModule.class, CorePresenterModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
