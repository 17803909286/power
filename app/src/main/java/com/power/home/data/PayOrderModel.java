package com.power.home.data;



import com.power.home.common.util.ParamUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.PayOrderBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.PayOrderContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class PayOrderModel implements PayOrderContract.IPayOrderModel {

    private ApiService mApiService;

    public PayOrderModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<PayOrderBean>> CreateOrder(String amount, String channelId, String payChannel, String topicId, String userId) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("amount", amount);
        map.put("channelId", channelId);
        map.put("payChannel", payChannel);
        map.put("topicId", topicId);
        map.put("userId", userId);
        return mApiService.createOrder(ParamUtil.getBody(map));
    }
}
