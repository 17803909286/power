package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.EMBAOnlineModule;
import com.power.home.di.module.MainHomeModule;
import com.power.home.ui.activity.EMBAOnlineActivity;
import com.power.home.ui.fragment.MainHomeFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {EMBAOnlineModule.class}, dependencies = AppComponent.class)
public interface EMBAOlineComponent {
    void inject(EMBAOnlineActivity activity);
}
