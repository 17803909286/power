package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.AllCourseModule;
import com.power.home.di.module.ReceiveMaterialModule;
import com.power.home.ui.activity.AllCourseActivity;
import com.power.home.ui.activity.ReceiveMaterialActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {ReceiveMaterialModule.class}, dependencies = AppComponent.class)
public interface ReceiveMaterialComponent {
    void inject(ReceiveMaterialActivity receiveMaterialActivity);
}
