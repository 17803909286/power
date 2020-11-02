package com.power.home.presenter;

import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.CodeContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class CodePresenter extends BasePresenter<CodeContract.ICodeModel, CodeContract.View> {

    @Inject
    public CodePresenter(CodeContract.ICodeModel iCodeModel, CodeContract.View view) {
        super(iCodeModel, view);
    }

    public void getCode(String mobile) {
        mModel.getCode(mobile).compose(RxHttpReponseCompat.<EmptyBean>compatResult())
                .subscribe(new ProgressSubcriber<EmptyBean>(mContext,mView) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {
                        if(hasView()){
                            mView.getCodeSuccess();
                        }
                    }
                });
    }
}
