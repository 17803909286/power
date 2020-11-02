package com.power.home.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.power.home.R;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

public class PaySuccessActivity extends BaseActivity {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_immediately_study)
    TextView tvImmediatelyStudy;
    @BindView(R.id.tv_back_homepage)
    TextView tvBackHomepage;
    @BindView(R.id.iv_pay_success)
    ImageView ivPaySuccess;

    @Override
    public int setLayout() {
        return R.layout.activity_pay_success;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        initView();
        return false;
    }

    private void initView() {
        String productName = SharePreferencesUtils.getProductName();
        tvContent.setText(productName);
        if (productName.equals(getString(R.string.pay_vip_tip))) {
            ivPaySuccess.setImageResource(R.drawable.icon_pay_vip_success);
        } else {
            ivPaySuccess.setImageResource(R.drawable.icon_pay_success);
        }
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        tvImmediatelyStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvBackHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishAllActivities();
                goMain();
            }
        });

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.LOGIN_BUY_REFRESH, ""));


    }
}
