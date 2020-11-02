package com.power.home.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.RxCountDown;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.BankInfoBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerBindBankComponent;
import com.power.home.di.module.BindBankModule;
import com.power.home.di.module.CodeModule;
import com.power.home.presenter.BindBankPresenter;
import com.power.home.presenter.CodePresenter;
import com.power.home.presenter.contract.BindBankContract;
import com.power.home.presenter.contract.CodeContract;
import com.power.home.ui.widget.MyTitleBar;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ZHP on 2020/7/17 0017.
 */
public class BindBankActivity extends BaseActivity<BindBankPresenter> implements BindBankContract.View, View.OnClickListener, CodeContract.View {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.et_bank_number)
    EditText etBankNumber;
    @BindView(R.id.et_bank_person_name)
    EditText etBankPersonName;
    @BindView(R.id.et_idcard_number)
    EditText etIdcardNumber;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_bank_phone)
    EditText etBankPhone;
    @BindView(R.id.et_bank_verify_code)
    EditText etBankVerifyCode;
    @BindView(R.id.button_convert_sure)
    Button buttonConvertSure;
    @BindView(R.id.tv_bank_get_verification_code)
    TextView tvBankGetVerificationCode;

    @Inject
    CodePresenter mCodePresenter;

    private RxCountDown rxCountDown;
    private BankInfoBean bankInfoBean;

    @Override
    public int setLayout() {
        return R.layout.activity_bind_bank_card;
    }

    @Override
    public void getExtras() {
        bankInfoBean = (BankInfoBean) getIntent().getSerializableExtra(Constant.data);
        if (null != bankInfoBean) {
            showInfo();
        }
    }

    private void showInfo() {
        etBankNumber.setText(bankInfoBean.getBankCardNum());
        etBankPersonName.setText(bankInfoBean.getAccountName());
        etBankName.setText(bankInfoBean.getBankName());
    }

    @Override
    public boolean init() {
        backListener(titleBar);
        return false;
    }

    @Override
    public void setListener() {
        tvBankGetVerificationCode.setOnClickListener(this);
        buttonConvertSure.setOnClickListener(this);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerBindBankComponent.builder().appComponent(appComponent)
                .bindBankModule(new BindBankModule(this))
                .codeModule(new CodeModule(this))
                .build().inject(this);
    }

    @Override
    public void bindBankCardInfoSuccess(BankInfoBean bankInfoBean) {
        UIUtil.showToastSafe("绑定成功");
        finish();
        Intent intent = new Intent(UIUtil.getContext(), BankInfoActivity.class);
        intent.putExtra(Constant.data, bankInfoBean);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        String mobile = etBankPhone.getText().toString();
        switch (view.getId()) {
            case R.id.button_convert_sure:
                String bankNumber = etBankNumber.getText().toString();
                String bankPersonName = etBankPersonName.getText().toString();
                String idcardNumber = etIdcardNumber.getText().toString();
                String bankName = etBankName.getText().toString();
                String bankVerifyCode = etBankVerifyCode.getText().toString();
                if (StringUtil.isNullString(bankNumber)) {
                    UIUtil.showToastSafe("请输入卡号");
                } else if (StringUtil.isNullString(bankPersonName)) {
                    UIUtil.showToastSafe("请输入持卡人姓名");
                } else if (StringUtil.isNullString(idcardNumber)) {
                    UIUtil.showToastSafe("请输入身份证号");
                } else if (StringUtil.isNullString(bankName)) {
                    UIUtil.showToastSafe("请输入银行名称");
                } else if (StringUtil.isNullString(mobile)) {
                    UIUtil.showToastSafe("请输入手机号");
                } else if (StringUtil.isNullString(bankVerifyCode)) {
                    UIUtil.showToastSafe("请输入验证码");
                } else if (null != bankInfoBean) {
                    //修改
                    mPresenter.bindBankCardInfo(bankPersonName, bankNumber, bankInfoBean.getBankId(), bankName, idcardNumber, mobile, bankVerifyCode);
                } else {
                    //绑定
                    mPresenter.bindBankCardInfo(bankPersonName, bankNumber, "", bankName, idcardNumber, mobile, bankVerifyCode);
                }
                break;
            //获取验证码
            case R.id.tv_bank_get_verification_code:
                if (mobile.length() < 11) {
                    ToastUtils.shortShow("请输入完整的11位手机号");
                    return;
                }
                mCodePresenter.getCode(mobile);
                break;
        }
    }

    @Override
    public void getCodeSuccess() {
        UIUtil.showToastSafe("发送成功");
        tvBankGetVerificationCode.setEnabled(false);
        rxCountDown = new RxCountDown();
        rxCountDown.startCountDown(60, new RxCountDown.ShowCountDown() {
            @Override
            public void show(int i) {
                tvBankGetVerificationCode.setText(i + "S后发送");
                if (i == 0) {
                    if (null != tvBankGetVerificationCode) {
                        tvBankGetVerificationCode.setEnabled(true);
                        tvBankGetVerificationCode.setText("重新发送");
                    }
                    rxCountDown.stop();
                    rxCountDown = null;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != rxCountDown) {
            rxCountDown.stop();
            rxCountDown = null;
        }
    }
}
