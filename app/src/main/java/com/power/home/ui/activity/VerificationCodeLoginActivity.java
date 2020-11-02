package com.power.home.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.home.BuildConfig;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.RxCountDown;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerLoginComponent;
import com.power.home.di.module.CodeModule;
import com.power.home.di.module.LoginModule;
import com.power.home.presenter.CodePresenter;
import com.power.home.presenter.LoginPresenter;
import com.power.home.presenter.contract.CodeContract;
import com.power.home.presenter.contract.LoginContract;
import com.power.home.ui.widget.MyTitleBar;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by XWL on 2020/2/11.
 * Description: 验证码登录
 */

public class VerificationCodeLoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginContract.View, CodeContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_edit_clear)
    ImageView ivEditClear;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.view_phone_num)
    View viewPhoneNum;
    @BindView(R.id.view_verification_code)
    View viewVerificationCode;

    @Inject
    CodePresenter mCodePresenter;

    private RxCountDown rxCountDown;
    private String from;

    @Override
    public int setLayout() {
        return R.layout.activity_verification_code_login;
    }

    @Override
    public void getExtras() {
        from = getIntent().getStringExtra(Constant.from);
    }

    @Override
    public boolean init() {
        return false;
    }


    @Override
    public void setListener() {
        backListener(titleBar);
        etPhoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    viewPhoneNum.setBackgroundColor(getResources().getColor(R.color.colorBlue0D7EF9));
                } else {
                    viewPhoneNum.setBackgroundColor(getResources().getColor(R.color.colorBlackDCE2E8));
                }
            }
        });
        etVerificationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    viewVerificationCode.setBackgroundColor(getResources().getColor(R.color.colorBlue0D7EF9));
                } else {
                    viewVerificationCode.setBackgroundColor(getResources().getColor(R.color.colorBlackDCE2E8));
                }
            }
        });
        //手机号输入监听
        etPhoneNum.addTextChangedListener(new TextWatcher() {
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
                isEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivEditClear.setOnClickListener(this);
        tvGetVerificationCode.setOnClickListener(this);
        tvLogin.setOnClickListener(this);


        etVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void isEnable() {
        if (etPhoneNum.getText().toString().length() == 11 && etVerificationCode.getText().toString().length() == 4) {
            tvLogin.setEnabled(true);
        } else {
            tvLogin.setEnabled(false);
        }
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .codeModule(new CodeModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清空输入的手机号
            case R.id.iv_edit_clear:
                etPhoneNum.setText("");
                break;
            //获取验证码
            case R.id.tv_get_verification_code:
                String mobile = etPhoneNum.getText().toString();
                if (mobile.length() < 11) {
                    ToastUtils.shortShow("请输入完整的11位手机号");
                    return;
                }
                mCodePresenter.getCode(mobile);
                break;
            //登录
            case R.id.tv_login:
                tologin();
                break;
        }
    }

    private void tologin() {
        String phone = etPhoneNum.getText().toString();
        if (phone.length() < 11) {
            ToastUtils.shortShow("请输入完整的11位手机号");
            return;
        }
        String code = etVerificationCode.getText().toString();
        if (code.length() < 4) {
            ToastUtils.shortShow("请输入正确的验证码");
            return;
        }
        mPresenter.login(code, phone);
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

    @Override
    public void loginSuccess(UserInfoBean userInfoBean) {
        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.FINISH_Login, ""));
        SharePreferencesUtils.saveToken(userInfoBean.getToken());
        UserCacheUtil.saveInfo(userInfoBean.getUser());

        SharePreferencesUtils.clearFloatFlag();
//        if (StringUtil.isNotNullString(userInfoBean.getUser().getIsFirstRegistration()) && userInfoBean.getUser().getIsFirstRegistration().equals("true")) {
//            Intent intent = new Intent(this, ReferralCodeActivity.class);
//            intent.putExtra(Constant.from, from);
//            startActivity(intent);
//            finish();
//        } else {
        if (StringUtil.isNotNullString(from) && from.equals("out")) {
            goMain();
        } else {
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.LOGIN_BUY_REFRESH, ""));
            finish();
        }
//        }
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
