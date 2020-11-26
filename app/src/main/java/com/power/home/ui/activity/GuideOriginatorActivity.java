package com.power.home.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.DensityUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.SystemUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.di.component.AppComponent;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.internal.CustomAdapt;

public class GuideOriginatorActivity extends BaseActivity   {



    private Handler hideHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            requestPhonePermissionStatic(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if (SharePreferencesUtils.isFirst()) {
                        if (UserCacheUtil.isLogin()) {
                            startActivity(new Intent(GuideOriginatorActivity.this, MainActivity.class));
                        } else {
                            //无论登录成功与否 都要去首页
                            Intent intent = new Intent(UIUtil.getContext(), LoginActivity.class);
                            intent.putExtra(Constant.from, "out");
                            startActivity(intent);
                        }
                    } else {
                        //第一次进入暂无标记
                        startActivity(new Intent(GuideOriginatorActivity.this, GuideActivity.class));
                    }
                    finish();
                }
            });

        }
    };
    @Override
    public int setLayout() {
        return R.layout.activity_originator;
    }

    @Override
    public void getExtras() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public boolean init() {


        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        hideHandler.sendEmptyMessageDelayed(0,3500);

        return true;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideHandler.removeCallbacksAndMessages(null);
    }


}
