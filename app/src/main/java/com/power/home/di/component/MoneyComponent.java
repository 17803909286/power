package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.MoneyModule;
import com.power.home.ui.fragment.MoneyInFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {MoneyModule.class}, dependencies = AppComponent.class)
public interface MoneyComponent {
    void inject(MoneyInFragment fragment);
}
