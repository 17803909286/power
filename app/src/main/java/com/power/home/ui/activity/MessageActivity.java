package com.power.home.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.adapter.PagerFragmentAdapter;
import com.power.home.ui.fragment.MessageFragment;
import com.power.home.ui.widget.MyTitleBar;
import com.power.home.ui.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
@Route(path = "/android/message")
public class MessageActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager_message)
    MyViewPager viewPagerMessage;

    @Override
    public int setLayout() {
        return R.layout.activity_message;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {
        initView();
        String[] names = {"系统消息", "账户通知"};
        //添加tab文字
        int count = names.length;
        for (int i = 0; i < count; i++) {
            tablayout.getTabAt(i).setText(names[i]);
        }

        return false;
    }

    private void initView() {
        // 系统消息:SYSTEM   账户通知:ACCOUNT
        List<Fragment> list = new ArrayList<>();
        list.add(getFragment("SYSTEM"));
        list.add(getFragment("ACCOUNT"));
        PagerFragmentAdapter pagerFragmentAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
        pagerFragmentAdapter.setFragments(list);
        viewPagerMessage.setAdapter(pagerFragmentAdapter);
        viewPagerMessage.setScanScroll(false);
        tablayout.setupWithViewPager(viewPagerMessage);
        //tab居中显示
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //tab不可滚动
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabIndicatorFullWidth(false);
        showLoading(false);
    }

    @Override
    public void setListener() {
        backListener(titleBar);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    private MessageFragment getFragment(String type) {
        MessageFragment messageFragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.type, type);
        messageFragment.setArguments(bundle);
        return messageFragment;
    }

}