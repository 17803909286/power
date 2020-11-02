package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.BindPhoneModule;
import com.power.home.di.module.CertificationTwoModule;
import com.power.home.di.module.CodeModule;
import com.power.home.ui.activity.BindPhoneTwoActivity;
import com.power.home.ui.activity.CertificationTwoActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {CertificationTwoModule.class, CodeModule.class}, dependencies = AppComponent.class)
public interface CertificationTwoComponent {
    void inject(CertificationTwoActivity activity);
}
