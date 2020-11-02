package com.power.home.di.module;


import com.power.home.data.SearchModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.SearchContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class SearchModule {

    private SearchContract.View mView;

    public SearchModule(SearchContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public SearchContract.View provideView() {
        return mView;
    }

    @Provides
    public SearchContract.ISearchModel provideModel(ApiService apiService) {

        return new SearchModel(apiService);
    }
}
