package com.power.home.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.FloatMusicUtil;
import com.power.home.common.util.FontUtil;
import com.power.home.common.util.MusicList;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.CommodityBean;
import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.data.bean.Music;
import com.power.home.data.bean.ShareBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerCoursePlayerComponent;
import com.power.home.di.module.CoursePlayerModule;
import com.power.home.jzvd.JZDataSource;
import com.power.home.jzvd.JZUtils;
import com.power.home.jzvd.Jzvd;
import com.power.home.jzvd.JzvdStd;
import com.power.home.presenter.CoursePlayerPresenter;
import com.power.home.presenter.contract.CoursePlayerContract;
import com.power.home.ui.fragment.CourseDetailsCatalogueFragment;
import com.power.home.ui.fragment.CourseDetailsIntroductionFragment;
import com.power.home.ui.service.MediaService;
import com.power.home.ui.widget.MyJzPlayer;
import com.power.home.ui.widget.MySharePopup;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import butterknife.BindView;

import static android.view.View.VISIBLE;
import static com.power.home.jzvd.Jzvd.STATE_PLAYING;
import static com.power.home.jzvd.Jzvd.WIFI_TIP_DIALOG_SHOWED;


@BindEventBus
@Route(path = "/android/video")
public class CoursePlayerActivity extends BaseActivity<CoursePlayerPresenter> implements CoursePlayerContract.View, View.OnClickListener {


    @BindView(R.id.mJC)
    MyJzPlayer myJzvdStd;


    Jzvd.JZAutoFullscreenListener mSensorEventListener;
    SensorManager mSensorManager;//重力感应
    @BindView(R.id.tv_course_title)
    TextView tvCourseTitle;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_course_subtitle)
    TextView tvCourseSubtitle;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_album_buy)
    TextView tvAlbumBuy;
    @BindView(R.id.ll_album_buy)
    LinearLayout llAlbumBuy;
    @BindView(R.id.ll_bottom_buy)
    LinearLayout ll_bottom_buy;
    @BindView(R.id.tv_growth_buy)
    TextView tvGrowthBuy;
    @BindView(R.id.tv_album_state)
    TextView tvAlbumState;
    @BindView(R.id.tv_toGroth)
    TextView tvToGroth;
    @BindView(R.id.tv_receive)
    TextView tvReceive;
    @BindView(R.id.tv_course_size)
    TextView tv_course_size;


    private String id;//课程或者专辑id
    private String type;//课程或者专辑

    private Jzvd.AlbumOrientationEventListener albumOrientationEventListener;
    private ArrayList<Fragment> mFragmentList;
    private CourseDetailsCatalogueFragment catalogueFragment;//课程选集
    private CourseDetailsIntroductionFragment introductionFragment;//课程简介
    private CoursePlayerBean coursePlayerBean;
    private int position;//视频列表脚本
    private String coursesId;
    private String coruseTitle;
    private String courseSubtitle;
    private Sensor accelerometerSensor;
    private boolean isClickAudio = false;
    private String shareIcon;
    private String courseDisplayPic;
    private int seek;
    private Music fromMusic;
    private boolean canPlay;
    private Animation rotateAnimation;
    private boolean isPlay;
    private String from;
    private Music currentMusic;//进入此页面的音频文件

    @Override
    public int setLayout() {
        setStatusBarColor(this, getResources().getColor(R.color.colorBlack));
        return R.layout.activity_course_player;

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (null != intent) {
            id = intent.getStringExtra(Constant.id);
            type = intent.getStringExtra(Constant.type);
            from = intent.getStringExtra(Constant.from);
            fromMusic = (Music) intent.getSerializableExtra(Constant.seek);//浮窗进入携带数据
            if (StringUtil.isNullString(from) && null != MediaService.mMediaPlayer) {//正常进入 可能播放 也可能没在播放音频 全都暂停 打开的不是点击浮窗的资源，默认播放视频,暂停掉上次的音频
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_STOP, EventBusUtils.EventCode.SERVICE_STOP));
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE, EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE));
            }
        }
    }

    @Override
    public void getExtras() {
        id = getIntent().getStringExtra(Constant.id);
        type = getIntent().getStringExtra(Constant.type);
        from = getIntent().getStringExtra(Constant.from);
        fromMusic = (Music) getIntent().getSerializableExtra(Constant.seek);//浮窗进入携带数据

        if (StringUtil.isNullString(from) && null != MediaService.mMediaPlayer) {//正常进入 可能播放 也可能没在播放音频 全都暂停 打开的不是点击浮窗的资源，默认播放视频,暂停掉上次的音频
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_STOP, EventBusUtils.EventCode.SERVICE_STOP));
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE, EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE));
        }
    }

    @Override
    public boolean init() {
        initView();
        initTabLayout();
        mPresenter.getCourseDetails(type, id);
        return false;
    }

    private void initView() {
        rotateAnimation = AnimationUtils.loadAnimation(UIUtil.getContext(), R.anim.rotate_anim);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnimation.setInterpolator(lin);
        tablayout.setTabRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new Jzvd.JZAutoFullscreenListener();
        albumOrientationEventListener = new Jzvd.AlbumOrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL);

        if (albumOrientationEventListener.canDetectOrientation()) {
            albumOrientationEventListener.enable();
        }
