package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.ChoseChannelModule;
import com.power.home.di.module.VipModule;
import com.power.home.ui.activity.VipActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {VipModule.class, ChoseChannelModule.class}, dependencies = AppComponent.class)
public interface VipComponent {
    void inject(VipActivity activity);
}
