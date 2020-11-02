package com.power.home.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.FontUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.GoShareBean;
import com.power.home.data.bean.UserAssetsBean;
import com.power.home.data.bean.WithdrawInfoBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerAccountComponent;
import com.power.home.di.module.AccountModule;
import com.power.home.presenter.AccountPresenter;
import com.power.home.presenter.contract.AccountContract;
import com.power.home.ui.widget.MyTitleBar;
import com.power.home.wxapi.WxShare;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
/**
 * Created by zhp on 2019-11-08
 */

@BindEventBus
@Route(path = "/android/myAccount")
public class AccountActivity extends BaseActivity<AccountPresenter> implements View.OnClickListener, AccountContract.View {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tv_account_balance)
    TextView tvAccountBalance;
    @BindView(R.id.tv_account_take_in)
    TextView tvAccountTakeIn;
    @BindView(R.id.rl_account_withdraw)
    RelativeLayout rlAccountWithdraw;
    @BindView(R.id.rl_account_list)
    RelativeLayout rlAccountList;
    @BindView(R.id.rl_account_record)
    RelativeLayout rlAccountRecord;
    @BindView(R.id.rl_bind_card)
    RelativeLayout rlBindCard;
    @BindView(R.id.tv_account_verify_status)
    TextView tvAccountVerifyStatus;
    @BindView(R.id.tv_card_info)
    TextView tvCardInfo;
    @BindView(R.id.rl_account_verify)
    RelativeLayout rlAccountVerify;
    private String accountBalance;
    private UserAssetsBean mUserAssetsBean;
    private String surplusQuota;
    private boolean hasBankInfo;

    @Override
    public int setLayout() {
        return R.layout.activity_account;
    }

    @Override
    public void getExtras() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getAppUserAssets();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date.getCode() == EventBusUtils.EventCode.WITHDRAWL_SUCESS || date.getCode() == EventBusUtils.EventCode.CERTIFICATION_SAVE_REFRESH) {
            mPresenter.getAppUserAssets();
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public boolean init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(UIUtil.getColor(R.color.colorBlue50A4FF));
        }
        mPresenter.getAppUserAssets();
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        rlAccountWithdraw.setOnClickListener(this);
        rlAccountList.setOnClickListener(this);
        rlAccountRecord.setOnClickListener(this);
        rlBindCard.setOnClickListener(this);
        rlAccountVerify.setOnClickListener(this);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerAccountComponent.builder().appComponent(appComponent)
                .accountModule(new AccountModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_account_withdraw:
                if (null != mUserAssetsBean && mUserAssetsBean.getIsAuth()) {
                    if (hasBankInfo) {
                        mPresenter.getWithdrawInfo();
                    } else {
                        //绑定银行卡
                        startActivity(new Intent(UIUtil.getContext(), BindBankActivity.class));
                    }

                } else {
                    UIUtil.showToastSafe("您未进行实名认证,请先进行实名认证");
                }
                break;
            case R.id.rl_bind_card:
                if (null != mUserAssetsBean && mUserAssetsBean.getIsAuth()) {
                    if (hasBankInfo) {
                        mPresenter.getBankCardInfo();
                    } else {
                        //绑定银行卡
                        startActivity(new Intent(UIUtil.getContext(), BindBankActivity.class));
                    }
                } else {
                    UIUtil.showToastSafe("您未进行实名认证,请先进行实名认证");
                }

                break;
            case R.id.rl_account_verify:
                startActivity(new Intent(UIUtil.getContext(), CertificationFristActivity.class));
                break;
            case R.id.rl_account_record:
                startActivity(new Intent(UIUtil.getContext(), WithDrawalRecordActivity.class));
                break;
            case R.id.rl_account_list:
                startActivity(new Intent(UIUtil.getContext(), MoneyInRecordActivity.class));
                break;
        }
    }

    @Override
    public void getAppUserAssetsSuccess(UserAssetsBean userAssetsBean) {
        if (null == userAssetsBean) {
            return;
        }
        mUserAssetsBean = userAssetsBean;
        accountBalance = userAssetsBean.getAccountBalance();
        tvAccountBalance.setText(StringUtil.getDecimalMoney(accountBalance));
        FontUtil.setFont(tvAccountBalance);
        tvAccountTakeIn.setText(StringUtil.getDecimalMoney(userAssetsBean.getAgencyIncome()));
        surplusQuota = userAssetsBean.getSurplusQuota();
        if (mUserAssetsBean.getIsAuth()) {
            tvAccountVerifyStatus.setText("已认证");
            tvAccountVerifyStatus.setTextColor(UIUtil.getColor(R.color.colorAccent));
            rlAccountVerify.setEnabled(false);
        } else {
            tvAccountVerifyStatus.setText("未认证");
            tvAccountVerifyStatus.setTextColor(UIUtil.getColor(R.color.colorRedFF4D4D));
            rlAccountVerify.setEnabled(true);
        }
        hasBankInfo = mUserAssetsBean.isHasBankInfo();
        if (hasBankInfo) {
            tvCardInfo.setText("我的银行卡");
        } else {
            tvCardInfo.setText("绑定银行卡");
        }
    }

    @Override
    public void getWithdrawInfoSuccess(WithdrawInfoBean withdrawInfoBean) {
        Intent intent = new Intent(UIUtil.getContext(), WithDrawalActivity.class);
        intent.putExtra(Constant.money, withdrawInfoBean);
        startActivity(intent);
    }

    @Override
    public void goShareSuccess(GoShareBean goShareBean) {
        new WxShare().WxShareUrl(goShareBean.getShareUrl(), goShareBean.getShareTitle(), goShareBean.getShareSubtitle(), goShareBean.getShareImg(), 1);
    }

    @Override
    public void getBankCardInfoSuccess(BankInfoBean bankInfoBean) {
        Intent intent = new Intent(UIUtil.getContext(), BankInfoActivity.class);
        intent.putExtra(Constant.data, bankInfoBean);
        startActivity(intent);
    }
}
