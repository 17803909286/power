package com.power.home.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.CacheUtils;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerLogoutComponent;
import com.power.home.di.module.LogoutModule;
import com.power.home.presenter.LogoutPresenter;
import com.power.home.presenter.contract.LogoutContract;
import com.power.home.ui.widget.MyPopupWindow;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@Route(path = "/android/setting")
public class SettingActivity extends BaseActivity<LogoutPresenter> implements View.OnClickListener, LogoutContract.View {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tv_setting_phone)
    TextView tvSettingPhone;
    @BindView(R.id.rl_setting_phone)
    RelativeLayout rlSettingPhone;
    @BindView(R.id.tv_setting_rom)
    TextView tvSettingRom;
    @BindView(R.id.rl_setting_clear)
    RelativeLayout rlSettingClear;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.button_logout)
    Button buttonLogout;
    private int count = 0;


    @Override
    public int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        String phone = SharePreferencesUtils.getPhone();
        rlSettingPhone.setOnClickListener(this);
        rlSettingClear.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        llContent.setOnClickListener(this);
        tvSettingPhone.setText(UIUtil.getPhone(phone));
        tvSettingRom.setText(CacheUtils.getInstance().getCacheSize() / 1024 / 1024 + "M");
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerLogoutComponent.builder().appComponent(appComponent)
                .logoutModule(new LogoutModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_logout:
                loginOutPop();
                break;
            case R.id.rl_setting_phone:
                startActivity(new Intent(UIUtil.getContext(), BindPhoneActivity.class));
                break;
            case R.id.rl_setting_clear:
                if (CacheUtils.getInstance().getCacheSize() == 0) {
                    UIUtil.showLongToastSafe("暂时没有产生缓存");
                } else {
                    clearCache();
                }
                break;
            case R.id.ll_content:
                count++;
                if (count == 5) {
                    UIUtil.showToastSafe("渠道:" + UIUtil.getMetaDataValue("channelName") +
                            "版本:" + UIUtil.getVersionName());
                    count = 0;
                }
                break;
        }
    }

    private void loginOutPop() {

        MyPopupWindow mLoginOutPopupWindow = new PopuUtil().initAtLocationPopuWrap(this,
                R.layout.popup_exit);
        TextView tvTitle = mLoginOutPopupWindow.getContentView().findViewById(R.id.tv_title);
        Button buttonPopupCancel = mLoginOutPopupWindow.getContentView().findViewById(R.id.button_creat_cancel);
        Button buttonPopupCommitSure = mLoginOutPopupWindow.getContentView().findViewById(R.id.button_creat_commit_sure);
        tvTitle.setText("确定退出登录？");
        buttonPopupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginOutPopupWindow.dismiss();
            }
        });
        buttonPopupCommitSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginOutPopupWindow.dismiss();
                mPresenter.logout();
            }
        });
    }

    private void clearCache() {
        MyPopupWindow clearPopupWindow = new PopuUtil().initAtLocationPopuWrap(this,
                R.layout.popup_exit);
        TextView tvTitle = clearPopupWindow.getContentView().findViewById(R.id.tv_title);
        Button buttonPopupCancel = clearPopupWindow.getContentView().findViewById(R.id.button_creat_cancel);
        Button buttonPopupCommitSure = clearPopupWindow.getContentView().findViewById(R.id.button_creat_commit_sure);
        tvTitle.setText("确定清除缓存?");
        buttonPopupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPopupWindow.dismiss();
            }
        });
        buttonPopupCommitSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CacheUtils.getInstance().clear();
                UIUtil.showLongToastSafe("缓存清除成功");
                clearPopupWindow.dismiss();
            }
        });
    }

    @Override
    public void logoutSuccess() {
        UserCacheUtil.loginOut();
        ActivityUtils.finishAllActivities();
        Intent intent = new Intent(UIUtil.getContext(), LoginActivity.class);
        intent.putExtra(Constant.from, "out");
        startActivity(intent);
    }
}
