package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.AllCourseModule;
import com.power.home.di.module.MainHomeModule;
import com.power.home.ui.activity.AllCourseActivity;
import com.power.home.ui.fragment.MainHomeFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {AllCourseModule.class}, dependencies = AppComponent.class)
public interface AllCourseComponent {
    void inject(AllCourseActivity allCourseActivity);
}
