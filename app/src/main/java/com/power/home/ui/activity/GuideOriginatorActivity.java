package com.power.home.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.power.home.R;
import com.power.home.di.component.AppComponent;

public class GuideOriginatorActivity extends BaseActivity{

    private Handler hideHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            finish();
        }
    };
    @Override
    public int setLayout() {
        return R.layout.activity_originator;
    }

    @Override
    public void getExtras() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        if(getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }

    }

    @Override
    public boolean init() {
        hideHandler.sendEmptyMessageDelayed(0,3000);
        return false;
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