//        myJzvdStd.onStateAutoComplete();

    }

    private void initPlayer(CoursePlayerBean.CoursesBean coursesBean) {

        //https://up.imgupio.com/demo/birds.m3u8
        //http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4
//        String url = "http://www.test.video.wlfxdata.com/test_yinping1_10_video_index_file.m3u8";
        coursesId = coursesBean.getId();//当前播放课程id
        coruseTitle = coursesBean.getTitle();
        courseSubtitle = coursesBean.getSubtitle();
        courseDisplayPic = coursesBean.getDisplayPic();
        shareIcon = coursesBean.getShareIcon();
        myJzvdStd.tvCourseName.setText(coruseTitle);
        canPlay = coursesBean.isCanPlay();

        LinkedHashMap map = new LinkedHashMap();
        String[] videoUrls = coursesBean.getVideoUrl().split(";");
        map.put("高清", videoUrls[1]);
        map.put("标清", videoUrls[0]);

        JZDataSource jzDataSource = new JZDataSource(map, coursesBean.getTitle());
        jzDataSource.currentUrlIndex = 1;
        jzDataSource.currentSpeedIndex = 1;
        jzDataSource.isCanplay = coursesBean.isCanPlay();
        myJzvdStd.tv_speed.setText("倍速");

        if (myJzvdStd.screen == myJzvdStd.SCREEN_FULLSCREEN) {
            myJzvdStd.setUp(jzDataSource, JzvdStd.SCREEN_FULLSCREEN);
        } else {
            myJzvdStd.setUp(jzDataSource, JzvdStd.SCREEN_NORMAL);
        }

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_place_holder_375_211)
                .error(R.drawable.icon_place_holder_375_211)
                .fallback(R.drawable.icon_place_holder_375_211);

        Glide.with(this).load(coursesBean.getCourseCover()).apply(requestOptions).into(myJzvdStd.thumbImageView);
        if (seek != 0) {//外部悬浮携带进度进入
            myJzvdStd.seekToInAdvance = seek;
        } else {
            myJzvdStd.seekToInAdvance = coursesBean.getLatestProgress() * 1000;
        }
        playVideo(coursesBean.isFree(), coursesBean.isCanPlay());

    }

    /**
     * 播放视频
     */
    private void playVideo(boolean isFree, boolean isCanPlay) {
        if (isFree) {
            //当前课程为免费课程
            myJzvdStd.my_audio_video_change.setVisibility(VISIBLE);
            myJzvdStd.rl_buy_album.setVisibility(View.GONE);
            if (isClickAudio) {
                //音频 不需要处理
            } else {
                //视频
                if (!JZUtils.isWifiConnected(this) && !WIFI_TIP_DIALOG_SHOWED) {
                    myJzvdStd.showWifiDialog();
                    return;
                } else {
                    myJzvdStd.startVideo();
                }
            }
        } else {
            //当前课程为收费课程
            if (isCanPlay) {
                // 能够播放
                myJzvdStd.my_audio_video_change.setVisibility(VISIBLE);
                myJzvdStd.rl_buy_album.setVisibility(View.GONE);
                if (isClickAudio) {
                    //音频 不需要处理
                } else {
                    //视频
                    if (!JZUtils.isWifiConnected(this) && !WIFI_TIP_DIALOG_SHOWED) {
                        myJzvdStd.showWifiDialog();
                        return;
                    } else {
                        myJzvdStd.startVideo();
                    }
                }
            } else {
                //不能播放
                myJzvdStd.my_audio_video_change.setVisibility(View.GONE);
                myJzvdStd.rl_buy_album.setVisibility(VISIBLE);
                myJzvdStd.tv_album_price.setText("¥" + coursePlayerBean.getPrice() + "/年 购买本专辑");
                myJzvdStd.tv_album_hint.setText("您尚未购买本专辑，当前课程无法观看");
                if (myJzvdStd.screen == myJzvdStd.SCREEN_FULLSCREEN) {
                    myJzvdStd.gotoScreenNormal();
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.VIDEO_BEAN:
                    if (UserCacheUtil.isLogin()) {
                        mPresenter.setStudyProgress(coursesId, false, myJzvdStd.mediaInterface.getCurrentPosition() / 1000 + "");
                    }
                    seek = 0;
                    CoursePlayerBean.CoursesBean clickBean = (CoursePlayerBean.CoursesBean) date.getData();
                    position = clickBean.getPosition();
                    myJzvdStd.antholigyAdapter.setSelectPosition(position);
                    prepareNextMusic();
                    initPlayer(clickBean);

                    break;

                case EventBusUtils.EventCode.VIDEO_COMPLETE://切换下一节课
                    myJzvdStd.civHead.clearAnimation();
                    if (UserCacheUtil.isLogin()) {
                        mPresenter.setStudyProgress(coursesId, true, "0");
                    }
                    seek = 0;
                    Jzvd.releaseAllVideos();
                    if (coursePlayerBean != null) {
                        if (position + 1 == coursePlayerBean.getCourses().size()) {
                            position = 0;
                        } else {
                            position++;
                        }
                        catalogueFragment.catalogueAdapter.setSelectPosition(position);
                        myJzvdStd.antholigyAdapter.setSelectPosition(position);
                        CoursePlayerBean.CoursesBean coursesNextBean = coursePlayerBean.getCourses().get(position);
                        prepareNextMusic();
                        initPlayer(coursesNextBean);
                    }
                    break;
                case EventBusUtils.EventCode.LOGIN_BUY_REFRESH:
                    mPresenter.getCourseDetails(type, id);
                    type = "1";
                    break;
                case EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_SECOND:
                    Music musicSecond = (Music) date.getData();
                    myJzvdStd.tvAudioCurrent.setText(JZUtils.stringForTime(musicSecond.getCurrentPosition()));
                    myJzvdStd.tvAudioTotal.setText(JZUtils.stringForTime(musicSecond.getDuration()));
                    myJzvdStd.seekProgress.setMax(musicSecond.getDuration());
                    myJzvdStd.seekProgress.setProgress(musicSecond.getCurrentPosition());
                    break;
                case EventBusUtils.EventCode.SERVICE_FLOAT_AUDIO_PLAY_PAUSE:
                    Music music = (Music) date.getData();
                    seek = music.getCurrentPosition();
                    if (isPlay) {
                        //正在播放 ---->暂停---->展示播放键
                        isPlay = false;
                        myJzvdStd.civHead.clearAnimation();
                        myJzvdStd.ivAudioStart.setImageDrawable(UIUtil.getDrawable(R.drawable.jz_click_play_selector));
                        currentMusic.setPlaying(false);
                        if (null != MediaService.mMediaPlayer) {
                            seek = MediaService.mMediaPlayer.getCurrentPosition();
                        }
                    } else {
                        isPlay = true;
                        myJzvdStd.civHead.startAnimation(rotateAnimation);
                        myJzvdStd.ivAudioStart.setImageDrawable(UIUtil.getDrawable(R.drawable.jz_click_pause_selector));
                        currentMusic.setPlaying(true);
                    }
                    break;
            }
        }
    }

    /**
     * 切换下一集 或者 准备下一集
     */
    private void prepareNextMusic() {
        currentMusic = MusicList.getMusic(position);
        myJzvdStd.tvAudioCurrent.setText(JZUtils.stringForTime(0));
        myJzvdStd.tvAudioTotal.setText(JZUtils.stringForTime(currentMusic.getDuration()));
        currentMusic.setAudio(isClickAudio);
        if (isClickAudio) {
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PAUSE, currentMusic));
            if (StringUtil.isNotNullString(currentMusic.getUrl()) && currentMusic.isCanPlay()) {
                //默认点击下一集 自动播放 更换按钮状态
                currentMusic.setPlaying(true);
                myJzvdStd.civHead.startAnimation(rotateAnimation);
                myJzvdStd.ivAudioStart.setImageDrawable(UIUtil.getDrawable(R.drawable.jz_click_pause_selector));
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NEXT, currentMusic));
            } else {
                currentMusic.setPlaying(false);
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PAUSE, currentMusic));
                //点击不支持音频 或者 点击之前是视频   需要切换回视频页面
                //展示视频页面
                changeToVideo();
            }
        } else {
            if (StringUtil.isNotNullString(currentMusic.getUrl()) && currentMusic.isCanPlay()) {
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NEXT_PREPARE, currentMusic));
            } else {
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PAUSE, currentMusic));
                //点击不支持音频 或者 点击之前是视频   需要切换回视频页面
                //展示视频页面
                changeToVideo();
            }

        }
    }


    /*
     * 简介  目录  互动 Tab
     */
    private void initTabLayout() {

        String[] tabTips = {"专辑简介", "课程选集"};
        mFragmentList = new ArrayList<>();
        introductionFragment = new CourseDetailsIntroductionFragment();
        mFragmentList.add(introductionFragment);
        catalogueFragment = new CourseDetailsCatalogueFragment();
        mFragmentList.add(catalogueFragment);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTips[position];
            }
        });
        viewPager.setCurrentItem(1);
        tablayout.setupWithViewPager(viewPager);

    }

    @Override
    public void setListener() {
        StatusBarUtil.setBarDarkMode(this, false);
        myJzvdStd.mIvShare.setOnClickListener(this);
        myJzvdStd.tv_album_price.setOnClickListener(this);
        myJzvdStd.buyShare.setOnClickListener(this);
        tvGrowthBuy.setOnClickListener(this);
        llAlbumBuy.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        myJzvdStd.my_audio_video_change.setOnClickListener(this);
        tvToGroth.setOnClickListener(this);
        tvReceive.setOnClickListener(this);
        myJzvdStd.ivAudioStart.setOnClickListener(this);
        myJzvdStd.ivAudioBack.setOnClickListener(this);
        myJzvdStd.ivAudioShare.setOnClickListener(this);
        myJzvdStd.seekProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myJzvdStd.tvAudioCurrent.setText(JZUtils.stringForTime(seekBar.getProgress()));
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_SEEK, seekBar.getProgress()));
            }
        });
        myJzvdStd.antholigyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int pos) {
                Jzvd.releaseAllVideos();
                position = pos;
                catalogueFragment.catalogueAdapter.setSelectPosition(position);
                myJzvdStd.antholigyAdapter.setSelectPosition(position);
                initPlayer(coursePlayerBean.getCourses().get(position));
                if (myJzvdStd.antholigyPopWindow != null) {
                    myJzvdStd.antholigyPopWindow.dismiss();
                }
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCoursePlayerComponent.builder().appComponent(appComponent)
                .coursePlayerModule(new CoursePlayerModule(this))
                .build().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isClickAudio) {
            accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
            //home back
            Jzvd.goOnPlayOnResume();
        }
    }


    @Override
    public void getCourseDetailsSuces(CoursePlayerBean coursePlayerBean) {
        this.coursePlayerBean = coursePlayerBean;
        myJzvdStd.my_audio_video_change.setVisibility(VISIBLE);//涉及音频进入切换问题，数据成功以后再显示

        catalogueFragment.catalogueAdapter.setNewData(coursePlayerBean.getCourses());
        myJzvdStd.antholigyAdapter.setNewData(coursePlayerBean.getCourses());
        String descriptionUrl = coursePlayerBean.getDescriptionUrl();
        Boolean isContainUrl = StringUtil.isContainUrl(descriptionUrl);
        if(isContainUrl){
            String urlString = StringUtil.getUrl(descriptionUrl);
            introductionFragment.webView.loadUrl(urlString);
        }else{
            String htmlString = StringUtil.appendHtmlString(descriptionUrl);
            introductionFragment.webView.loadDataWithBaseURL(null, htmlString,"text/html;charset=utf-8","utf-8",null);
        }
        introductionFragment.webView.loadUrl(descriptionUrl);
        if (null != fromMusic) {//悬浮窗进入
            seek = fromMusic.getCurrentPosition();
        }
        if (!StringUtil.isEmptyList(coursePlayerBean.getCourses())) {
            if (StringUtil.isEquals(type, "2")) {
                for (int i = 0; i < coursePlayerBean.getCourses().size(); i++) {
                    if (StringUtil.isEquals(id, coursePlayerBean.getCourses().get(i).getId())) {
                        position = i;
                        catalogueFragment.catalogueAdapter.setSelectPosition(i);
                        myJzvdStd.antholigyAdapter.setSelectPosition(i);
                        initPlayer(coursePlayerBean.getCourses().get(i));
                        break;
                    }
                }
            } else {
                catalogueFragment.catalogueAdapter.setSelectPosition(position);
                catalogueFragment.catalogueAdapter.setSelectPosition(position);
                initPlayer(coursePlayerBean.getCourses().get(position));
            }
        }
        initService();
        FontUtil.setFont(tvAlbumBuy);
        tvCourseTitle.setText(coursePlayerBean.getCourseTopicTitle());
        tvCourseSubtitle.setText(coursePlayerBean.getCourseTopicSubtitle());
        if (coursePlayerBean.isContinueUpdating()) {
            tv_course_size.setText("更新至" + coursePlayerBean.getCourseSize() + "节/共" + coursePlayerBean.getCourseTotalSize() + "节");
        } else {
            tv_course_size.setText("已完结/共" + coursePlayerBean.getCourseTotalSize() + "节");
        }
        ll_bottom_buy.setVisibility(VISIBLE);
        if (StringUtil.isEmpty(coursePlayerBean.getBelongsTo())) {
            if (coursePlayerBean.isBuy()) {
                //专辑已购买
                llAlbumBuy.setVisibility(View.VISIBLE);
                tvAlbumBuy.setVisibility(View.GONE);
                tvGrowthBuy.setVisibility(View.GONE);
                tvAlbumState.setText("专辑已购买");
//                tvGrowthBuy.setText("加入动力营免费学");
                tvAlbumState.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            } else {
                llAlbumBuy.setVisibility(VISIBLE);
                tvAlbumBuy.setVisibility(View.GONE);
                tvGrowthBuy.setVisibility(View.GONE);
                tvAlbumState.setText("购买专辑 ¥" + coursePlayerBean.getPrice() + "/年");
//                tvGrowthBuy.setText("加入动力营免费学");
                llAlbumBuy.setBackgroundColor(getResources().getColor(R.color.colorBlue0D7EF9));
                tvAlbumState.setTextColor(getResources().getColor(R.color.colorWhite));
                tvAlbumState.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            }
        } else {
            if (coursePlayerBean.isVip()) {
                //动力营已购买
                llAlbumBuy.setVisibility(View.GONE);
                tvGrowthBuy.setText("续费动力营");
            } else {
                //动力营未购买
                if (coursePlayerBean.isBuy()) {
                    //专辑已购买
                    llAlbumBuy.setVisibility(VISIBLE);
                    tvAlbumBuy.setVisibility(View.GONE);
                    tvAlbumState.setText("专辑已购买");
                    tvGrowthBuy.setText("加入动力营免费学");
                    tvAlbumState.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                } else {
                    llAlbumBuy.setVisibility(VISIBLE);
                    tvAlbumBuy.setVisibility(VISIBLE);
                    tvAlbumState.setText("购买专辑");
                    tvAlbumBuy.setText(" ¥" + coursePlayerBean.getPrice() + "/年");
                    tvGrowthBuy.setText("加入动力营免费学");
                }
            }
        }

        if (Double.parseDouble(coursePlayerBean.getPrice()) == 0) {
            llAlbumBuy.setVisibility(View.GONE);
            ll_bottom_buy.setVisibility(View.GONE);
//                tvGrowthBuy.setText("加入动力营免费学");
        }
    }

    //上传观看时长
    @Override
    public void setStudyProgressSuccess() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_audio_back:
                onBackPressed();
                break;
            case R.id.iv_share:
            case R.id.iv_course_share:
            case R.id.tv_share:
            case R.id.iv_audio_share:
                if (UserCacheUtil.isLogin()) {
                    if (coursePlayerBean == null) {
                        ToastUtils.shortShow("网络异常,无法分享");
                        return;
                    }
                    if (myJzvdStd.screen == myJzvdStd.SCREEN_FULLSCREEN) {
                        myJzvdStd.gotoScreenNormal();
                    }

                    ShareBean shareBean = new ShareBean();
                    shareBean.setPosterImgs(coursePlayerBean.getPosterImgs());
                    shareBean.setSlogans(coursePlayerBean.getSlogans());
                    shareBean.setCourseName("《" + coursePlayerBean.getCourses().get(position).getTitle() + "》");
                    shareBean.setInviteFrontName("邀请您观看");
                    shareBean.setInviteBehindName("");
                    if (StringUtil.isEmpty(shareIcon)) {
                        shareBean.setShareImg(courseDisplayPic);
                    } else {
                        shareBean.setShareImg(shareIcon);
                    }

                    shareBean.setShareTitle(coruseTitle);
                    shareBean.setShareSubtitle(courseSubtitle);

                    String shareUrl = coursePlayerBean.getShareUrl();
                    if (StringUtil.isNullString(shareUrl)) {
                        return;
                    }
                    shareBean.setShareUrl(shareUrl.contains("?") ? shareUrl + "&courseId=" + coursesId : shareUrl + "?courseId=" + coursesId);

                    MySharePopup mySharePopup = new MySharePopup(this, shareBean);
                    mySharePopup.showPopWindow();
                } else {
                    goLogin();
                }

                break;
            case R.id.tv_album_price:
                if (coursePlayerBean == null) {
                    ToastUtils.shortShow("网络异常,无法购买");
                    return;
                }
                buyAlbum();
            case R.id.ll_album_buy:
                if (coursePlayerBean == null) {
                    ToastUtils.shortShow("网络异常,无法购买");
                    return;
                }
                if (coursePlayerBean.isBuy()) {
//                    ARouter.getInstance().build("/android/paid").navigation();
                } else {
                    buyAlbum();
                }
                break;
            case R.id.tv_growth_buy:
                if (coursePlayerBean == null) {
                    ToastUtils.shortShow("网络异常,无法购买");
                    return;
                }
                if (UserCacheUtil.isLogin()) {
                    ARouter.getInstance().build("/android/vipCenter").navigation();
                } else {
                    goLogin();
                }

                break;
            case R.id.tv_toGroth:
                if (UserCacheUtil.isLogin()) {
                    ARouter.getInstance().build("/android/vipCenter").navigation();
                } else {
                    goLogin();
                }
                break;
            case R.id.tv_receive:
                if (UserCacheUtil.isLogin()) {
                    if (coursePlayerBean == null) {
                        ToastUtils.shortShow("网络异常");
                        return;
                    }
                    Intent intent1 = new Intent(this, ReceiveMaterialActivity.class);
                    intent1.putExtra("displayPic", coursePlayerBean.getDisplayPic());
                    intent1.putExtra("courseTopicTitle", coursePlayerBean.getCourseTopicTitle());
                    intent1.putExtra("courseTopicSubtitle", coursePlayerBean.getCourseTopicSubtitle());
                    intent1.putExtra("descriptionUrl", coursePlayerBean.getDescriptionUrl());
                    startActivity(intent1);
                } else {
                    goLogin();
                }

                break;
            case R.id.my_audio_video_change:
                if (myJzvdStd.my_audio_video_change.getText().toString().equals("音频")) {
                    if (null == currentMusic || StringUtil.isNullString(currentMusic.getUrl())) {
                        UIUtil.showToastSafe("本课程暂不支持音频");
                    } else {
                        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_OPEN, EventBusUtils.EventCode.SERVICE_NOTIFICATION_OPEN));
                        isClickAudio = true;

                        isPlay = !(myJzvdStd.state == STATE_PLAYING);//如果播放中 更改isPlay  调用showAudioPlayOrPause方法去更改isPlay的值
                        Jzvd.goOnPlayOnPause();//视频暂停播放
                        if (myJzvdStd.screen == myJzvdStd.SCREEN_FULLSCREEN) {
                            myJzvdStd.gotoScreenNormal();
                        }
                        myJzvdStd.rlAudioBg.setVisibility(VISIBLE);
                        myJzvdStd.llAudioController.setVisibility(VISIBLE);
                        seek = (int) myJzvdStd.getCurrentPositionWhenPlaying();
                        myJzvdStd.tvAudioCurrent.setText(JZUtils.stringForTime(seek));
                        myJzvdStd.tvAudioTotal.setText(JZUtils.stringForTime(currentMusic.getDuration()));
                        myJzvdStd.my_audio_video_change.setText("视频");
                        mSensorManager.unregisterListener(mSensorEventListener);
                        currentMusic.setAudio(true);
                        currentMusic.setPlaying(myJzvdStd.state == STATE_PLAYING);
                        currentMusic.setDuration((int) myJzvdStd.getDuration());
                        currentMusic.setCurrentPosition((int) myJzvdStd.getCurrentPositionWhenPlaying());
                        showAudioPlayOrPause();
                    }
                } else {
                    EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PAUSE, currentMusic));
                    showVideoPage();
                }
                break;
            case R.id.iv_audio_start:
                showAudioPlayOrPause();
                break;
        }
    }

    private void showFromAudioPage() {
        //因为音频进入 默认直接播放
        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_OPEN, EventBusUtils.EventCode.SERVICE_NOTIFICATION_OPEN));
        isClickAudio = true;
        myJzvdStd.rlAudioBg.setVisibility(VISIBLE);
        myJzvdStd.llAudioController.setVisibility(VISIBLE);
        mSensorManager.unregisterListener(mSensorEventListener);
        myJzvdStd.my_audio_video_change.setText("视频");
