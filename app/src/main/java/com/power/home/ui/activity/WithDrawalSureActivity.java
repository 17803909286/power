package com.power.home.ui.activity;

import android.view.View;
import android.widget.Button;

import com.power.home.R;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
public class WithDrawalSureActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.button_withdrawal_finish)
    Button buttonWithdrawalFinish;


    @Override
    public int setLayout() {
        return R.layout.activity_withdrawal_sure;
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
        buttonWithdrawalFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusUtils.sendEvent(new EventBusEvent(EventBusUtils.EventCode.WITHDRAWL_SUCESS, EventBusUtils.EventCode.WITHDRAWL_SUCESS));
                finish();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

}