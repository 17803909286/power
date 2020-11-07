package com.power.home.wxapi;

import android.util.Log;

import com.power.home.common.Constant;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.PayOrderBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by XWL on 2020/3/18.
 * Description:微信支付调起微信
 */
public class WxPayUtil {
    public WxPayUtil( PayOrderBean payBean) {

        IWXAPI api = WXAPIFactory.createWXAPI(UIUtil.getContext(), Constant.APP_ID);

        if (!api.isWXAppInstalled()) {
            ToastUtils.shortShow("请先安装微信");
            return;
        }
        PayReq req = new PayReq();
        req.appId = payBean.getAppId();
        req.partnerId =payBean.getPartnerId();
        req.prepayId = payBean.getPrepayId();
        req.nonceStr = payBean.getNonceStr();
        req.timeStamp =payBean.getTimeStamp();
        req.packageValue = payBean.getPackageValue();
        req.sign = payBean.getSign();
        api.sendReq(req);
    }
}
