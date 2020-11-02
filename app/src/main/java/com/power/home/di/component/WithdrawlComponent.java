package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.WithdrawlModule;
import com.power.home.ui.activity.WithDrawalActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {WithdrawlModule.class}, dependencies = AppComponent.class)
public interface WithdrawlComponent {
    void inject(WithDrawalActivity activity);
}
