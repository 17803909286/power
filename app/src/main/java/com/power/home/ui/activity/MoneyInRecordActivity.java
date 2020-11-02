package com.power.home.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.adapter.PagerFragmentAdapter;
import com.power.home.ui.fragment.MoneyInFragment;
import com.power.home.ui.widget.MyTitleBar;
import com.google.android.material.tabs.TabLayout;
import com.power.home.ui.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
public class MoneyInRecordActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager_money)
    MyViewPager viewPagerMoney;

    @Override
    public int setLayout() {
        return R.layout.activity_money_in_record;
    }

    @Override
    public void getExtras() {
    }

    @Override
    public boolean init() {
        initView();
        return false;
    }

    private void initView() {

        //收入类型:不传默认账户余额,CONVERT(待转化金额)
        List<Fragment> list = new ArrayList<>();
        list.add(getFragment(""));
        list.add(getFragment("CONVERT"));
        PagerFragmentAdapter pagerFragmentAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
        pagerFragmentAdapter.setFragments(list);
        viewPagerMoney.setAdapter(pagerFragmentAdapter);
        viewPagerMoney.setScanScroll(false);
        tablayout.setupWithViewPager(viewPagerMoney);
        //tab居中显示
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //tab不可滚动
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabIndicatorFullWidth(false);
        String[] names = {"账户余额", "待转化余额"};

        //添加tab文字
        int count = names.length;
        for (int i = 0; i < count; i++) {
            tablayout.getTabAt(i).setText(names[i]);
        }
        showLoading(false);
    }

    @Override
    public void setListener() {
        backListener(titleBar);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    private MoneyInFragment getFragment(String type) {
        MoneyInFragment moneyInFragment = new MoneyInFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.type, type);
        moneyInFragment.setArguments(bundle);
        return moneyInFragment;
    }

}
