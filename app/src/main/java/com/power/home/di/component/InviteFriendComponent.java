package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.InviteFriendlModule;
import com.power.home.ui.activity.InviteFriendActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {InviteFriendlModule.class}, dependencies = AppComponent.class)
public interface InviteFriendComponent {
    void inject(InviteFriendActivity activity);
}
