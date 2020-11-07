package com.power.home.common;

import android.app.Activity;
import android.drm.DrmStore;

import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

public class MyFragmentManager {
    private static MyFragmentManager mInstance = new MyFragmentManager();

    private WeakReference<Fragment> sCurrentFragmentWeakRef;

    private   MyFragmentManager(){

    }
    public static MyFragmentManager getInstance(){
        return mInstance;
    }
    public Fragment getCurrentFragment(){
        Fragment currentFragment = null;
        if(sCurrentFragmentWeakRef != null){
            currentFragment = sCurrentFragmentWeakRef.get();
        }
        return  currentFragment;
    }

    public void setCurrentFragment(Fragment fragment){
        sCurrentFragmentWeakRef = new WeakReference<Fragment>(fragment);
    }
}
