package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.CourseOfflineModule;
import com.power.home.ui.activity.CourseOfflineActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {CourseOfflineModule.class}, dependencies = AppComponent.class)
public interface CourseOfflineComponent {
    void inject(CourseOfflineActivity activity);
}
