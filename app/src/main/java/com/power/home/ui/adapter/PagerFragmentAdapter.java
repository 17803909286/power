package com.power.home.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhp on 2020-03-14
 */
public class PagerFragmentAdapter extends FragmentPagerAdapter {

    private IPageTitleListener pageTitleListener;
    private List<Fragment> fragments = null;

    public PagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (null != fragments)
            return fragments.get(position);
        else
            return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (null != pageTitleListener) {
            return pageTitleListener.getTitle(position);
        }
        return "";
    }

    @Override
    public int getCount() {
        return null != fragments ? fragments.size() : 0;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public interface IPageTitleListener {
        public String getTitle(int position);
    }

    public void setPageTitleListener(IPageTitleListener pageTitleListener) {
        this.pageTitleListener = pageTitleListener;
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
