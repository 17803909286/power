package com.power.home.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerConvertComponent;
import com.power.home.di.module.ConvertModule;
import com.power.home.presenter.ConvertPresenter;
import com.power.home.presenter.contract.ConvertContract;
import com.power.home.ui.widget.MyPopupWindow;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@Route(path = "/android/redeemCode")
public class ConvertActivity extends BaseActivity<ConvertPresenter> implements View.OnClickListener, ConvertContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.et_convert_code)
    EditText etConvertCode;
    @BindView(R.id.button_convert_sure)
    Button buttonConvertSure;
    @BindView(R.id.ll_content)
    LinearLayout llContent;


    @Override
    public int setLayout() {
        return R.layout.activity_convert;
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
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UIUtil.getContext(), ConvertRecordActivity.class);
                startActivity(intent);
            }
        });
        etConvertCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 10) {
                    buttonConvertSure.setEnabled(true);
                } else {
                    buttonConvertSure.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buttonConvertSure.setOnClickListener(this);

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerConvertComponent.builder().appComponent(appComponent)
                .convertModule(new ConvertModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_convert_sure:
                String exchangeCode = etConvertCode.getText().toString();
                mPresenter.codeMarket(exchangeCode);
                break;
        }
    }

    private void convertPopup(String date) {
        MyPopupWindow convertPopupWindow = new PopuUtil().initAtLocationPopuWrap(this,
                R.layout.popup_convert);
        TextView tvConvertDate = convertPopupWindow.getContentView().findViewById(R.id.tv_convert_date);
        Button buttonConvertStudy = convertPopupWindow.getContentView().findViewById(R.id.button_convert_study);
        tvConvertDate.setText("截止日期:" + date);
        buttonConvertStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertPopupWindow.dismiss();
                // 跳转到 变现营
//                finish();
//                ARouter.getInstance().build("/android/growth").navigation();
                goMain();
            }
        });
    }

    @Override
    public void codeMarketSuccess(String date) {
        convertPopup(date);
    }
}
