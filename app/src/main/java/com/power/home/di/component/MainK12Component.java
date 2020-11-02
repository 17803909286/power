package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.MainK12Module;
import com.power.home.ui.fragment.MainK12Fragment;

import dagger.Component;

@FragmentScope
@Component(modules = {MainK12Module.class}, dependencies = AppComponent.class)
public interface MainK12Component {
    void inject(MainK12Fragment fragment);
}
