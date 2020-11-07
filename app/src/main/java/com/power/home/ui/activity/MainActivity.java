package com.power.home.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.MyFragmentManager;
import com.power.home.common.util.AppInnerDownLoder;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.VersionBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerMainComponent;
import com.power.home.di.module.VersionModule;
import com.power.home.presenter.VersionPresenter;
import com.power.home.presenter.contract.VersionContract;
import com.power.home.ui.fragment.MainHomeFragment;
import com.power.home.ui.fragment.MainK12Fragment;
import com.power.home.ui.fragment.MainMineFragment;
import com.power.home.ui.service.MediaService;
import com.power.home.ui.widget.MyPopupWindow;
import com.power.home.ui.widget.floatview.FloatingView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<VersionPresenter> implements
        RadioGroup.OnCheckedChangeListener, VersionContract.View {

    @BindView(R.id.rbs_fragment)
    RadioGroup rbsFragment;
    @BindView(R.id.rl_content)
    LinearLayout rlContent;
    @BindView(R.id.rb_main_home)
    RadioButton rbMainHome;
    @BindView(R.id.rb_main_find)
    RadioButton rbMainFind;
    @BindView(R.id.rb_main_mine)
    RadioButton rbMainMine;
    private MainHomeFragment homeFragment;//首页
    private MainK12Fragment mainK12Fragment;//K12
    private MainMineFragment mineFragment;//我的
    private int mPosition = 0;
    private MyPopupWindow mExitPopupWindow;


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean init() {
        setClick(mPosition);
        mPresenter.getVersion();
        return true;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public void setListener() {
        rbsFragment.setOnCheckedChangeListener(this);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent)
                .versionModule(new VersionModule(this))
                .build().inject(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {

        switch (checkedID) {
            case R.id.rb_main_home: //首页
                mPosition = Constant.TAB_HOME;
                break;
            case R.id.rb_main_find:  //发现
                mPosition = Constant.TAB_FIND;
                break;
            case R.id.rb_main_mine:  //自我
                mPosition = Constant.TAB_MINE;
                break;
        }
        setClick(mPosition);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        exitPop();
    }

    private void exitPop() {

        mExitPopupWindow = new PopuUtil().initAtLocationPopuWrap(this,
                R.layout.popup_exit);
        TextView tvTitle = mExitPopupWindow.getContentView().findViewById(R.id.tv_title);
        Button buttonPopupCancel = mExitPopupWindow.getContentView().findViewById(R.id.button_creat_cancel);
        Button buttonPopupCommitSure = mExitPopupWindow.getContentView().findViewById(R.id.button_creat_commit_sure);
        tvTitle.setText("确定退出应用吗?");
        buttonPopupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExitPopupWindow.dismiss();
            }
        });
        buttonPopupCommitSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExitPopupWindow.dismiss();
                ActivityUtils.finishAllActivities();
                FloatingView.get().remove();
                stopService(new Intent(UIUtil.getContext(), MediaService.class));
                FloatingView.get().remove();
                finish();
            }
        });
    }


    @Override
    public void getVersionSucess(VersionBean versionBean) {
        if (versionBean.isIsPop()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //显示dialog
//                    new UpdateVerUtil(MainActivity.this).updateVer(versionBean);
                    new AppInnerDownLoder(MainActivity.this).updateVer(versionBean);
                }
            }, 500);
        }
    }


    /**
     * 切换Fragment
     *
     * @param type Fragment对应的角标
     */
    private void setClick(int type) {
        //开启事务
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //显示之前将所有的fragment都隐藏起来,在去显示我们想要显示的fragment
        hideFragment(fragmentTransaction);
        if(type==Constant.TAB_HOME || type == Constant.TAB_FIND){
            if(StringUtil.isNotNullString(SharePreferencesUtils.getFloatStatus())){
                if (null != FloatingView.get().getView()) {
                    FloatingView.get().getView().setVisibility(View.VISIBLE);
                }
            }

        }else{
            if (null != FloatingView.get().getView()) {
                FloatingView.get().getView().setVisibility(View.GONE);
            }
        }

        switch (type) {
            case Constant.TAB_HOME://成长
                if (homeFragment == null) {
                    homeFragment = new MainHomeFragment();
                    fragmentTransaction.add(R.id.fl_container, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                MyFragmentManager.getInstance().setCurrentFragment(homeFragment);
                break;

            case Constant.TAB_FIND://发现
                if (mainK12Fragment == null) {
                    mainK12Fragment = new MainK12Fragment();
                    fragmentTransaction.add(R.id.fl_container, mainK12Fragment);
                } else {
                    fragmentTransaction.show(mainK12Fragment);
                }
                MyFragmentManager.getInstance().setCurrentFragment(mainK12Fragment);
                break;
            case Constant.TAB_MINE://我的
                if (mineFragment == null) {
                    mineFragment = new MainMineFragment();
                    fragmentTransaction.add(R.id.fl_container, mineFragment);
                } else {
                    fragmentTransaction.show(mineFragment);
                }
                MyFragmentManager.getInstance().setCurrentFragment(mineFragment);
                break;
        }
        fragmentTransaction.commit();
    }



    /**
     * 用来隐藏fragment的方法
     *
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果此fragment不为空的话就隐藏起来
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (mainK12Fragment != null) {
            fragmentTransaction.hide(mainK12Fragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }
}
