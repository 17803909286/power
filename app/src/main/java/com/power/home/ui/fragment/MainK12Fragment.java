package com.power.home.ui.fragment;

import android.app.Activity;
import android.content.Intent;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.BannerImageLoader;
import com.power.home.common.util.ColorInfo;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.BannerBean;
import com.power.home.data.bean.CourseContentBean;
import com.power.home.data.bean.ForwardAddressBean;
import com.power.home.data.bean.FreeExperienceBean;
import com.power.home.data.bean.MainGridBean;
import com.power.home.data.bean.MainK12Bean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerMainK12Component;
import com.power.home.di.module.MainK12Module;
import com.power.home.presenter.MainK12Presenter;
import com.power.home.presenter.contract.MainK12Contract;
import com.power.home.ui.activity.TeacherDetailActivity;
import com.power.home.ui.activity.WebViewActivity;
import com.power.home.ui.adapter.HomeClassifyAdapter;
import com.power.home.ui.adapter.HomeGridAdapter;
import com.power.home.ui.adapter.HomeTabAdapter;
import com.power.home.ui.adapter.MainChampAdapter;
import com.power.home.ui.adapter.PagerFragmentAdapter;
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
public class MainK12Fragment extends BaseFragment<MainK12Presenter> implements View.OnClickListener, MainK12Contract.View {


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
    TextView tvTeacherGuide;
    RecyclerView recycle_grid;//宫格
    RecyclerView recycleChamp;//状元指导
    TextView tvFreeTrial;
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
    private GridLayoutManager gridManager;
    private TextView tvTitleRecommend;
    private ViewPager viewPager_free_experience;
    private List<Fragment> list = new ArrayList<>();
    private MainChampAdapter mainChampAdapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_k12;
    }

    @Override
    protected void getExtras() {

    }


    @Override
    public void init() {

        initHeader();
        initView();

        initRecycle();
        mPresenter.getK12Data();
        refreshLayout.setEnableLoadMore(false);

    }

    private void initHeader() {
        headerView = getLayoutInflater().inflate(R.layout.item_k12_header, null);
        banner = headerView.findViewById(R.id.banner);
        recycleChamp = headerView.findViewById(R.id.recycle_champ);
        tvFreeTrial = headerView.findViewById(R.id.tv_free_trial);
//        recycleFreeExperience = headerView.findViewById(R.id.recycle_free_experience);
        viewPager_free_experience = headerView.findViewById(R.id.viewPager_free_experience);
        tvTeacherGuide = headerView.findViewById(R.id.tv_teacher_guide);
        recycle_grid = headerView.findViewById(R.id.recycle_grid);
        tv_title_daily_business = headerView.findViewById(R.id.tv_title_daily_business);
        tv_title_free_trial = headerView.findViewById(R.id.tv_title_free_trial);
        tvTitleRecommend = headerView.findViewById(R.id.tv_title_recommend);
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
        //状元指导
        LinearLayoutManager champMannager = new LinearLayoutManager(getActivity());
        champMannager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleChamp.setLayoutManager(champMannager);
        mainChampAdapter = new MainChampAdapter(R.layout.item_main_champ, null);
        recycleChamp.setAdapter(mainChampAdapter);

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
        tvTeacherGuide.setOnClickListener(this);
        tvFreeTrial.setOnClickListener(this);
        ivSearch.setOnClickListener(this);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getK12Data();
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
        mainChampAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String forwardAddress = mainChampAdapter.getData().get(position).getForwardAddress();
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                Intent intent = new Intent(UIUtil.getContext(), TeacherDetailActivity.class);
                intent.putExtra(Constant.id, forwardAddressBean.getId());
                startActivity(intent);

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
//                                .withString(Constant.title, homeGridAdapter.getData().get(position).getTitle())
                                .withString(Constant.from, "k12")
                                .withString(Constant.data, homeGridAdapter.getData().get(position).getTitle())
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
                            .withString(Constant.from, "k12")
                            .withString(Constant.data, classifyAdapter.getData().get(position).getAreaName())
                            .navigation();
                }
            }
        });

        ivMessage.setOnClickListener(this);
        tvGrowth.setOnClickListener(this);
        ivBackToTop.setOnClickListener(this);

    }


    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerMainK12Component.builder().appComponent(appComponent)
                .mainK12Module(new MainK12Module(this))
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

            case R.id.tv_teacher_guide:
                ARouter.getInstance().build("/android/teacherGuide")
                        .withString(Constant.title, tv_title_daily_business.getText().toString())
                        .navigation();
                break;

            case R.id.tv_free_trial:
                ARouter.getInstance().build("/android/freeCourse")
                        .withString(Constant.title, tv_title_free_trial.getText().toString())
                        .withString(Constant.from, "k12")
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
                ARouter.getInstance().build("/android/growth")
                        .withString(Constant.title, tv_title_free_trial.getText().toString())
                        .navigation();
                break;

            case R.id.iv_back_to_top:
