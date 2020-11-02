package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.MessageModule;
import com.power.home.di.module.WithdrawlRecordModule;
import com.power.home.ui.activity.MessageActivity;
import com.power.home.ui.activity.WithDrawalRecordActivity;
import com.power.home.ui.fragment.MessageFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {MessageModule.class}, dependencies = AppComponent.class)
public interface MessageComponent {
    void inject(MessageFragment fragment);
}
