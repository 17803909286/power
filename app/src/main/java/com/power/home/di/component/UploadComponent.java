package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.NickNameModule;
import com.power.home.di.module.UploadModule;
import com.power.home.ui.activity.PersonInfoActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {UploadModule.class, NickNameModule.class}, dependencies = AppComponent.class)
public interface UploadComponent {
    void inject(PersonInfoActivity activity);
}
