package com.power.home.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.TimeUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ForwardAddressBean;
import com.power.home.data.bean.FreeExperienceBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.activity.WebViewActivity;
import com.power.home.ui.widget.GlideRoundTransform;

import butterknife.BindView;


public class MainHomeFreeFragment extends BaseFragment {

    @BindView(R.id.iv_course)
    ImageView ivCourse;
    @BindView(R.id.tv_play_amount)
    TextView tvPlayAmount;
    @BindView(R.id.tv_courseTime)
    TextView tvCourseTime;
    private FreeExperienceBean.ContentsBean bean;

    @Override
    public int setLayout() {
        return R.layout.fragment_main_home_free;
    }

    @Override
    protected void getExtras() {
        Bundle arguments = getArguments();
        bean = (FreeExperienceBean.ContentsBean) arguments.getSerializable(Constant.data);
        String displayCount = (bean.getDisplayCount() > 9999) ? StringUtil.getPlayCount(bean.getDisplayCount()) + "万" : bean.getDisplayCount() + "";
        tvPlayAmount.setText(displayCount);
        if (bean.getCourseTime() > 3600) {
            tvCourseTime.setText(TimeUtil.dateToString(bean.getCourseTime() * 1000, TimeUtil.dateFormat_hour2));
        } else {
            tvCourseTime.setText(TimeUtil.dateToString(bean.getCourseTime() * 1000, TimeUtil.dateFormat_minutes));
        }
        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(4))
                .placeholder(R.drawable.icon_place_holder_288_167_round)
                .error(R.drawable.icon_place_holder_288_167_round)
                .fallback(R.drawable.icon_place_holder_288_167_round);
        Glide.with(getActivity()).load(bean.getDisplayPic()).apply(requestOptions).into(ivCourse);
    }

    @Override
    public void init() {
    }

    @Override
    protected void setListener() {
        ivCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forwardAddress = bean.getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                switch (bean.getForwardType()) {
                    //1:原生   2:web
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.type, bean.getType())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), bean.getTitle());
                        break;
                }
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }
}
