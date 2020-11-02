package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.CodeModule;
import com.power.home.di.module.EnrollModule;
import com.power.home.ui.activity.CourseDetailsActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {EnrollModule.class, CodeModule.class}, dependencies = AppComponent.class)
public interface EnrollComponent {
    void inject(CourseDetailsActivity activity);
}
