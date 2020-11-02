package com.power.home.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.RxCountDown;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerBindPhoneComponent;
import com.power.home.di.module.BindPhoneModule;
import com.power.home.di.module.CodeModule;
import com.power.home.presenter.BindPhonePresenter;
import com.power.home.presenter.CodePresenter;
import com.power.home.presenter.contract.BindPhoneContract;
import com.power.home.presenter.contract.CodeContract;
import com.power.home.ui.widget.MyTitleBar;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
public class BindPhoneTwoActivity extends BaseActivity<BindPhonePresenter> implements View.OnClickListener, BindPhoneContract.View, CodeContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_edit_clear)
    ImageView ivEditClear;
    @BindView(R.id.view_phone)
    View viewPhone;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.view_code)
    View viewCode;
    @BindView(R.id.button_bind_sure)
    Button buttonBindSure;
    private String oldPhone;

    @Inject
    CodePresenter mCodePresenter;

    private RxCountDown rxCountDown;


    @Override
    public int setLayout() {
        return R.layout.activity_bindphone_two;
    }

    @Override
    public void getExtras() {
        oldPhone = getIntent().getStringExtra(Constant.phone);
    }

    @Override
    public boolean init() {

        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    viewPhone.setBackgroundColor(getResources().getColor(R.color.colorBlue0D7EF9));
                } else {
                    viewPhone.setBackgroundColor(getResources().getColor(R.color.colorBlackDCE2E8));
                }
            }
        });
        etVerificationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    viewCode.setBackgroundColor(getResources().getColor(R.color.colorBlue0D7EF9));
                } else {
                    viewCode.setBackgroundColor(getResources().getColor(R.color.colorBlackDCE2E8));
                }
            }
        });

        //手机号输入监听
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 1) {
                    ivEditClear.setVisibility(View.VISIBLE);
                } else {
                    ivEditClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 11) {
                    tvGetVerificationCode.setEnabled(true);
                    if (StringUtil.isNotNullString(s.toString()) && StringUtil.isNotNullString(etVerificationCode.getText().toString())) {
                        buttonBindSure.setEnabled(true);
                    }
                } else {
                    tvGetVerificationCode.setEnabled(false);
                    buttonBindSure.setEnabled(false);
                }
            }
        });

        etVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.isNotNullString(s.toString()) && StringUtil.isNotNullString(etPhone.getText().toString())) {
                    buttonBindSure.setEnabled(true);
                } else {
                    buttonBindSure.setEnabled(false);
                }
            }
        });

        ivEditClear.setOnClickListener(this);
        tvGetVerificationCode.setOnClickListener(this);
        buttonBindSure.setOnClickListener(this);

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerBindPhoneComponent.builder().appComponent(appComponent)
                .bindPhoneModule(new BindPhoneModule(this))
                .codeModule(new CodeModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清空输入的手机号
            case R.id.iv_edit_clear:
                etPhone.setText("");
                break;
            //获取验证码
            case R.id.tv_get_verification_code:
                String mobile = etPhone.getText().toString();
                if (mobile.length() < 11) {
                    ToastUtils.shortShow("请输入完整的11位手机号");
                    return;
                }
                mCodePresenter.getCode(mobile);
                break;
            case R.id.button_bind_sure:
                toBind();
                break;
        }
    }

    private void toBind() {
        String phone = etPhone.getText().toString();
        if (phone.length() < 11) {
            ToastUtils.shortShow("请输入完整的11位手机号");
            return;
        }
        String code = etVerificationCode.getText().toString();
        if (code.length() < 4) {
            ToastUtils.shortShow("请输入正确的验证码");
            return;
        }
        mPresenter.changeMobile(code, phone, oldPhone);
    }

    @Override
    public void changeMobileSuccess() {
        UIUtil.showToastSafe("修改成功");
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != rxCountDown) {
            rxCountDown.stop();
            rxCountDown = null;
        }
    }

    @Override
    public void getCodeSuccess() {
        UIUtil.showToastSafe("发送成功");
        tvGetVerificationCode.setEnabled(false);
        rxCountDown = new RxCountDown();
        rxCountDown.startCountDown(60, new RxCountDown.ShowCountDown() {
            @Override
            public void show(int i) {
                tvGetVerificationCode.setText(i + "S后发送");
                if (i == 0) {
                    if (null != tvGetVerificationCode) {
                        tvGetVerificationCode.setEnabled(true);
                        tvGetVerificationCode.setText("重新发送");
                    }
                    rxCountDown.stop();
                    rxCountDown = null;
                }
            }
        });
    }
}
