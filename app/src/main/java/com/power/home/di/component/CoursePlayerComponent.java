package com.power.home.di.component;


import com.power.home.data.CoursePlayerModel;
import com.power.home.di.FragmentScope;
import com.power.home.di.module.CoursePlayerModule;
import com.power.home.di.module.EMBAOnlineModule;
import com.power.home.ui.activity.CoursePlayerActivity;
import com.power.home.ui.activity.EMBAOnlineActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {CoursePlayerModule.class}, dependencies = AppComponent.class)
public interface CoursePlayerComponent {
    void inject(CoursePlayerActivity activity);
}
