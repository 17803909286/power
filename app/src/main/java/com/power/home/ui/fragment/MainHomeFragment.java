package com.power.home.ui.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.BannerImageLoader;
import com.power.home.common.util.ColorInfo;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.AdvBean;
import com.power.home.data.bean.BannerBean;
import com.power.home.data.bean.CourseContentBean;
import com.power.home.data.bean.ForwardAddressBean;
import com.power.home.data.bean.FreeExperienceBean;
import com.power.home.data.bean.HomePagePopupData;
import com.power.home.data.bean.MainGridBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerMainHomeComponent;
import com.power.home.di.module.MainHomeModule;
import com.power.home.interfaces.OnItemClickListener;
import com.power.home.presenter.MainHomePresenter;
import com.power.home.presenter.contract.MainHomeContract;
import com.power.home.ui.activity.WebViewActivity;
import com.power.home.ui.adapter.HomeClassifyAdapter;
import com.power.home.ui.adapter.HomeDailyBusinessAdapter;
import com.power.home.ui.adapter.HomeGridAdapter;
import com.power.home.ui.adapter.HomeRecommendAdapter;
import com.power.home.ui.adapter.HomeTabAdapter;
import com.power.home.ui.adapter.PagerFragmentAdapter;
import com.power.home.ui.widget.GlideRoundTransform;
import com.power.home.ui.widget.MyImageView;
import com.power.home.ui.widget.MyPopupWindow;
import com.power.home.ui.widget.ScalePageTransformer;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by zhangpeng on 2019/11/06.
 */
@BindEventBus
public class MainHomeFragment extends BaseFragment<MainHomePresenter> implements View.OnClickListener, MainHomeContract.View {


    @BindView(R.id.recycle_classify)
    RecyclerView recycleClassify;
    @BindView(R.id.iv_header_bg)
    ImageView ivHeaderBg;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    @BindView(R.id.view_status_bar)
    View viewStatusBar;
    @BindView(R.id.ll_title_search)
    LinearLayout llTitleSearch;
    @BindView(R.id.rl_title_header)
    RelativeLayout rlTitleHeader;
    @BindView(R.id.recycle_tab)
    RecyclerView recyclerTab;
    @BindView(R.id.tv_searchkey)
    TextView tvSearchkey;

    Banner banner;
    TextView tvDailyBusiness;
    RecyclerView recycle_grid;//宫格
    RecyclerView recycleUprising;//每日商道
    TextView tvFreeTrial;
    //    RecyclerView recycleFreeExperience;//免费体验
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.tv_growth)
    TextView tvGrowth;
    @BindView(R.id.iv_back_to_top)
    ImageView ivBackToTop;


    private int count;
    private List<String> bannerList;
    private List<ColorInfo> colorList;
    private BannerImageLoader imageLoader;
    private boolean isInit = true;
    private int statusBarHeight;
    private HomeDailyBusinessAdapter dailyBusinessAdapter;
    private LinearLayoutManager classifyManage;
    private HomeClassifyAdapter classifyAdapter;
    private View headerView;
    private int firstVisibleItemPosition;
    private HomeTabAdapter tabAdapter;
    private HomeGridAdapter homeGridAdapter;
    private TextView tv_title_daily_business;
    private TextView tv_title_free_trial;
    private int statusColor;
    private boolean useDart;
    private ImageView ivMainHomeAdv;
    private GridLayoutManager gridManager;
    private TextView tvTitleRecommend;
    private RecyclerView recycleRecommend;
    private HomeRecommendAdapter homeRecommendAdapter;
    private ViewPager viewPager_free_experience;
    private List<Fragment> list = new ArrayList<>();
    private MyPopupWindow mPopupWindow;


    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void getExtras() {

    }


    @Override
    public void init() {

        initHeader();
        initView();

        initRecycle();
        mPresenter.getHomeData();
        mPresenter.getHomePagePopup();
        refreshLayout.setEnableLoadMore(false);

    }

    private void initHeader() {
        headerView = getLayoutInflater().inflate(R.layout.item_home_header, null);
        ivMainHomeAdv = headerView.findViewById(R.id.iv_main_home_adv);


        banner = headerView.findViewById(R.id.banner);
        recycleUprising = headerView.findViewById(R.id.recycle_uprising);
        tvFreeTrial = headerView.findViewById(R.id.tv_free_trial);
//        recycleFreeExperience = headerView.findViewById(R.id.recycle_free_experience);
        viewPager_free_experience = headerView.findViewById(R.id.viewPager_free_experience);
        tvDailyBusiness = headerView.findViewById(R.id.tv_daily_business);
        recycle_grid = headerView.findViewById(R.id.recycle_grid);
        tv_title_daily_business = headerView.findViewById(R.id.tv_title_daily_business);
        tv_title_free_trial = headerView.findViewById(R.id.tv_title_free_trial);
        tvTitleRecommend = headerView.findViewById(R.id.tv_title_recommend);
        recycleRecommend = headerView.findViewById(R.id.recycle_recommend);

    }

    private void initView() {
        //获取状态栏高
        statusBarHeight = StatusBarUtil.getStatusBarHeight(getActivity());
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewStatusBar.getLayoutParams();
        params.height = statusBarHeight;
        viewStatusBar.setLayoutParams(params);
        //banner滑动监听
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 1) {//会出现极个别大于1的数据
                    return;
                }
                //修正position，解决两头颜色错乱，来自Banner控件源码
                if (position == 0) {
                    position = count;
                }
                if (position > count) {
                    position = 1;
                }
                int pos = (position + 1) % count;//很关键

                int vibrantColor = ColorUtils.blendARGB(imageLoader.getVibrantDarkColor(pos), imageLoader.getVibrantDarkColor(pos + 1), positionOffset);
                ivHeaderBg.setBackgroundColor(vibrantColor);
