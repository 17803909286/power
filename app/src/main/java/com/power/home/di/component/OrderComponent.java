package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.OrderModule;
import com.power.home.ui.activity.OrderActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {OrderModule.class}, dependencies = AppComponent.class)
public interface OrderComponent {
    void inject(OrderActivity activity);
}
