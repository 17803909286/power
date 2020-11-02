package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.BindBankModule;
import com.power.home.di.module.CodeModule;
import com.power.home.ui.activity.BindBankActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {BindBankModule.class, CodeModule.class}, dependencies = AppComponent.class)
public interface BindBankComponent {
    void inject(BindBankActivity activity);
}
