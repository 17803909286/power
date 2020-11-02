package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.CourseDailyModule;
import com.power.home.di.module.CourseFreeModule;
import com.power.home.ui.activity.CourseDailyActivity;
import com.power.home.ui.activity.CourseFreeActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {CourseDailyModule.class}, dependencies = AppComponent.class)
public interface CourseDailyComponent {
    void inject(CourseDailyActivity activity);
}
