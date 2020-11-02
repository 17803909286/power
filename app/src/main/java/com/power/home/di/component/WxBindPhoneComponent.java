package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.CodeModule;
import com.power.home.di.module.WxBindPhoneModule;
import com.power.home.di.module.WxLoginModule;
import com.power.home.ui.activity.LoginActivity;
import com.power.home.ui.activity.WechatBindPhoneActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {WxBindPhoneModule.class, CodeModule.class}, dependencies = AppComponent.class)
public interface WxBindPhoneComponent {
    void inject(WechatBindPhoneActivity activity);
}