//                recycleClassify.smoothScrollToPosition(0);
                LinearLayoutManager manager = (LinearLayoutManager) recycleClassify.getLayoutManager();
//平滑滚动
                manager.scrollToPosition(0);
                break;

        }
    }


    @Override
    public void getK12DataSuccess(MainK12Bean mainK12Bean) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }

        bannerList = new ArrayList<>();
        colorList = new ArrayList<>();
        List<BannerBean.ContentsBean> banner = mainK12Bean.getBanner().getContents();
        for (int i = 0; i < banner.size(); i++) {
            bannerList.add(banner.get(i).getDisplayPic());
        }
        initBanner(banner);
        initViewPager(mainK12Bean.getFreeExperience().getContents());

        classifyAdapter.setNewData(mainK12Bean.getLinkageArea().getAreas());
        tabAdapter.setNewData(mainK12Bean.getLinkageArea().getAreas());
        List<MainGridBean.ContentsBean> grids = mainK12Bean.getGrid().getContents();
        homeGridAdapter.setNewData(grids);
        mainChampAdapter.setNewData(mainK12Bean.getChampGuide().getContents());
        if (null != grids) {
            gridManager.setSpanCount(grids.size() >= 4 ? 4 : grids.size());
        }
        tv_title_daily_business.setText(mainK12Bean.getChampGuide().getAreaName());
        tv_title_free_trial.setText(mainK12Bean.getFreeExperience().getAreaName());
        SharePreferencesUtils.saveSearchKey(mainK12Bean.getSearchWords());
        tvSearchkey.setText(SharePreferencesUtils.getSearchKey());

    }

    private void initViewPager(List<FreeExperienceBean.ContentsBean> frees) {
        if (null != frees && frees.size() > 0) {
            list.clear();
            for (int i = 0; i < frees.size(); i++) {
                list.add(getFragment(frees.get(i)));
            }
            PagerFragmentAdapter pagerFragmentAdapter = new PagerFragmentAdapter(getChildFragmentManager());
            pagerFragmentAdapter.setFragments(list);
            viewPager_free_experience.setAdapter(pagerFragmentAdapter);
            viewPager_free_experience.setPageMargin(UIUtil.dip2px(24));
            viewPager_free_experience.setOffscreenPageLimit(6);
            viewPager_free_experience.setPageTransformer(true, new ScalePageTransformer());
//            pagerFragmentAdapter.registerDataSetObserver(indicator.getDataSetObserver());
            if (frees.size() > 2) {
                viewPager_free_experience.setCurrentItem(1);
            }
        }
    }

    private MainHomeFreeFragment getFragment(FreeExperienceBean.ContentsBean bean) {
        MainHomeFreeFragment mainHomeFreeFragment = new MainHomeFreeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.data, bean);

        mainHomeFreeFragment.setArguments(bundle);
        return mainHomeFreeFragment;
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
                    mPresenter.getK12Data();
                    break;
            }
        }
    }
}
