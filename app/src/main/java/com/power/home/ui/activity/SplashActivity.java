package com.power.home.ui.activity;

import android.content.Intent;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.di.component.AppComponent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by zhangpeng on 2018/1/2.
 */

public class SplashActivity extends BaseActivity {
    @Override
    public int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean init() {

        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        jump();
                    }
                });

        return true;

    }

    @Override
    public void getExtras() {

    }

    @Override
    public void setListener() {

    }

    private void jump() {
        Intent intent = new Intent(this, GuideOriginatorActivity.class);
        startActivity(intent);
        finish();
        /*int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        //检查是否有读取联系人权限
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //无权限第二次打开App再次申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            //有权限了，对其进行操作

        }*/
//        requestPhonePermissionStatic(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                if (SharePreferencesUtils.isFirst()) {
//                    if (UserCacheUtil.isLogin()) {
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                    } else {
//                        //无论登录成功与否 都要去首页
//                        Intent intent = new Intent(UIUtil.getContext(), LoginActivity.class);
//                        intent.putExtra(Constant.from, "out");
//                        startActivity(intent);
//                    }
//                } else {
//                    //第一次进入暂无标记
//                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));
//                }
//                finish();
//            }
//        });


    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

}
