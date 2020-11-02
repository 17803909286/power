package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.InviteRecordModule;
import com.power.home.ui.activity.InviteRecordActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {InviteRecordModule.class}, dependencies = AppComponent.class)
public interface InviteRecordComponent {
    void inject(InviteRecordActivity activity);
}