//                setStatusBarColor(getActivity(), vibrantColor);
            }

            @Override
            public void onPageSelected(final int position) {

                if (isInit) {// 第一次,延时加载才能拿到颜色
                    isInit = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int vibrantColor = imageLoader.getVibrantDarkColor(1);
                            ivHeaderBg.setBackgroundColor(vibrantColor);
//                            setStatusBarColor(getActivity(), vibrantColor);
                        }
                    }, 500);

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initRecycle() {
        //每日商道
        LinearLayoutManager uprisingMannager = new LinearLayoutManager(getActivity());
        uprisingMannager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleUprising.setLayoutManager(uprisingMannager);
        dailyBusinessAdapter = new HomeDailyBusinessAdapter();
        recycleUprising.setAdapter(dailyBusinessAdapter);
        //精品推荐
        LinearLayoutManager recommendMannager = new LinearLayoutManager(getActivity());
        recommendMannager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleRecommend.setLayoutManager(recommendMannager);
        homeRecommendAdapter = new HomeRecommendAdapter(R.layout.item_home_recommend, null);
        recycleRecommend.setAdapter(homeRecommendAdapter);

        //免费体验
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        recycleFreeExperience.setLayoutManager(gridLayoutManager);
//        recycleFreeExperience.setNestedScrollingEnabled(false);//禁止滑动
//        recycleFreeExperience.setHasFixedSize(true);
//        recycleFreeExperience.setFocusable(false);//禁止获取焦点
//        freeExperienceAdapter = new HomeFreeExperienceAdapter(R.layout.item_home_free_experience, null);
//        recycleFreeExperience.setAdapter(freeExperienceAdapter);
        //全部课程
        classifyManage = new LinearLayoutManager(getActivity());
        classifyManage.setOrientation(LinearLayoutManager.VERTICAL);
        recycleClassify.setLayoutManager(classifyManage);
        recycleClassify.setNestedScrollingEnabled(false);//禁止滑动
        recycleClassify.setHasFixedSize(true);
        recycleClassify.setFocusable(false);//禁止获取焦点
        classifyAdapter = new HomeClassifyAdapter(R.layout.item_home_course_list, null);
        classifyAdapter.addHeaderView(headerView);
        recycleClassify.setAdapter(classifyAdapter);
        //tab
        LinearLayoutManager tabManager = new LinearLayoutManager(getActivity());
        tabManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tabAdapter = new HomeTabAdapter(R.layout.item_home_tab, null);
        recyclerTab.setLayoutManager(tabManager);
        recyclerTab.setAdapter(tabAdapter);
        //宫格
        gridManager = new GridLayoutManager(getContext(), 4);
        recycle_grid.setLayoutManager(gridManager);
        recycle_grid.setNestedScrollingEnabled(false);//禁止滑动
        recycle_grid.setHasFixedSize(true);
        recycle_grid.setFocusable(false);//禁止获取焦点
        homeGridAdapter = new HomeGridAdapter(R.layout.item_home_grid, null);
        recycle_grid.setAdapter(homeGridAdapter);

    }


    @Override
    protected void setListener() {

        llTitleSearch.setOnClickListener(this);
        tvDailyBusiness.setOnClickListener(this);
        tvFreeTrial.setOnClickListener(this);
        ivSearch.setOnClickListener(this);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHomeData();
            }
        });

        recycleClassify.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int height = banner.getHeight() * 10 / 9;
                int scrollY = recyclerView.computeVerticalScrollOffset();
                if (scrollY <= height) {
                    llTitleSearch.setVisibility(View.VISIBLE);
                    viewStatusBar.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite00));
                    rlTitleHeader.setVisibility(View.GONE);
                    statusColor = R.color.colorWhite00;
                    setStatusBarColor(getActivity(), getActivity().getResources().getColor(statusColor));
                    useDart = false;
                    StatusBarUtil.setStatusTextColor(useDart, getActivity());

                } else {
                    llTitleSearch.setVisibility(View.GONE);
                    viewStatusBar.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));
                    rlTitleHeader.setVisibility(View.VISIBLE);
                    statusColor = R.color.colorWhite;
                    setStatusBarColor(getActivity(), getActivity().getResources().getColor(statusColor));
                    useDart = true;
                    StatusBarUtil.setStatusTextColor(useDart, getActivity());

                }
                firstVisibleItemPosition = classifyManage.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition > 0) {
                    recyclerTab.setVisibility(View.VISIBLE);
                    if (tabAdapter.getSelector() != firstVisibleItemPosition - 1) {
                        tabAdapter.setSelector(firstVisibleItemPosition - 1);
                        recyclerTab.scrollToPosition(firstVisibleItemPosition - 1);
                    }
                    ivBackToTop.setVisibility(View.VISIBLE);
                } else {
                    recyclerTab.setVisibility(View.GONE);
                    ivBackToTop.setVisibility(View.GONE);

                }


            }
        });
        tabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                classifyManage.scrollToPositionWithOffset(position + 1, 0);

            }
        });
        homeGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                String forwardAddress = homeGridAdapter.getData().get(position).getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);

                switch (homeGridAdapter.getData().get(position).getForwardType()) {
                    //1:原生   2:web
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.title, homeGridAdapter.getData().get(position).getTitle())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), homeGridAdapter.getData().get(position).getTitle());
                        break;
                }
            }
        });
