package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.ConvertRecordModule;
import com.power.home.ui.activity.ConvertRecordActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {ConvertRecordModule.class}, dependencies = AppComponent.class)
public interface ConvertRecordComponent {
    void inject(ConvertRecordActivity activity);
}
