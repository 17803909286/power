package com.power.home.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ForwardAddressBean;
import com.power.home.data.bean.SearchBean;
import com.power.home.data.bean.SearchHotWordBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerSearchComponent;
import com.power.home.di.module.SearchModule;
import com.power.home.presenter.SearchPresenter;
import com.power.home.presenter.contract.SearchContract;
import com.power.home.ui.adapter.SearchParentAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xwl on 2019/11/06
 * 搜索页面
 */
@Route(path = "/android/search")
public class SearchActivity extends BaseActivity<SearchPresenter> implements View.OnClickListener, SearchContract.View {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tabLayout)
    TagFlowLayout tabLayout;
    @BindView(R.id.ll_words)
    LinearLayout llWords;
    @BindView(R.id.recycle_parent)
    RecyclerView recycleParent;
    @BindView(R.id.tabLayout_history)
    TagFlowLayout tabLayoutHistory;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.tv_clear_history)
    TextView tvClearHistory;


    private TagAdapter<String> tagAdapter;
    private TagAdapter<String> historyAdapter;
    private ArrayList<String> hotWords;
    private ArrayList<String> historyWords;
    private SearchParentAdapter searchParentAdapter;
    private String searchKey;


    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void getExtras() {
        searchKey = SharePreferencesUtils.getSearchKey();
    }

    @Override
    public boolean init() {
        initView();
        initRecycler();
        mPresenter.searchWords();
        mPresenter.getFindNearWord();
        return false;

    }

    private void initView() {
        etSearch.setHint(searchKey);
        //获取焦点
        etSearch.setFocusable(true);
        etSearch.setFocusableInTouchMode(true);
        etSearch.requestFocus();
    }

    private void initRecycler() {
        //专辑和课程
        LinearLayoutManager albumManager = new LinearLayoutManager(this);
        recycleParent.setLayoutManager(albumManager);

        searchParentAdapter = new SearchParentAdapter(R.layout.item_search_parent, null);
        recycleParent.setAdapter(searchParentAdapter);
        //课程


    }

    //热门搜索
    private void initHotWord() {
        tagAdapter = new TagAdapter<String>(hotWords) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_tag_recent_searches_text, null);
                TextView tv_word = layout.findViewById(R.id.tv_word);
                if (s.length() > 9) {
                    String substring = s.substring(0, 9);
                    tv_word.setText(substring + "...");
                } else {
                    tv_word.setText(s);
                }
                return layout;
            }
        };
        tabLayout.setAdapter(tagAdapter);
    }

    private void initHistoryWord() {
        historyAdapter = new TagAdapter<String>(historyWords) {
            @SuppressLint("SetTextI18n")
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_tag_recent_searches_text, null);
                TextView tv_word = layout.findViewById(R.id.tv_word);
                if (s.length() > 9) {
                    String substring = s.substring(0, 9);
                    tv_word.setText(substring + "...");
                } else {
                    tv_word.setText(s);
                }
                return layout;
            }
        };
        tabLayoutHistory.setAdapter(historyAdapter);
    }


    @Override
    public void setListener() {
        //取消
        tvCancle.setOnClickListener(this);
        tvClearHistory.setOnClickListener(this);
        //最近搜索标签点击
        tabLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String searchText = hotWords.get(position);
                etSearch.setText(searchText);
                etSearch.setSelection(searchText.length());//将光标移至文字末尾
                mPresenter.search(etSearch.getText().toString());
                InputMethodManager manager = (InputMethodManager) etSearch.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                return false;
            }
        });

        //历史搜索标签点击
        tabLayoutHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String historyText = historyWords.get(position);
                etSearch.setText(historyText);
                etSearch.setSelection(historyText.length());//将光标移至文字末尾
                mPresenter.search(etSearch.getText().toString());
                InputMethodManager manager = (InputMethodManager) etSearch.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                return false;
            }
        });
        //搜索框的输入监听
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    llWords.setVisibility(View.GONE);
                } else {
                    llWords.setVisibility(View.VISIBLE);
                    recycleParent.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //键盘上 搜索 键的点击
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    InputMethodManager manager = (InputMethodManager) etSearch.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    if (etSearch.getText().toString().length() > 0) {
                        mPresenter.search(etSearch.getText().toString());
                    } else {
                        etSearch.setText(etSearch.getHint().toString());
                        etSearch.setSelection(etSearch.getText().toString().length());//将光标移至文字末尾
                        mPresenter.search(etSearch.getHint().toString());
                    }

                    return true;
                }
                return false;
            }
        });

        searchParentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_all:
                        SearchBean searchBean = searchParentAdapter.getData().get(position);
                        Intent intent = new Intent(SearchActivity.this, SearchAllAlbumActivity.class);
                        intent.putExtra("search", searchBean);
                        startActivity(intent);

                        break;
                }
            }
        });
        searchParentAdapter.setClick(new SearchParentAdapter.Click() {
            @Override
            public void childClick(int parent, int child) {

                SearchBean.SearchResultsBean searchResultsBean = searchParentAdapter.getData().get(parent).getSearchResults().get(child);
                ForwardAddressBean forwardAddressBean = new Gson().fromJson(searchResultsBean.getForwardAddress(), ForwardAddressBean.class);
                switch (searchResultsBean.getForwardType()) {
                    //1:原生   2:web
                    case "1":
                        ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                                .withString(Constant.type, String.valueOf(searchResultsBean.getType()))
                                .withString(Constant.id, forwardAddressBean.getId())
                                .navigation();
                        break;
                    case "2":
                        WebViewActivity.launcher(UIUtil.getContext(), forwardAddressBean.getWebUrl(), searchResultsBean.getTitle());
                        break;
                }
            }
        });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerSearchComponent.builder().appComponent(appComponent)
                .searchModule(new SearchModule(this))
                .build().inject(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle://取消
                finish();
                break;
            case R.id.tv_clear_history://清空历史搜索
                mPresenter.deleteAll();
                break;

        }
    }


    @Override
    public void searchWordsSuccess(List<SearchHotWordBean> searchHotWordBeans) {
        hotWords = new ArrayList<>();
        for (int i = 0; i < searchHotWordBeans.size(); i++) {
            hotWords.add(searchHotWordBeans.get(i).getWordsName());
        }
        initHotWord();
    }

    @Override
    public void searchSuccess(List<SearchBean> searchBeans) {
        searchParentAdapter.setNewData(searchBeans);
        if (searchBeans.size() == 0) {
            searchParentAdapter.setEmptyView(getEmptyView(R.drawable.icon_search_empty, "没有相关内容"));
        }
        recycleParent.setVisibility(View.VISIBLE);
    }

    @Override
    public void getFindNearWordSuccess(List<String> historys) {
        if (null != historys && historys.size() > 0) {
            llHistory.setVisibility(View.VISIBLE);
            historyWords = new ArrayList<>();
            for (int i = 0; i < historys.size(); i++) {
                historyWords.add(historys.get(i));
            }
            initHistoryWord();
        } else {
            llHistory.setVisibility(View.GONE);
        }

    }

    @Override
    public void deleteAllSuccess() {
        llHistory.setVisibility(View.GONE);
    }
}
