package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.UploadIdCardModule;
import com.power.home.ui.activity.CertificationFristActivity;
import com.power.home.ui.activity.PersonInfoActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {UploadIdCardModule.class}, dependencies = AppComponent.class)
public interface UploadIdCardComponent {
    void inject(CertificationFristActivity activity);
}
