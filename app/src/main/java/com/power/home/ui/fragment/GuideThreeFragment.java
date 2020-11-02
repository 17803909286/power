package com.power.home.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.interfaces.OnMultiClickListener;
import com.power.home.ui.activity.LoginActivity;

import java.util.Objects;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */
public class GuideThreeFragment extends BaseFragment {
    @BindView(R.id.button_open)
    TextView buttonOpen;

    @Override
    public int setLayout() {
        return R.layout.fragment_guide_three;
    }

    @Override
    protected void getExtras() {

    }

    @Override
    public void init() {

    }

    @Override
    protected void setListener() {
        buttonOpen.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                SharePreferencesUtils.saveFirst(true);
                Intent intent = new Intent(UIUtil.getContext(), LoginActivity.class);
                intent.putExtra(Constant.from, "out");
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }
}
