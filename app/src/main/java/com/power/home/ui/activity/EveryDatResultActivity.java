package com.power.home.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.ClipboardManagerUtil;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.GiftBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerGiftComponent;
import com.power.home.di.module.GiftModule;
import com.power.home.presenter.GiftPresenter;
import com.power.home.presenter.contract.GiftContract;
import com.power.home.ui.widget.MyTitleBar;

import butterknife.BindView;

@Route(path = "/android/everyDayResult")
public class EveryDatResultActivity extends BaseActivity<GiftPresenter> implements GiftContract.View, View.OnClickListener {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.tv_everyday_result_title)
    TextView tvEverydayResultTitle;
    @BindView(R.id.tv_everyday_result_code)
    TextView tvEverydayResultCode;
    @BindView(R.id.tv_everyday_result_copy)
    TextView tvEverydayResultCopy;
    @BindView(R.id.ll_everyday_result_vip)
    LinearLayout llEverydayResultVip;
    @BindView(R.id.tv_everyday_result_course_content)
    TextView tvEverydayResultCourseContent;
    @BindView(R.id.ll_everyday_result_course)
    LinearLayout llEverydayResultCourse;
    @BindView(R.id.iv_everyday_result_code)
    ImageView ivEverydayResultCode;
    @BindView(R.id.ll_everyday_result_offline)
    LinearLayout llEverydayResultOffline;
    @BindView(R.id.tv_everyday_result_button)
    TextView tvEverydayResultButton;
    private String type;
    private String id;
    private String exchangeCode;

    @Override
    public int setLayout() {
        return R.layout.activity_everyday_result;
    }

    @Override
    public void getExtras() {
        type = getIntent().getStringExtra(Constant.type);
        String id = getIntent().getStringExtra(Constant.id);
        mPresenter.activityGift(id);
        switch (type) {
            case "VIP":
                llEverydayResultVip.setVisibility(View.VISIBLE);
                tvEverydayResultButton.setVisibility(View.VISIBLE);
                tvEverydayResultButton.setText("去邀请");
                tvEverydayResultTitle.setText("恭喜您！获得动力营名额一个");
                break;
            case "COURSE":
                llEverydayResultCourse.setVisibility(View.VISIBLE);
                tvEverydayResultButton.setVisibility(View.VISIBLE);
                tvEverydayResultTitle.setText("恭喜您！获得专辑课程");
                break;
            case "OFFLINE":
                llEverydayResultOffline.setVisibility(View.VISIBLE);
                tvEverydayResultTitle.setText("恭喜您！获得线下课程名额");
                break;
        }
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        tvEverydayResultCopy.setOnClickListener(this);
        tvEverydayResultButton.setOnClickListener(this);
        ivEverydayResultCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ivEverydayResultCode.setDrawingCacheEnabled(true);
                ivEverydayResultCode.buildDrawingCache();
                Bitmap drawingCache = Bitmap.createBitmap(ivEverydayResultCode.getDrawingCache());
                if (ContextCompat.checkSelfPermission(EveryDatResultActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EveryDatResultActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    DrawableUtil.saveImageToGallery(drawingCache, "PowerHomeQrCode.jpg");
                    ivEverydayResultCode.destroyDrawingCache();
                }
                return false;
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerGiftComponent.builder().appComponent(appComponent)
                .giftModule(new GiftModule(this))
                .build().inject(this);
    }

    @Override
    public void activityGiftSuccess(GiftBean giftBean) {
        id = giftBean.getGitResult().getId();
        switch (type) {
            case "VIP":
                exchangeCode = giftBean.getGitResult().getExchangeCode();
                tvEverydayResultCode.setText("兑换码:" + exchangeCode);
                break;
            case "COURSE":
                tvEverydayResultCourseContent.setText("你已获得《" + giftBean.getGitResult().getTitle() + "》专辑课程，赶紧去学习吧！");
                break;
            case "OFFLINE":
                DrawableUtil.loadUrl(R.drawable.icon_place_holder_64_64, ivEverydayResultCode, giftBean.getGitResult().getQrCodeImg());
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_everyday_result_copy:
                if (StringUtil.isNotNullString(exchangeCode)) {
                    ClipboardManagerUtil.copy(exchangeCode, UIUtil.getContext());
                    UIUtil.showLongToastSafe("复制成功");
                }
                break;
            case R.id.tv_everyday_result_button:
                switch (tvEverydayResultButton.getText().toString()) {
                    case "去邀请":
                        ARouter.getInstance().build("/android/invitedFriend")
                                .navigation();
                        break;
                    case "去学习":
                        // 跳转播放专辑
                        if (StringUtil.isNotNullString(id)) {
                            ARouter.getInstance().build("/android/video")
                                    .withString(Constant.type, "1")//专辑
                                    .withString(Constant.id, id)
                                    .navigation();
                        }
                        break;
                }
                break;
        }
    }
}
