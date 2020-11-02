package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.CodeModule;
import com.power.home.di.module.LoginModule;
import com.power.home.ui.activity.VerificationCodeLoginActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {LoginModule.class, CodeModule.class}, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(VerificationCodeLoginActivity activity);
}
