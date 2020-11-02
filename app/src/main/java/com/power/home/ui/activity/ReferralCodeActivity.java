package com.power.home.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerReferralCodeComponent;
import com.power.home.di.module.ReferralCodeModule;
import com.power.home.presenter.ReferralCodePresenter;
import com.power.home.presenter.contract.ReferralCodeContract;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by XWL on 2020/2/11.
 * Description: 推荐码填写
 */
public class ReferralCodeActivity extends BaseActivity<ReferralCodePresenter> implements View.OnClickListener, ReferralCodeContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.et_referral_code)
    EditText etReferralCode;
    @BindView(R.id.tv_accomplish)
    TextView tvAccomplish;
    @BindView(R.id.iv_edit_clear)
    ImageView ivEditClear;
    @BindView(R.id.view_referral_code)
    View viewReferralCode;
    private String from;

    @Override
    public int setLayout() {
        return R.layout.activity_referral_code;
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
        etReferralCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    viewReferralCode.setBackgroundColor(getResources().getColor(R.color.colorBlue0D7EF9));
                } else {
                    viewReferralCode.setBackgroundColor(getResources().getColor(R.color.colorBlackDCE2E8));
                }
            }
        });
        //推荐码输入监听
        etReferralCode.addTextChangedListener(new TextWatcher() {
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
        tvAccomplish.setOnClickListener(this);
        titleBar.setRightLayoutClickListener(this);

    }

    private void isEnable() {
        if (etReferralCode.getText().toString().length() == 5 ) {
            tvAccomplish.setEnabled(true);
        } else {
            tvAccomplish.setEnabled(false);
        }
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerReferralCodeComponent.builder().appComponent(appComponent)
                .referralCodeModule(new ReferralCodeModule(this))
                .build().inject(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清空输入的推荐码
            case R.id.iv_edit_clear:
                etReferralCode.setText("");
                break;
            case R.id.tv_accomplish:
                String code = etReferralCode.getText().toString();
                if (StringUtil.isNotNullString(code)) {
                    mPresenter.setUserInvitedCode(code);
                } else {
                    UIUtil.showLongToastSafe("请输入推荐码");
                }
                break;
            case R.id.right_layout:
                onBackPressed();
                break;
        }
    }

    @Override
    public void setUserInvitedCodeSuccess() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (StringUtil.isNotNullString(from) && from.equals("out")) {
            goMain();
        } else {
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.LOGIN_BUY_REFRESH,"" ));
            finish();
        }
    }
}
