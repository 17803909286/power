package com.power.home.di.component;


import com.power.home.data.SearchModel;
import com.power.home.di.FragmentScope;
import com.power.home.di.module.SearchModule;
import com.power.home.ui.activity.SearchActivity;

import dagger.Component;

@FragmentScope
@Component(modules = {SearchModule.class}, dependencies = AppComponent.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}
