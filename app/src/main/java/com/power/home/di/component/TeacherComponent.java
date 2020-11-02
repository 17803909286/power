package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.TeacherModule;
import com.power.home.ui.activity.TeacherGuideActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {TeacherModule.class}, dependencies = AppComponent.class)
public interface TeacherComponent {
    void inject(TeacherGuideActivity activity);
}
