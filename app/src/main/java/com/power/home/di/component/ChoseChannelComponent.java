package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.AllCourseModule;
import com.power.home.di.module.ChoseChannelModule;
import com.power.home.presenter.contract.ChoseChannelContract;
import com.power.home.ui.activity.AllCourseActivity;
import com.power.home.ui.activity.ChosePayWayActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {ChoseChannelModule.class}, dependencies = AppComponent.class)
public interface ChoseChannelComponent {
    void inject(ChosePayWayActivity chosePayWayActivity);
}
