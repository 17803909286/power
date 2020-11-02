package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.MainMineModule;
import com.power.home.ui.fragment.MainMineFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {MainMineModule.class}, dependencies = AppComponent.class)
public interface MainMineComponent {
    void inject(MainMineFragment fragment);
}
