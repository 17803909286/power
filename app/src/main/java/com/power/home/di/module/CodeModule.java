package com.power.home.di.module;


import com.power.home.data.CodeModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CodeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class CodeModule {

    private CodeContract.View mView;

    public CodeModule(CodeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public CodeContract.View provideView() {
        return mView;
    }

    @Provides
    public CodeContract.ICodeModel provideModel(ApiService apiService) {

        return new CodeModel(apiService);
    }
}
