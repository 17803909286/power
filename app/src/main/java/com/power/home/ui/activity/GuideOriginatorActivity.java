package com.power.home.ui.activity;

import android.content.Context;
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
import com.power.home.common.util.DensityUtil;
import com.power.home.di.component.AppComponent;

import butterknife.BindView;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.internal.CustomAdapt;

public class GuideOriginatorActivity extends BaseActivity   {



    private Handler hideHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            finish();
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
        hideHandler.sendEmptyMessageDelayed(0,3000);

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
