package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.WithdrawlRecordModule;
import com.power.home.ui.activity.WithDrawalRecordActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {WithdrawlRecordModule.class}, dependencies = AppComponent.class)
public interface WithdrawlRecordComponent {
    void inject(WithDrawalRecordActivity activity);
}
