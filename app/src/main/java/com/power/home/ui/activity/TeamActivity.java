package com.power.home.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.TeamInfoBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerTeamComponent;
import com.power.home.di.module.TeamInfoModule;
import com.power.home.presenter.TeamInfoPresenter;
import com.power.home.presenter.contract.TeamInfoContract;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@Route(path = "/android/myTeam")
public class TeamActivity extends BaseActivity<TeamInfoPresenter> implements TeamInfoContract.View, View.OnClickListener {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tv_team_all_number)
    TextView tvTeamAllNumber;
    @BindView(R.id.tv_team_use_number)
    TextView tvTeamUseNumber;
    @BindView(R.id.tv_team_surplus_number)
    TextView tvTeamSurplusNumber;
    @BindView(R.id.tv_invite_vip_number)
    TextView tvInviteVipNumber;
    @BindView(R.id.ll_invite_vip)
    LinearLayout llInviteVip;
    @BindView(R.id.tv_invite_ambassador_number)
    TextView tvInviteAmbassadorNumber;
    @BindView(R.id.ll_invite_ambassador)
    LinearLayout llInviteAmbassador;
    @BindView(R.id.tv_invite_school_number)
    TextView tvInviteSchoolNumber;
    @BindView(R.id.ll_invite_school)
    LinearLayout llInviteSchool;
    private String queryType;

    @Override
    public int setLayout() {
        return R.layout.activity_team;
    }

    @Override
    public void getExtras() {
        queryType = getIntent().getStringExtra(Constant.type);
        if (StringUtil.isNotNullString(queryType)) {
            switch (queryType) {
                case "SCHOOLMASTER"://联盟校长
                    llInviteAmbassador.setVisibility(View.VISIBLE);
                    break;
                case "HEADQUARTERS"://总部
                    llInviteAmbassador.setVisibility(View.VISIBLE);
                    llInviteSchool.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public boolean init() {
        mPresenter.getTeamInfo();
        llInviteVip.setOnClickListener(this);
        llInviteAmbassador.setOnClickListener(this);
        llInviteSchool.setOnClickListener(this);
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerTeamComponent.builder().appComponent(appComponent)
                .teamInfoModule(new TeamInfoModule(this))
                .build().inject(this);
    }

    @Override
    public void getTeamInfoSuccess(TeamInfoBean teamInfoBean) {
        if (null != teamInfoBean) {
            tvTeamAllNumber.setText(teamInfoBean.getTeamTotalCount());
            tvTeamUseNumber.setText("已用名额:" + teamInfoBean.getUsedQuota());
            tvTeamSurplusNumber.setText("未用名额:" + teamInfoBean.getSurplusQuota());

            // 该身份只能查看上一级数据  AMBASSADOR--->只能看VIP
            switch (queryType) {
                case "AMBASSADOR":
                    tvInviteVipNumber.setText(teamInfoBean.getMyVip().getCount());
                    break;
                case "SCHOOLMASTER":
                    tvInviteVipNumber.setText(teamInfoBean.getMyVip().getCount());
                    tvInviteAmbassadorNumber.setText(teamInfoBean.getMyAmbassador().getCount());
                    break;
                case "HEADQUARTERS":
                    tvInviteVipNumber.setText(teamInfoBean.getMyVip().getCount());
                    tvInviteAmbassadorNumber.setText(teamInfoBean.getMyAmbassador().getCount());
                    tvInviteSchoolNumber.setText(teamInfoBean.getMySchoolMaster().getCount());
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_invite_vip:
                queryType = "VIP";
                break;
            case R.id.ll_invite_ambassador:
                queryType = "AMBASSADOR";
                break;
            case R.id.ll_invite_school:
                queryType = "SCHOOLMASTER";
                break;
        }
        ARouter.getInstance().build("/android/invitedRecord")
                .withString(Constant.type, queryType)
                .navigation();
    }
}
