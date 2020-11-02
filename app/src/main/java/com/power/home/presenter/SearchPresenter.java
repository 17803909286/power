package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.SearchBean;
import com.power.home.data.bean.SearchHotWordBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.SearchContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class SearchPresenter extends BasePresenter<SearchContract.ISearchModel, SearchContract.View> {

    @Inject
    public SearchPresenter(SearchContract.ISearchModel iSearchModel, SearchContract.View view) {
        super(iSearchModel, view);
    }

    public void searchWords() {
        mModel.searchWords().compose(RxHttpReponseCompat.<List<SearchHotWordBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<SearchHotWordBean>>(mContext, mView, true) {
                    @Override
                    public void onNext(List<SearchHotWordBean> searchHotWordBeans) {
                        if (hasView()) {
                            mView.searchWordsSuccess(searchHotWordBeans);
                        }
                    }
                });
    }

    public void search(String title) {
        mModel.search(title).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<List<SearchBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<SearchBean> searchBeans) {
                        if (hasView()) {
                            mView.searchSuccess(searchBeans);
                        }
                    }
                });
    }

    public void getFindNearWord() {
        mModel.getFindNearWord().compose(RxHttpReponseCompat.compatResult2())
                .subscribe(new ProgressSubcriber<Optional<List<String>>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<List<String>> listOptional) {
                        mView.getFindNearWordSuccess(listOptional.get());
                    }
                });
    }

    public void deleteAll() {
        mModel.deleteAll().compose(RxHttpReponseCompat.compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<EmptyBean> listOptional) {
                        mView.deleteAllSuccess();
                    }
                });
    }
}
