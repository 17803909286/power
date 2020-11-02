package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.SearchBean;
import com.power.home.data.bean.SearchHotWordBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface SearchContract {

    interface ISearchModel {
        Observable<BaseBean<List<SearchHotWordBean>>> searchWords();

        Observable<BaseBean<List<SearchBean>>> search(String title);

        Observable<BaseBean<List<String>>> getFindNearWord();

        Observable<BaseBean<EmptyBean>> deleteAll();
    }

    interface View extends BaseView {
        void searchWordsSuccess(List<SearchHotWordBean> searchHotWordBeans);

        void searchSuccess(List<SearchBean> searchBeans);

        void getFindNearWordSuccess(List<String> historys);

        void deleteAllSuccess();
    }

}
