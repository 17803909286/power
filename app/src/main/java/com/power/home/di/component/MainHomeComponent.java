package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.MainHomeModule;
import com.power.home.ui.fragment.MainHomeFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {MainHomeModule.class}, dependencies = AppComponent.class)
public interface MainHomeComponent {
    void inject(MainHomeFragment fragment);
}
