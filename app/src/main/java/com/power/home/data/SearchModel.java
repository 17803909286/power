package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.SearchBean;
import com.power.home.data.bean.SearchHotWordBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.SearchContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class SearchModel implements SearchContract.ISearchModel {

    private ApiService mApiService;

    public SearchModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<List<SearchHotWordBean>>> searchWords() {
        return mApiService.searchWords();
    }

    @Override
    public Observable<BaseBean<List<SearchBean>>> search(String title) {
        return mApiService.search(title);
    }

    @Override
    public Observable<BaseBean<List<String>>> getFindNearWord() {
        return mApiService.getFindNearWord();
    }

    @Override
    public Observable<BaseBean<EmptyBean>> deleteAll() {
        return mApiService.deleteAll();
    }
}
