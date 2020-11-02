package com.power.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.EditInputFilter;
import com.power.home.common.util.FontUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.WithdrawInfoBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerWithdrawlComponent;
import com.power.home.di.module.WithdrawlModule;
import com.power.home.presenter.WithdrawlPresenter;
import com.power.home.presenter.contract.WithdrawlContract;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhp on 2019-11-08
 */
public class WithDrawalActivity extends BaseActivity<WithdrawlPresenter> implements View.OnClickListener, WithdrawlContract.View {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.button_withdrawal)
    Button buttonWithdrawal;
    @BindView(R.id.tv_withdrawl_bank)
    TextView tvWithdrawlBank;
    @BindView(R.id.tv_withdrawl_name)
    TextView tvWithdrawlName;
    @BindView(R.id.tv_withdrawl_balance)
    TextView tvWithdrawlBalance;
    @BindView(R.id.tv_withdrawl_all)
    TextView tvWithdrawlAll;
    @BindView(R.id.tv_withdrawl_tip)
    TextView tvWithdrawlTip;
    @BindView(R.id.et_withdrawl_number)
    EditText etWithdrawlNumber;
    @BindView(R.id.tv_money_symbol)
    TextView tvMoneySymbol;
    private WithdrawInfoBean withdrawInfoBean;

    @Override
    public int setLayout() {
        return R.layout.activity_withdrawal;
    }

    @Override
    public void getExtras() {
        withdrawInfoBean = (WithdrawInfoBean) getIntent().getSerializableExtra(Constant.money);
        tvWithdrawlBank.setText("到账银行卡:" + withdrawInfoBean.getBankCardNum());
        tvWithdrawlName.setText("姓名:" + withdrawInfoBean.getAccountName());
        tvWithdrawlTip.setText(withdrawInfoBean.getWithdrawShow());
        etWithdrawlNumber.setHint("(提现金额不小于" + withdrawInfoBean.getWithdrawTip() + "元)");
        tvWithdrawlBalance.setText("¥" + withdrawInfoBean.getAccountBalance());
        FontUtil.setFont(tvMoneySymbol);
        FontUtil.setFont(etWithdrawlNumber);
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        InputFilter[] filters = {new EditInputFilter(99999999, 2)};
        etWithdrawlNumber.setFilters(filters);
        etWithdrawlNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.isNotNullString(s.toString()) && (Double.valueOf(s.toString()) != 0)) {
                    etWithdrawlNumber.setTextSize(40);
                    buttonWithdrawal.setEnabled(true);

                } else {
                    etWithdrawlNumber.setTextSize(14);
                    buttonWithdrawal.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvWithdrawlAll.setOnClickListener(this);
        buttonWithdrawal.setOnClickListener(this);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerWithdrawlComponent.builder().appComponent(appComponent)
                .withdrawlModule(new WithdrawlModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_withdrawal:
                String number = etWithdrawlNumber.getText().toString();
                if (StringUtil.isNullString(number)) {
                    UIUtil.showToastSafe("请输入提现金额");
                } else if (StringUtil.isNotNullString(withdrawInfoBean.getWithdrawTip()) && Integer.valueOf(withdrawInfoBean.getWithdrawTip()) <= Double.valueOf(number)) {
                    mPresenter.withdraw(withdrawInfoBean.getAccountBalance(), number, withdrawInfoBean.getBankId());
                } else {
                    UIUtil.showToastSafe("提现金额不小于" + withdrawInfoBean.getWithdrawTip() + "元");
                }
                break;
            case R.id.tv_withdrawl_all:
                etWithdrawlNumber.setText(withdrawInfoBean.getAccountBalance());
                break;
        }
    }

    @Override
    public void withdrawSuccess() {
        startActivity(new Intent(UIUtil.getContext(), WithDrawalSureActivity.class));
        finish();
    }

}