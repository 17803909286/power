package com.power.home.ui.fragment;


import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.power.home.R;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;

import butterknife.BindView;

/**
 * Created by xwl on 2019/11/12
 * 课程详情-简介
 */
public class CourseDetailsIntroductionFragment extends BaseFragment {


    @BindView(R.id.webView)
    public WebView webView;

    @Override
    public int setLayout() {
        return R.layout.fragment_course_details_introduction;
    }

    @Override
    protected void getExtras() {

    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {
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
        String cacheDirPath = "/data/data/" + UIUtil.getContext().getPackageName() + "/databases/";
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        Log.i("WebActivity", "cacheDirPath=" + cacheDirPath);
        //设置  Application Caches 缓存目录
        webSettings.setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能

        webView.setBackgroundColor(UIUtil.getColor(R.color.colorWhiteEEEEEE));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setDatabasePath(cacheDirPath);
        }


    }


    @Override
    protected void setListener() {

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }


}
