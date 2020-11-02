package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.BindPhoneModule;
import com.power.home.di.module.CodeModule;
import com.power.home.ui.activity.BindPhoneTwoActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {BindPhoneModule.class, CodeModule.class}, dependencies = AppComponent.class)
public interface BindPhoneComponent {
    void inject(BindPhoneTwoActivity activity);
}
