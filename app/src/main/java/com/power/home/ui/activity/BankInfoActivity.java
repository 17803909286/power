package com.power.home.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.BankInfoBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by ZHP on 2020/7/17 0017.
 */
public class BankInfoActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tv_bank_info_name)
    TextView tvBankInfoName;
    @BindView(R.id.tv_bank_info_change)
    TextView tvBankInfoChange;
    @BindView(R.id.tv_bank_info_first)
    TextView tvBankInfoFirst;
    @BindView(R.id.tv_bank_info_end)
    TextView tvBankInfoEnd;
    private BankInfoBean bankInfoBean;

    @Override
    public int setLayout() {
        return R.layout.activity_bank_info;
    }

    @Override
    public void getExtras() {
        bankInfoBean = (BankInfoBean) getIntent().getSerializableExtra(Constant.data);
        if (null != bankInfoBean) {
            String bankCardNum = bankInfoBean.getBankCardNum();
            tvBankInfoName.setText(bankInfoBean.getBankName());
            tvBankInfoFirst.setText(bankCardNum.substring(0, 4));
            tvBankInfoEnd.setText(bankCardNum.substring(bankCardNum.length() - 4, bankCardNum.length()));
        }
    }

    @Override
    public boolean init() {
        backListener(titleBar);
        tvBankInfoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(UIUtil.getContext(), BindBankActivity.class);
                intent.putExtra(Constant.data, bankInfoBean);
                startActivity(intent);
            }
        });
        return false;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

}
