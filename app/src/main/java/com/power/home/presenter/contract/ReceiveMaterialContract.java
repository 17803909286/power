package com.power.home.presenter.contract;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ReceiveMaterialBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public interface ReceiveMaterialContract {
    interface IReceiveMaterialModel {

        Observable<BaseBean<ReceiveMaterialBean>> receiveMaterial();

    }

    interface View extends BaseView {

        void receiveMaterialSuces(ReceiveMaterialBean receiveMaterialBean);

    }
}
