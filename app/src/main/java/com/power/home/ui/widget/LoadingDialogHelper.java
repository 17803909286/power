package com.power.home.ui.widget;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.android.tu.loadingdialog.LoadingDialog;

public class LoadingDialogHelper {


    private LoadingDialog.Builder loadingBuilder;
    private LoadingDialog loadingDialog;
    private Context mContext;


    public LoadingDialogHelper(Context context){
        this.mContext = context;
    }

    public void init(){
        loadingBuilder = new LoadingDialog.Builder(this.mContext);
        loadingBuilder.setCancelable(false);
        loadingBuilder.setMessage("正在加载...");
        loadingBuilder.setCancelOutside(true);
        loadingDialog = loadingBuilder.create();

    }


    public void changeMessageToAnother(String message){
        loadingBuilder.setMessage(message);
    }

    public void show(){
        loadingDialog.show();
    }

    public void hide(){
        loadingDialog.hide();
    }
}
