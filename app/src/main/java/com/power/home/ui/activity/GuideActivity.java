package com.power.home.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.widget.LinearLayout;

import com.power.home.R;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.fragment.GuideOneFragment;
import com.power.home.ui.fragment.GuideThreeFragment;
import com.power.home.ui.fragment.GuideTwoFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by S on 2018/2/24.
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.container)
    LinearLayout container;

    private ArrayList<Fragment> mFragmentList;

    @Override
    public int setLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new GuideOneFragment());
        mFragmentList.add(new GuideTwoFragment());
        mFragmentList.add(new GuideThreeFragment());
        vpGuide.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }


        });
        return true;
    }


    @Override
    public void setListener() {

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }
}
