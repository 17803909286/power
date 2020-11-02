package com.power.home.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.power.home.R;
import com.power.home.common.util.StatusBarUtil;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZHP on 2019/1/4/0004.
 */

public class ScanActivity extends AppCompatActivity {


    @BindView(R.id.dbv)
    DecoratedBarcodeView mDBV;
    private CaptureManager captureManager;     //捕获管理器

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar2(this);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        mDBV.setStatusText("请将二维码置于扫描框中");
        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();

    }
}
