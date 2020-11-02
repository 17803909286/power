package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.TeacherDetailModule;
import com.power.home.ui.activity.TeacherDetailActivity;
import com.power.home.ui.activity.TeacherGuideActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {TeacherDetailModule.class}, dependencies = AppComponent.class)
public interface TeacherDetailComponent {
    void inject(TeacherDetailActivity activity);
}