//        freeExperienceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });

        dailyBusinessAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String forwardAddress = dailyBusinessAdapter.getData().get(position).getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                switch (dailyBusinessAdapter.getData().get(position).getForwardType()) {
                    //1:原生   2:web
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.type, dailyBusinessAdapter.getData().get(position).getType())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), dailyBusinessAdapter.getData().get(position).getTitle());
                        break;
                }
            }
        });
        classifyAdapter.setClick(new HomeClassifyAdapter.onItemClick() {
            @Override
            public void childClick(int position, int childPosition) {
                if (position == 0) {
                    return;
                }
                CourseContentBean contentsBean = classifyAdapter.getData().get(position - 1).getContents().get(childPosition);
                String forwardAddress = contentsBean.getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                switch (contentsBean.getForwardType()) {
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.type, contentsBean.getType())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), contentsBean.getTitle());
                        break;
                }
            }
        });
        classifyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_item_class_more) {
                    ARouter.getInstance().build("/android/allCourse")
                            .withString(Constant.data, classifyAdapter.getData().get(position).getAreaName())
                            .navigation();
                }
            }
        });

        homeRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String forwardAddress = homeRecommendAdapter.getData().get(position).getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                switch (homeRecommendAdapter.getData().get(position).getForwardType()) {
                    //1:原生   2:web
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.type, homeRecommendAdapter.getData().get(position).getType())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), homeRecommendAdapter.getData().get(position).getTitle());
                        break;
                }
            }
        });

        ivMessage.setOnClickListener(this);
        tvGrowth.setOnClickListener(this);
        ivBackToTop.setOnClickListener(this);

    }


    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerMainHomeComponent.builder().appComponent(appComponent)
                .mainHomeModule(new MainHomeModule(this))
                .build().inject(this);
    }


    /**
     * 初始化banner
     */
    private void initBanner(List<BannerBean.ContentsBean> banners) {
        count = bannerList.size();
        colorList.clear();
        for (int i = 0; i <= count + 1; i++) {
            ColorInfo info = new ColorInfo();
            if (i == 0) {
                info.setImgUrl(bannerList.get(count - 1));
            } else if (i == count + 1) {
                info.setImgUrl(bannerList.get(0));
            } else {
                info.setImgUrl(bannerList.get(i - 1));
            }
            colorList.add(info);
        }

        imageLoader = new BannerImageLoader(colorList);
        banner.setImageLoader(imageLoader);
        //设置图片集合
        banner.setImages(bannerList);
        //设置banner动画效果
        // banner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        banner.setDelayTime(Constant.TIME_AUTO_POLL);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerBean.ContentsBean contentsBean = banners.get(position);
                String forwardAddress = contentsBean.getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                switch (contentsBean.getForwardType()) {
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.type, contentsBean.getType())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), contentsBean.getTitle());
                        break;
                }
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_search:
            case R.id.iv_search:
