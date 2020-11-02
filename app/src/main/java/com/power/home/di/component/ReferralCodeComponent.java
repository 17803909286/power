package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.ReferralCodeModule;
import com.power.home.ui.activity.ReferralCodeActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {ReferralCodeModule.class}, dependencies = AppComponent.class)
public interface ReferralCodeComponent {
    void inject(ReferralCodeActivity activity);
}
