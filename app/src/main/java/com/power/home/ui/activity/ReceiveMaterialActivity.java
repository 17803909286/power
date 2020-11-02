package com.power.home.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.power.home.R;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ReceiveMaterialBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerReceiveMaterialComponent;
import com.power.home.di.module.ReceiveMaterialModule;
import com.power.home.presenter.ReceiverMaterialPresenter;
import com.power.home.presenter.contract.ReceiveMaterialContract;
import com.power.home.ui.widget.MyPopupWindow;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

@Route(path = "/android/ReceiveMaterialActivity")
public class ReceiveMaterialActivity extends BaseActivity<ReceiverMaterialPresenter> implements ReceiveMaterialContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.img_course)
    ImageView imgCourse;
    @BindView(R.id.tv_course_title)
    TextView tvCourseTitle;
    @BindView(R.id.tv_course_subtitle)
    TextView tvCourseSubtitle;
    @BindView(R.id.tv_free_rreceive)
    TextView tvFreeReceive;
    @BindView(R.id.webView)
    WebView webView;
    private MyPopupWindow myPopupWindow;
    private String displayPic;
    private String courseTopicTitle;
    private String courseTopicSubtitle;


    @Override
    public int setLayout() {
        return R.layout.activity_receive_material;
    }

    @Override
    public void getExtras() {
        Intent intent = getIntent();
        displayPic = intent.getStringExtra("displayPic");
        courseTopicTitle = intent.getStringExtra("courseTopicTitle");
        courseTopicSubtitle = intent.getStringExtra("courseTopicSubtitle");
    }

    @Override
    public boolean init() {
        initView();
        initWebView();
        return false;
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setLoadWithOverviewMode(true);//适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString() + "android");
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        String cacheDirPath = "/data/data/" + getPackageName() + "/databases/";
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        Log.i("WebActivity", "cacheDirPath=" + cacheDirPath);
        //设置  Application Caches 缓存目录
        webSettings.setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能

        webView.setBackgroundColor(UIUtil.getColor(R.color.colorWhiteEEEEEE));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setDatabasePath(cacheDirPath);
        }
        webView.loadUrl("http://www.image.yshili.com/tool_20200806174819.png");
    }

    private void initView() {

        Glide.with(this)
                .load(displayPic)
                .into(imgCourse);
        tvCourseTitle.setText(courseTopicTitle);
        tvCourseSubtitle.setText(courseTopicSubtitle);
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        tvFreeReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.receiveMaterial();
            }
        });
    }

    private void receivePopup(ReceiveMaterialBean receiveMaterialBean) {
        myPopupWindow = new PopuUtil().initAtLocationPopuWrap(this,
                R.layout.popup_receive);
        TextView tvTitle = myPopupWindow.getContentView().findViewById(R.id.tv_title);
        Button buttonPopupCommitSure = myPopupWindow.getContentView().findViewById(R.id.button_creat_commit_sure);
        ImageView iv_qrcode = myPopupWindow.getContentView().findViewById(R.id.iv_qrcode);
        iv_qrcode.setImageBitmap(DrawableUtil.createQRCodeBitmap(receiveMaterialBean.getQrCodeImg(), UIUtil.dip2px(160), UIUtil.dip2px(160)));
        buttonPopupCommitSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_qrcode.setDrawingCacheEnabled(true);
                iv_qrcode.buildDrawingCache();
                Bitmap drawingCache = Bitmap.createBitmap(iv_qrcode.getDrawingCache());
                if (ContextCompat.checkSelfPermission(ReceiveMaterialActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReceiveMaterialActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    DrawableUtil.saveImageToGallery(drawingCache, receiveMaterialBean.getName() + "_receive.jpg");
                }
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerReceiveMaterialComponent.builder().appComponent(appComponent )
                .receiveMaterialModule(new ReceiveMaterialModule(this))
                .build().inject(this);
    }


    @Override
    public void receiveMaterialSuces(ReceiveMaterialBean receiveMaterialBean) {
        receivePopup(receiveMaterialBean);
    }
}
