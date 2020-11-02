package com.power.home.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_edit_clear)
    ImageView ivEditClear;
    @BindView(R.id.button_bind)
    Button buttonBind;


    @Override
    public int setLayout() {
        return R.layout.activity_bindphone;
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
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 11) {
                    buttonBind.setEnabled(true);
                } else {
                    buttonBind.setEnabled(false);
                }
            }
        });
        ivEditClear.setOnClickListener(this);
        buttonBind.setOnClickListener(this);

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清空输入的手机号
            case R.id.iv_edit_clear:
                etPhoneNum.setText("");
                break;
            case R.id.button_bind:
                String oldPhone = etPhoneNum.getText().toString();
                if (!oldPhone.equals(SharePreferencesUtils.getPhone())) {
                    UIUtil.showLongToastSafe("手机号填写不正确，请重新填写");
                } else {
                    Intent intent = new Intent(UIUtil.getContext(), BindPhoneTwoActivity.class);
                    intent.putExtra(Constant.phone, oldPhone);
                    startActivity(intent);
                }
                break;
        }
    }
}
