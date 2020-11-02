package com.power.home.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerWxLoginComponent;
import com.power.home.di.module.WxLoginModule;
import com.power.home.presenter.WxLoginPresenter;
import com.power.home.presenter.contract.WxLoginContract;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by xwl on 2020/02/10
 * 登录页  由于外部进入没有返回键，因为物理返回键默认关闭
 */
@BindEventBus
public class LoginActivity extends BaseActivity<WxLoginPresenter> implements WxLoginContract.View {
    @BindView(R.id.tv_verification_code)
    TextView tvVerificationCode;
    @BindView(R.id.tv_wx_login)
    TextView tvWxLogin;
    @BindView(R.id.tv_look_around)
    TextView tvLookAround;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.tv_secrect)
    TextView tvSecrect;

    private String from;//是否是第一次打开app "out"外部 "in" 内部
    private IWXAPI api;
    private ProgressDialog mProgressDialog;


    @Override
    public int setLayout() {
        return R.layout.activity_login;

    }

    @Override
    public void getExtras() {
        Intent intent = getIntent();
        from = intent.getStringExtra(Constant.from);
    }

    @Override
    public boolean init() {
        //测量手机状态栏的高度
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, statusBarHeight, 0, 0);
        llTitle.setLayoutParams(lp);
        //判断是否是外部打开app  控制返回按钮和随便看看的显示隐藏  "out"外部 "in" 内部
        if (StringUtil.isNotNullString(from) && from.equals("out")) {
            //第一次打开app
            ivBack.setVisibility(View.GONE);
            tvLookAround.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.VISIBLE);
            tvLookAround.setVisibility(View.GONE);
        }

        SpanUtils spanUtilsUser = new SpanUtils().append("用户协议").setFontSize(12, true).setForegroundColor(getResources().getColor(R.color.colorBlue0D7EF9)).setUnderline();
        tvUser.setText(spanUtilsUser.create());
        SpanUtils spanUtilsSecrect = new SpanUtils().append("隐私协议").setFontSize(12, true).setForegroundColor(getResources().getColor(R.color.colorBlue0D7EF9)).setUnderline();
        tvSecrect.setText(spanUtilsSecrect.create());
        return true;
    }


    @Override
    public void setListener() {
        //验证码登录
        tvVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goVerificationCode();
            }
        });
        //微信登录
        tvWxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWXLogin();
            }
        });
        //返回按钮
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //随便看看
        tvLookAround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
            }
        });
        //用户协议
        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.launcher(UIUtil.getContext(), Constant.BASE_URL + "/wlfx_mobile/useragr", "用户协议");
            }
        });
        //隐私协议
        tvSecrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.launcher(UIUtil.getContext(), Constant.BASE_URL + "/wlfx_mobile/privacyagr", "隐私协议");
            }
        });

    }


    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerWxLoginComponent.builder().appComponent(appComponent)
                .wxLoginModule(new WxLoginModule(this))
                .build().inject(this);
    }

    //微信登录
    private void goWXLogin() {
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);
        api.registerApp(Constant.APP_ID);


        if (!api.isWXAppInstalled()) {
            ToastUtils.shortShow("您的设备未安装微信客户端");
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);

        }
    }

    //验证码登录
    private void goVerificationCode() {
        Intent intent = new Intent(LoginActivity.this, VerificationCodeLoginActivity.class);
        intent.putExtra(Constant.from, from);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.WX_LOGIN_CODE:
                    String code = (String) date.getData();
//                    getAccessToken(code);
                    mPresenter.weChatLogin(code);
                    break;
                case EventBusUtils.EventCode.FINISH_Login:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void weChatLoginSuccess(UserInfoBean userInfoBean) {

        if (StringUtil.isNotNullString(userInfoBean.getUser().getPhone())) {
            UserCacheUtil.saveInfo(userInfoBean.getUser());
            SharePreferencesUtils.saveToken(userInfoBean.getToken());

            if (StringUtil.isNotNullString(from) && from.equals("out")) {
                goMain();
            } else {
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.LOGIN_BUY_REFRESH, ""));
                finish();
            }
        } else {
            Intent intent = new Intent(this, WechatBindPhoneActivity.class);
            intent.putExtra(Constant.userId, userInfoBean.getUser().getUserId());
            intent.putExtra(Constant.from, from);
            startActivity(intent);
        }
    }

}
