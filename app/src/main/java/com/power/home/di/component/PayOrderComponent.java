package com.power.home.di.component;


import com.power.home.common.util.AppCrashHandlerUtil;
import com.power.home.common.util.OrderUitils;
import com.power.home.di.FragmentScope;
import com.power.home.di.module.LogModule;
import com.power.home.di.module.PayOrderModule;

import dagger.Component;

@FragmentScope
@Component(modules = {PayOrderModule.class },dependencies = AppComponent.class)
public interface PayOrderComponent {
    void inject(OrderUitils orderUitils);
}
