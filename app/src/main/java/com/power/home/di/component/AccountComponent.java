package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.AccountModule;
import com.power.home.ui.activity.AccountActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {AccountModule.class}, dependencies = AppComponent.class)
public interface AccountComponent {
    void inject(AccountActivity activity);
}
