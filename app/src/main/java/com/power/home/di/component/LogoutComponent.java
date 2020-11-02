package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.LogoutModule;
import com.power.home.ui.activity.SettingActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {LogoutModule.class}, dependencies = AppComponent.class)
public interface LogoutComponent {
    void inject(SettingActivity activity);
}
