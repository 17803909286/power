package com.power.home.di.component;


import com.power.home.di.FragmentScope;
import com.power.home.di.module.StudyPlanModule;
import com.power.home.ui.activity.MainStudyPlanActivity;
import com.power.home.ui.fragment.MainStudyPlanFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {StudyPlanModule.class}, dependencies = AppComponent.class)
public interface StudyPlanComponent {
    void inject(MainStudyPlanFragment mainFindFragment);
    void inject(MainStudyPlanActivity activity);
}
