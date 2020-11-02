package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.ConvertModule;
import com.power.home.ui.activity.ConvertActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {ConvertModule.class}, dependencies = AppComponent.class)
public interface ConvertComponent {
    void inject(ConvertActivity activity);
}
