package com.power.home.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@Route(path = "/android/about")
public class AboutActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.tv_about_version)
    TextView tvAboutVersion;
    @BindView(R.id.tv_title_tip)
    TextView tvTitleTip;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.rl_secrect)
    RelativeLayout rlSecrect;

    @Override
    public int setLayout() {
        return R.layout.activity_about;
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
        rlAbout.setOnClickListener(this);
        rlUser.setOnClickListener(this);
        rlSecrect.setOnClickListener(this);
        tvAboutVersion.setText("当前版本" + UIUtil.getVersionName());
        tvTitleTip.setText("关于" + getString(R.string.app_name) + "app");
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_about:
                WebViewActivity.launcher(UIUtil.getContext(), Constant.BASE_URL + "/wlfx_mobile/about", "关于" + getString(R.string.app_name) + "app");
                break;
            case R.id.rl_user:
                WebViewActivity.launcher(UIUtil.getContext(), Constant.BASE_URL + "/wlfx_mobile/useragr", "用户协议");
                break;
            case R.id.rl_secrect:
                WebViewActivity.launcher(UIUtil.getContext(), Constant.BASE_URL + "/wlfx_mobile/privacyagr", "隐私协议");
                break;
        }
    }

}

