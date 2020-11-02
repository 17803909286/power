package com.power.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.home.App;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ForwardAddressBean;
import com.power.home.data.bean.MessageBean;
import com.power.home.di.component.DaggerMessageComponent;
import com.power.home.di.module.MessageModule;
import com.power.home.presenter.MessagePresenter;
import com.power.home.presenter.contract.MessageContract;
import com.power.home.ui.adapter.MessageAdapter;
import com.power.home.ui.widget.BaseLazyFragment;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhp on 2020-03-14
 */
public class MessageFragment extends BaseLazyFragment implements MessageContract.View {

    @BindView(R.id.recycle_message)
    RecyclerView recycleMessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int page = 0;
    private int size = 10;
    private String messageLevel;
    @Inject
    MessagePresenter mPresenter;
    private MessageAdapter messageAdapter;

    @Override
    protected View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        messageLevel = getArguments().getString("type");
        View view = inflater.inflate(R.layout.fragment_message, null);
        mUnbinder = ButterKnife.bind(this, view);
        initRecycle();
        DaggerMessageComponent.builder().appComponent(App.getApplication().getAppComponent())
                .messageModule(new MessageModule(this))
                .build().inject(this);
        mPresenter.getUserNotify(page, size, messageLevel);
        return view;
    }

    private void initRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        recycleMessage.setLayoutManager(linearLayoutManager);
        messageAdapter = new MessageAdapter(R.layout.item_message, null);
        recycleMessage.setAdapter(messageAdapter);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getUserNotify(page, size, messageLevel);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getUserNotify(page, size, messageLevel);
            }
        });
        messageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String forwardAddress = messageAdapter.getData().get(position).getForwardAddress();
                if (!StringUtil.isEmpty(forwardAddress)) {
                    ForwardAddressBean forwardAddressBean = new Gson().fromJson(forwardAddress, ForwardAddressBean.class);
                    ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                            .withString(Constant.type, messageAdapter.getData().get(position).getType())
                            .withString(Constant.id, forwardAddressBean.getId())
                            .navigation();
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void dismissLoading() {

    }

    public View getEmptyView(int id, String tip) {
        View view = RelativeLayout.inflate(UIUtil.getContext(), R.layout.empty_layout, null);
        TextView textView = view.findViewById(R.id.tv_dataEmpty);
        textView.setText(tip);
        ImageView imageView = view.findViewById(R.id.img_dataEmpty);
        imageView.setImageDrawable(UIUtil.getDrawable(id));
        return view;
    }

    @Override
    public void getUserNotifySuccess(List<MessageBean> messageBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
        if (messageBeans.size() == 0) {
            if (page == 0) {
                messageAdapter.setNewData(messageBeans);
                messageAdapter.setEmptyView(getEmptyView(R.drawable.icon_empty_message, "暂无消息记录"));
            } else {
                UIUtil.showToastSafe("没有更多数据");
            }
        } else {
            if (page == 0) {
                messageAdapter.setNewData(messageBeans);
            } else {
                messageAdapter.addData(messageBeans);
            }
            page++;
        }
        if (messageBeans.size() < size) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }
}
