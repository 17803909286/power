package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.VersionModule;
import com.power.home.ui.activity.MainActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {VersionModule.class}, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