//                Intent search = new Intent(getContext(), SearchActivity.class);
//                startActivity(search);
//                String forwardAddress = contentsBean.getForwardAddress();
//                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                ARouter.getInstance().build("/android/search")
                        .navigation();
                break;

            case R.id.tv_daily_business:
                ARouter.getInstance().build("/android/businessCourse")
                        .withString(Constant.title, tv_title_daily_business.getText().toString())
                        .navigation();
                break;

            case R.id.tv_free_trial:
                ARouter.getInstance().build("/android/freeCourse")
                        .withString(Constant.title, tv_title_free_trial.getText().toString())
                        .navigation();
                break;
            case R.id.iv_message:
                if (UserCacheUtil.isLogin()) {
                    ARouter.getInstance().build("/android/message")
                            .withString(Constant.title, tv_title_free_trial.getText().toString())
                            .navigation();
                } else {
                    goLogin();
                }

                break;
            case R.id.tv_growth:
                ARouter.getInstance().build("/android/vipCenter").navigation();
                break;

            case R.id.iv_back_to_top:
                //recycleClassify.smoothScrollToPosition(0);
                LinearLayoutManager manager = (LinearLayoutManager) recycleClassify.getLayoutManager();
                //平滑滚动
                manager.scrollToPosition(0);
                break;

        }
    }


    @Override
    public void getHomeDataSuces(MainHomeBean mainHomeBean) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        bannerList = new ArrayList<>();
        colorList = new ArrayList<>();
        List<BannerBean.ContentsBean> banner = mainHomeBean.getBanner().getContents();
        for (int i = 0; i < banner.size(); i++) {
            bannerList.add(banner.get(i).getDisplayPic());
        }
        initBanner(banner);
        initViewPager(mainHomeBean.getFreeExperience().getContents());

        initLottie(mainHomeBean.getAdv());
        if (mainHomeBean.isHappinessVip()) {
            tvGrowth.setVisibility(View.GONE);
        } else {
            tvGrowth.setVisibility(View.VISIBLE);
        }
        dailyBusinessAdapter.setDatas(mainHomeBean.getDailyBusiness().getContents());
        recycleUprising.scrollToPosition(dailyBusinessAdapter.getItemRawCount() * 99999);
        dailyBusinessAdapter.notifyDataSetChanged();
        homeRecommendAdapter.setNewData(mainHomeBean.getRecommend().getContents());
        classifyAdapter.setNewData(mainHomeBean.getLinkageArea().getAreas());
        tabAdapter.setNewData(mainHomeBean.getLinkageArea().getAreas());
        List<MainGridBean.ContentsBean> grids = mainHomeBean.getGrid().getContents();
        homeGridAdapter.setNewData(grids);
        if (null != grids) {
            gridManager.setSpanCount(grids.size() >= 4 ? 4 : grids.size());
        }
        tv_title_daily_business.setText(mainHomeBean.getDailyBusiness().getAreaName());
        tv_title_free_trial.setText(mainHomeBean.getFreeExperience().getAreaName());
        tvTitleRecommend.setText(mainHomeBean.getRecommend().getAreaName());
        SharePreferencesUtils.saveSearchKey(mainHomeBean.getSearchWords());
        tvSearchkey.setText(SharePreferencesUtils.getSearchKey());

    }

    @Override
    public void getHomePagePopupSucess(List<HomePagePopupData> homePagePopupDatas) {
        if (null != homePagePopupDatas && homePagePopupDatas.size() > 0) {
            showPop(homePagePopupDatas.get(0));
        }
    }

    private void showPop(HomePagePopupData popupData) {

        mPopupWindow = new PopuUtil().initAtLocationPopuWrap(getActivity(),
                R.layout.popup_main_show);
        MyImageView iv_popup_main_image = mPopupWindow.getContentView().findViewById(R.id.iv_popup_main_image);
        ImageView iv_popup_main_close = mPopupWindow.getContentView().findViewById(R.id.iv_popup_main_close);
        RequestOptions requestOptions = RequestOptions.fitCenterTransform().transform(new GlideRoundTransform(8))
                .placeholder(R.drawable.icon_place_holder_64_96)
                .error(R.drawable.icon_place_holder_64_96)
                .fallback(R.drawable.icon_place_holder_64_96);
        Glide.with(UIUtil.getContext()).load(popupData.getDisplayPic()).apply(requestOptions).into(iv_popup_main_image);
        iv_popup_main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                String forwardAddress = popupData.getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                switch (popupData.getForwardType()) {
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.type, forwardAddressBean.getType())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), popupData.getTitle());
                        break;
                }
            }
        });
        iv_popup_main_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
    }

    private boolean mIsChanged = false;
    private static int POINT_LENGTH = 3;
    private static final int FIRST_ITEM_INDEX = 1;
    private int mCurrentPagePosition = FIRST_ITEM_INDEX;

    private void initViewPager(List<FreeExperienceBean.ContentsBean> frees) {
        if (null != frees && frees.size() > 0) {
            POINT_LENGTH = frees.size();
            list.clear();
            for (int i = 0; i < frees.size(); i++) {
                list.add(getFragment(frees.get(i)));
            }
            MainHomeFreeFragment firstFragment = getFragment(frees.get(0));
            MainHomeFreeFragment lastFragment = getFragment(frees.get(frees.size() - 1));
            list.add(0, lastFragment);
            list.add(firstFragment);
            PagerFragmentAdapter pagerFragmentAdapter = new PagerFragmentAdapter(getChildFragmentManager());
            pagerFragmentAdapter.setFragments(list);
            viewPager_free_experience.setAdapter(pagerFragmentAdapter);
            viewPager_free_experience.setPageMargin(UIUtil.dip2px(16));
            viewPager_free_experience.setOffscreenPageLimit(list.size() * 2);
            viewPager_free_experience.setPageTransformer(true, new ScalePageTransformer());
//            pagerFragmentAdapter.registerDataSetObserver(indicator.getDataSetObserver());
            if (frees.size() > 2) {
                viewPager_free_experience.setCurrentItem(1, false);
            }

//            viewPager_free_experience.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int pPosition) {
//                    mIsChanged = true;
//                    if (pPosition > POINT_LENGTH) {// 末位之后，跳转到首位（1）
//                        mCurrentPagePosition = FIRST_ITEM_INDEX;
//                    } else if (pPosition < FIRST_ITEM_INDEX) {// 首位之前，跳转到末尾（N）
//                        mCurrentPagePosition = POINT_LENGTH;
//                    } else {
//                        mCurrentPagePosition = pPosition;
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int pState) {
//                    if (ViewPager.SCROLL_STATE_IDLE == pState) {
//                        if (mIsChanged) {
//                            mIsChanged = false;
//                            viewPager_free_experience.setCurrentItem(mCurrentPagePosition, false);
//                        }
//                    }
//                }
//            });
        }
    }

    private MainHomeFreeFragment getFragment(FreeExperienceBean.ContentsBean bean) {
        MainHomeFreeFragment mainHomeFreeFragment = new MainHomeFreeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.data, bean);

        mainHomeFreeFragment.setArguments(bundle);
        return mainHomeFreeFragment;
    }

    private void initLottie(AdvBean advBean) {
        if (advBean != null && advBean.getContents().size() > 0) {
            ivMainHomeAdv.setVisibility(View.VISIBLE);
            Glide.with(UIUtil.getContext()).load(advBean.getContents().get(0).getDisplayPic()).into(ivMainHomeAdv);
        } else {
            ivMainHomeAdv.setVisibility(View.GONE);
        }

        ivMainHomeAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (advBean.getContents().size() == 0) {
                    return;
                }
                String forwardAddress = advBean.getContents().get(0).getForwardAddress();
                String type = advBean.getContents().get(0).getForwardType();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                switch (type) {
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .withString(Constant.type, advBean.getContents().get(0).getType())
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), advBean.getContents().get(0).getTitle());
                        break;
                }

            }
        });
    }


    @Override
    public void showError(String msg) {
        super.showError(msg);
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBarColor(getActivity(), UIUtil.getColor(statusColor));
            StatusBarUtil.setStatusTextColor(useDart, getActivity());
        }
    }

    /**
     * @param date 分享返回结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.LOGIN_BUY_REFRESH:
                    mPresenter.getHomeData();
                    break;
            }
        }
    }
}
