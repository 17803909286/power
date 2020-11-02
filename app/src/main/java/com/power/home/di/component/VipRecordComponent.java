package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.VipRecordModule;
import com.power.home.ui.activity.VipRecordActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {VipRecordModule.class}, dependencies = AppComponent.class)
public interface VipRecordComponent {
    void inject(VipRecordActivity activity);
}
