package com.power.home.di.component;

import android.app.Application;


import com.power.home.data.http.ApiService;
import com.power.home.di.module.AppModule;
import com.power.home.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ZHP on 2017/6/24.
 */


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();


    public Application getApplication();
}
