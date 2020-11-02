package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.StudyRecordModule;
import com.power.home.ui.activity.StudyRecordsActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {StudyRecordModule.class}, dependencies = AppComponent.class)
public interface StudyRecordComponent {
    void inject(StudyRecordsActivity activity);
}
