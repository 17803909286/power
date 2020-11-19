package com.power.home.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerEnrollComponent;
import com.power.home.di.module.EnrollModule;
import com.power.home.presenter.EnrollPresenter;
import com.power.home.presenter.contract.EnrollContract;
import com.power.home.ui.widget.MyPopupWindow;
import com.power.home.ui.widget.MyTitleBar;
import com.power.home.wxapi.WxShare;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-20
 */
@Route(path = "/android/offlineDetailsActivity")
public class CourseDetailsActivity extends BaseActivity<EnrollPresenter> implements EnrollContract.View, View.OnClickListener {
    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.button_course_join)
    Button buttonCourseJoin;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.webView)
    WebView webView;
    private String offlineActivityId;
    public ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;
    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;
    private CourseOfflineBean mCourseOfflineBean;

    @Override
    public int setLayout() {
        return R.layout.activity_course_details;
    }

    @Override
    public void getExtras() {
        offlineActivityId = getIntent().getStringExtra(Constant.id);
        mPresenter.getOfflineCourseDetails(offlineActivityId);
    }

    @Override
    public boolean init() {
        backListener(titleBar);
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
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    buttonCourseJoin.setVisibility(View.VISIBLE);
                } else {
                    buttonCourseJoin.setVisibility(View.GONE);
                }
            }

            //扩展浏览器上传文件
            //3.0++版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                openFileChooserImpl(uploadMsg);
            }

            // For Android > 5.0
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, FileChooserParams fileChooserParams) {
                openFileChooserImplForAndroid5(uploadMsg);
                return true;
            }

        });
        return false;
    }

    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
    }

    private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        mUploadMessageForAndroid5 = uploadMsg;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

    @Override
    public void setListener() {
        buttonCourseJoin.setOnClickListener(this);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPopupWindow offlinePopup = new PopuUtil().initAtBottomPopup(CourseDetailsActivity.this, R.layout.popup_offline);
                TextView tv_share_wx = offlinePopup.getContentView().findViewById(R.id.tv_share_wx);
                TextView tv_share_friends = offlinePopup.getContentView().findViewById(R.id.tv_share_friends);
                tv_share_wx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mCourseOfflineBean) {
                            new WxShare().WxShareUrl(mCourseOfflineBean.getWordPicDes(), mCourseOfflineBean.getName(), mCourseOfflineBean.getDescription(), mCourseOfflineBean.getShareIcon(), 1);
                        }
                    }
                });
                tv_share_friends.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mCourseOfflineBean) {
                            new WxShare().WxShareUrl(mCourseOfflineBean.getWordPicDes(), mCourseOfflineBean.getName(), mCourseOfflineBean.getDescription(), mCourseOfflineBean.getShareIcon(), 2);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerEnrollComponent.builder().appComponent(appComponent)
                .enrollModule(new EnrollModule(this))
                .build().inject(this);
    }

    private void joinCourse() {
        MyPopupWindow courseJoin = new PopuUtil().initAtLocationPopuWrap(this,
                R.layout.popup_course_join);
        TextView tvCourseJoinName = courseJoin.getContentView().findViewById(R.id.tv_course_join_name);
        TextView tvCourseJoinPhone = courseJoin.getContentView().findViewById(R.id.tv_course_join_phone);
        Button buttonPopupCancel = courseJoin.getContentView().findViewById(R.id.button_creat_cancel);
        Button buttonPopupCommitSure = courseJoin.getContentView().findViewById(R.id.button_creat_commit_sure);
        tvCourseJoinName.setText("姓名: " + SharePreferencesUtils.getNickName());
        tvCourseJoinPhone.setText("电话: " + SharePreferencesUtils.getPhone());
        buttonPopupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseJoin.dismiss();
            }
        });
        buttonPopupCommitSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseJoin.dismiss();
                if (UserCacheUtil.isLogin()) {
                    mPresenter.enroll(offlineActivityId);
                } else {
                    goLogin();
                }
            }
        });
    }

    @Override
    public void enrollSuccess() {
        UIUtil.showLongToastSafe("报名成功");
        finish();
    }

    @Override
    public void getOfflineCourseDetailsSuccess(CourseOfflineBean courseOfflineBean) {
        mCourseOfflineBean = courseOfflineBean;
        String url =  courseOfflineBean.getWordPicDes();
        Boolean isContainUrl = StringUtil.isContainUrl(url);
        if(isContainUrl){
               String urlString = StringUtil.getUrl(url);
            webView.loadUrl(urlString);
        }else{
            String htmlString = StringUtil.appendHtmlString(url);

            webView.loadDataWithBaseURL(null,htmlString,"text/html;charset=utf-8","utf-8",null);
        }

        if (StringUtil.isNotNullString(courseOfflineBean.getEnrollStatus())) {
            switch (courseOfflineBean.getEnrollStatus()) {
                case "ENROLL_NOT_START":
                    buttonCourseJoin.setEnabled(false);
                    buttonCourseJoin.setText("报名尚未开始");
//                    ivOfflineInvite.setVisibility(View.GONE);
                    break;
                case "ENROLL_END":
                    buttonCourseJoin.setEnabled(false);
                    buttonCourseJoin.setText("报名已结束");
//                    ivOfflineInvite.setVisibility(View.GONE);
                    break;
                case "ENROLL_START":
                    buttonCourseJoin.setEnabled(true);
                    buttonCourseJoin.setText("立即报名");
//                    ivOfflineInvite.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_course_join:
                if (UserCacheUtil.isLogin()) {
                    joinCourse();
                } else {
                    goLogin();
                }
                break;
        }
    }
}
