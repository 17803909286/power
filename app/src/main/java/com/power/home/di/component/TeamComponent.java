package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.TeamInfoModule;
import com.power.home.ui.activity.InviteRecordActivity;
import com.power.home.ui.activity.TeamActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {TeamInfoModule.class}, dependencies = AppComponent.class)
public interface TeamComponent {
    void inject(TeamActivity activity);
}
