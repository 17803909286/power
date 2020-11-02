package com.power.home.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.OrderUitils;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.ToastUtils;
import com.power.home.data.bean.CommodityBean;
import com.power.home.data.bean.PayChannelBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerChoseChannelComponent;
import com.power.home.di.module.ChoseChannelModule;
import com.power.home.presenter.ChoseChannelPresenter;
import com.power.home.presenter.contract.ChoseChannelContract;
import com.power.home.ui.adapter.ChosePayWayAdapter;
import com.power.home.ui.widget.MyTitleBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

@BindEventBus
public class ChosePayWayActivity extends BaseActivity<ChoseChannelPresenter> implements View.OnClickListener, ChoseChannelContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.recycle_pay_way)
    RecyclerView recyclePayWay;
    @BindView(R.id.tv_immediately_pay)
    TextView tvImmediatelyPay;
    @BindView(R.id.iv_course_icon)
    ImageView ivCourseIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ll_album)
    LinearLayout llAlbum;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;

    private ChosePayWayAdapter chosePayWayAdapter;
    private CommodityBean commodityBean;

    @Override
    public int setLayout() {
        return R.layout.activity_chose_pay_way;
    }

    @Override
    public void getExtras() {
        Intent intent = getIntent();
        commodityBean = (CommodityBean) intent.getSerializableExtra(Constant.commodity);
    }

    @Override
    public boolean init() {
        initView();
        initRecycle();
        mPresenter.getPayChannel("Android");
        return false;
    }

    private void initView() {
        DrawableUtil.loadUrl(R.drawable.icon_place_holder_64_96, ivCourseIcon, commodityBean.getImg());
        tvTitle.setText(commodityBean.getTitle());
        tvPrice.setText("¥" + commodityBean.getPrice());
        tvSubtitle.setText(commodityBean.getSubTitle());

    }

    private void initRecycle() {

        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclePayWay.setLayoutManager(layout);
        chosePayWayAdapter = new ChosePayWayAdapter(R.layout.item_chose_pay_way, null);
        recyclePayWay.setAdapter(chosePayWayAdapter);
    }


    @Override
    public void setListener() {
        backListener(titleBar);
        chosePayWayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                chosePayWayAdapter.setChoseId(position);
            }
        });
        tvImmediatelyPay.setOnClickListener(this);

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerChoseChannelComponent.builder().appComponent(appComponent)
                .choseChannelModule(new ChoseChannelModule(this))
                .build().inject(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_immediately_pay:
                if (chosePayWayAdapter.getData().size() == 0) {
                    ToastUtils.shortShow("请选择支付方式");
                    return;
                }
                new OrderUitils().CreateOrder(commodityBean.getPrice(),
                        chosePayWayAdapter.getData().get(chosePayWayAdapter.getChoseId()).getId(),
                        chosePayWayAdapter.getData().get(chosePayWayAdapter.getChoseId()).getPayType(), commodityBean.getTopicId(), SharePreferencesUtils.getUserId());
                break;
        }
    }


    @Override
    public void getPayChanneleSuces(List<PayChannelBean> payChannelBeans) {
        chosePayWayAdapter.setNewData(payChannelBeans);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.FINISH_PAY_WAY:
                    finish();
                    break;
            }
        }
    }

}
