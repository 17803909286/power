package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.CourseFreeModule;
import com.power.home.ui.activity.CourseFreeActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {CourseFreeModule.class}, dependencies = AppComponent.class)
public interface CourseFreeComponent {
    void inject(CourseFreeActivity activity);
}
