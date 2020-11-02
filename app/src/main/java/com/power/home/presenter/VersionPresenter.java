package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.VersionBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.VersionContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class VersionPresenter extends BasePresenter<VersionContract.IVersionModel, VersionContract.View> {

    @Inject
    public VersionPresenter(VersionContract.IVersionModel iVersionModel, VersionContract.View view) {
        super(iVersionModel, view);
    }

    public void getVersion() {
        mModel.getVersion().compose(RxHttpReponseCompat.compatResult2())
                .subscribe(new ProgressSubcriber<Optional<VersionBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<VersionBean> versionBeanOptional) {
                        if (hasView()) {
                            mView.getVersionSucess(versionBeanOptional.get());
                        }
                    }
                });
    }

}
