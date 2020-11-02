package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.EveryDayModule;
import com.power.home.ui.activity.EveryDayActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {EveryDayModule.class}, dependencies = AppComponent.class)
public interface EveryDayComponent {
    void inject(EveryDayActivity activity);
}
