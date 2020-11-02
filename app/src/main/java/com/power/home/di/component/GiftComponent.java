package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.GiftModule;
import com.power.home.ui.activity.EveryDatResultActivity;
import com.power.home.wxapi.WXEntryActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {GiftModule.class, GiftModule.class}, dependencies = AppComponent.class)
public interface GiftComponent {
    void inject(WXEntryActivity activity);
    void inject(EveryDatResultActivity activity);
}
