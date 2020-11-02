package com.power.home.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.home.App;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.GiftBean;
import com.power.home.di.component.DaggerGiftComponent;
import com.power.home.di.module.GiftModule;
import com.power.home.presenter.GiftPresenter;
import com.power.home.presenter.contract.GiftContract;
import com.power.home.ui.widget.MyPopupWindow;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler, GiftContract.View {

    private IWXAPI api;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    private MyPopupWindow mPopupWindow;

    @Inject
    GiftPresenter mGiftPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.transparencyBar2(this);
        StatusBarUtil.setStatusTextColor(false, this);
        DaggerGiftComponent.builder().appComponent(App.getApplication().getAppComponent())
                .giftModule(new GiftModule(this))
                .build().inject(this);
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq req) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.getType()) {
            case ConstantsAPI.COMMAND_SENDAUTH:
                //微信登录
                //登录回调
                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        String code = ((SendAuth.Resp) resp).code;
                        Log.e("ceshi", "onResp: " + code);
                        EventBusUtils.sendEvent(new EventBusEvent(EventBusUtils.EventCode.WX_LOGIN_CODE, code));
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                        finish();
                        break;
                    default:
                        finish();
                        break;
                }
                break;

            case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                //微信分享

                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
//						ToastUtils.shortShow("分享取消");
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_OK:
                        ToastUtils.shortShow("分享成功");
                        mGiftPresenter.activityGift("1");
                        break;
                }
                break;
        }
    }

    private void showPop(GiftBean giftBean) {

        mPopupWindow = new PopuUtil().initAtLocationPopuWrapOutFalse(this,
                R.layout.popup_redpackage);
        TextView tv_popup_red_title = mPopupWindow.getContentView().findViewById(R.id.tv_popup_red_title);
        TextView tv_popup_red_tip = mPopupWindow.getContentView().findViewById(R.id.tv_popup_red_tip);
        ImageView iv_popup_red_close = mPopupWindow.getContentView().findViewById(R.id.iv_popup_red_close);
        if (giftBean.getOutRedPackage().getMoneyIsReceiveFinish().equals("1")) {
            //已经领完
            tv_popup_red_title.setText("分享成功\n抱歉哦亲,本次分享暂无红包\n请再接再厉");
            tv_popup_red_title.setTextColor(UIUtil.getColor(R.color.colorAccent));
        } else {
            tv_popup_red_title.setText("分享成功\n恭喜您！获得" + StringUtil.getDecimalMoney(String.valueOf(giftBean.getOutRedPackage().getRedPackage().getMoneyValue() / 100)) + "元分享奖励\n已放入您的红包");
            tv_popup_red_title.setTextColor(UIUtil.getColor(R.color.colorRedFF4D4D));
        }
        tv_popup_red_tip.setText("说明：每天仅限" + giftBean.getDailyActivity().getTargetValue() + "个分享红包");
        iv_popup_red_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
    }

    @Override
    public void activityGiftSuccess(GiftBean giftBean) {

        if (null != giftBean && null != giftBean.getOutRedPackage() && !WXEntryActivity.this.isFinishing()) {
            Observable.timer(800, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            showPop(giftBean);
                        }
                    });

        } else {
            finish();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
        finish();
    }

    @Override
    public void dismissLoading() {

    }
}