//        showAudioPlayOrPause();
        if (isPlay) {
            //正在播放 ---->暂停---->展示播放键
            isPlay = false;
            myJzvdStd.civHead.clearAnimation();
            myJzvdStd.ivAudioStart.setImageDrawable(UIUtil.getDrawable(R.drawable.jz_click_play_selector));
            currentMusic.setPlaying(false);
            if (null != MediaService.mMediaPlayer) {
                seek = MediaService.mMediaPlayer.getCurrentPosition();
            }
        } else {
            isPlay = true;
            myJzvdStd.civHead.startAnimation(rotateAnimation);
            myJzvdStd.ivAudioStart.setImageDrawable(UIUtil.getDrawable(R.drawable.jz_click_pause_selector));
            currentMusic.setPlaying(true);
        }
    }

    private void changeToVideo() {
        isClickAudio = false;
        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE, EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE));
        myJzvdStd.my_audio_video_change.setText("音频");
        myJzvdStd.rlAudioBg.setVisibility(View.GONE);
        myJzvdStd.llAudioController.setVisibility(View.GONE);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void showVideoPage() {
        changeToVideo();
        seek = MediaService.mMediaPlayer.getCurrentPosition();
        currentMusic.setAudio(false);
        myJzvdStd.seekToInAdvance = seek;
        myJzvdStd.startVideo();
//        myJzvdStd.mediaInterface.seekTo(seek);
        if (isPlay) {
            Jzvd.goOnPlayOnResume();
        } else {
            Jzvd.goOnPlayOnPause();
        }
    }

    private void showAudioPlayOrPause() {
        if (isPlay) {
            //正在播放 ---->暂停---->展示播放键
            isPlay = false;
            myJzvdStd.civHead.clearAnimation();
            myJzvdStd.ivAudioStart.setImageDrawable(UIUtil.getDrawable(R.drawable.jz_click_play_selector));
            currentMusic.setPlaying(false);
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PAUSE, currentMusic));
            if (null != MediaService.mMediaPlayer) {
                seek = MediaService.mMediaPlayer.getCurrentPosition();
            }
        } else {
            isPlay = true;
            myJzvdStd.civHead.startAnimation(rotateAnimation);
            myJzvdStd.ivAudioStart.setImageDrawable(UIUtil.getDrawable(R.drawable.jz_click_pause_selector));
            currentMusic.setPlaying(true);
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PLAY, currentMusic));
        }
    }

    private void bugGroth() {
        if (UserCacheUtil.isLogin()) {
            CommodityBean commodityBean = new CommodityBean();
            commodityBean.setImg(coursePlayerBean.getGrowingIcon());
            commodityBean.setPrice(coursePlayerBean.getGrowingPrice());
            commodityBean.setTitle("动力营");
            commodityBean.setTopicId("0");
            commodityBean.setType("2");
            Intent intent2 = new Intent(this, ChosePayWayActivity.class);
            intent2.putExtra(Constant.commodity, commodityBean);
            startActivity(intent2);
            SharePreferencesUtils.saveProductName("你已成功购买动力营");
        } else {
            goLogin();
        }
    }

    private void buyAlbum() {
        if (UserCacheUtil.isLogin()) {
            CommodityBean commodityBean = new CommodityBean();
            commodityBean.setPrice(coursePlayerBean.getPrice());
            commodityBean.setTopicId(coursePlayerBean.getTopicId());
            commodityBean.setType(coursePlayerBean.getClassification());
            commodityBean.setImg(coursePlayerBean.getDisplayPic());
            commodityBean.setTitle(coursePlayerBean.getCourseTopicTitle());
            commodityBean.setSubTitle(coursePlayerBean.getCourseTopicSubtitle());
            SharePreferencesUtils.saveProductName("你已成功购买《" + coursePlayerBean.getCourseTopicTitle() + "》课程，赶紧去学习吧！");

            Intent intent1 = new Intent(this, ChosePayWayActivity.class);
            intent1.putExtra(Constant.commodity, commodityBean);
            startActivity(intent1);

        } else {
            goLogin();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(mSensorEventListener);
        JZUtils.clearSavedProgress(this, null);
        //home back
        Jzvd.goOnPlayOnPause();
    }


    @Override
    protected void onDestroy() {
        if (isClickAudio) {
            if (canPlay) {
                currentMusic.setCurrentPosition(MediaService.mMediaPlayer.getCurrentPosition());
                currentMusic.setDuration(MediaService.mMediaPlayer.getDuration());
            } else {
                currentMusic.setCurrentPosition(0);
                currentMusic.setDuration(0);
            }
        } else {
            if (canPlay && null != myJzvdStd && null != myJzvdStd.mediaInterface) {
                currentMusic.setCurrentPosition((int) myJzvdStd.getCurrentPositionWhenPlaying());
                currentMusic.setDuration((int) myJzvdStd.getDuration());
            } else {
                currentMusic.setCurrentPosition(0);
                currentMusic.setDuration(0);
            }
        }
        Jzvd.goOnPlayOnPause();
        if (myJzvdStd.mediaInterface != null && UserCacheUtil.isLogin()) {
            mPresenter.setStudyProgress(coursesId, false, myJzvdStd.mediaInterface.getCurrentPosition() / 1000 + "");
        }
        Jzvd.releaseAllVideos();
        FloatMusicUtil.saveStatus(currentMusic);
        SharePreferencesUtils.saveFloatStatus(Constant.floatFlag);
        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.APP_OPEN_FLOAT, currentMusic));
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }

    //=====================================服务相关===================================================
    //进入页面启动服务，必须携带音频资源开启
    private void initService() {
        MusicList.saveCurrentMusicData(coursePlayerBean);
        currentMusic = MusicList.getMusic(position);
        if (null == MediaService.mMediaPlayer) {
            initAudioService(currentMusic);
        } else {
            if (StringUtil.isNullString(from)) {
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NEXT_PREPARE, currentMusic));
            } else {
                currentMusic = fromMusic;
            }
        }
        if (null != fromMusic) {//悬浮窗进入
            position = fromMusic.getPosition();//很重要 带值进来更新位置
            if (fromMusic.isAudio()) {
                //展示音频页面
                isPlay = !fromMusic.isPlaying();
                myJzvdStd.seekProgress.setMax(fromMusic.getDuration());
                myJzvdStd.tvAudioCurrent.setText(JZUtils.stringForTime(fromMusic.getCurrentPosition()));
                myJzvdStd.tvAudioTotal.setText(JZUtils.stringForTime(fromMusic.getDuration()));
                myJzvdStd.seekProgress.setProgress(fromMusic.getCurrentPosition());
                currentMusic = fromMusic;
                showFromAudioPage();

            } else {
                //默认是视频页面，不做处理
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE, EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE));
            }
            seek = fromMusic.getCurrentPosition();
        }
    }

    private void initAudioService(Music currentMusic) {
        Intent MediaServiceIntent = new Intent(this, MediaService.class);
        MediaServiceIntent.putExtra(Constant.service_data, currentMusic);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(MediaServiceIntent);
        } else {
            startService(MediaServiceIntent);
        }
    }
}
