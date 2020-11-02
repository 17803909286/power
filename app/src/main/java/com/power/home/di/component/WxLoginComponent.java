package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.CodeModule;
import com.power.home.di.module.WxLoginModule;
import com.power.home.ui.activity.LoginActivity;
import com.power.home.ui.activity.VerificationCodeLoginActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {WxLoginModule.class}, dependencies = AppComponent.class)
public interface WxLoginComponent {
    void inject(LoginActivity activity);
}
