package com.power.home.di.component;


import com.power.home.common.util.AppCrashHandlerUtil;
import com.power.home.di.FragmentScope;
import com.power.home.di.module.LogModule;

import dagger.Component;

@FragmentScope
@Component(modules = {LogModule.class },dependencies = AppComponent.class)
public interface LogComponent {
    void inject(AppCrashHandlerUtil appCrashHandlerUtil);
}
