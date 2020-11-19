package com.power.home.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.power.home.R;
import com.power.home.common.util.DensityUtil;
import com.power.home.di.component.AppComponent;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.internal.CustomAdapt;

public class GuideOriginatorActivity extends BaseActivity   {

    private Handler hideHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            finish();
        }
    };
    @Override
    public int setLayout() {
        return R.layout.activity_originator;
    }

    @Override
    public void getExtras() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public boolean init() {
        this.scaleImage(findViewById(R.id.iv_head),R.mipmap.dlxs_app);
        hideHandler.sendEmptyMessageDelayed(0,3000);

        return true;
    }

    @Override
    public void setListener() {

    }
    private void scaleImage(View view,int sourceId){

        Point point = new Point();
        this.getWindow().getWindowManager().getDefaultDisplay().getSize(point);
        Bitmap resourceBitmap = BitmapFactory.decodeResource(this.getResources(),sourceId);
        if (resourceBitmap == null) {
            return;
        }


        int bitmapScaledHeight = Math.round(resourceBitmap.getHeight() * point.x * 1.0f / resourceBitmap.getWidth());

        final Bitmap scaledBitmap = Bitmap.createScaledBitmap(resourceBitmap, point.x, bitmapScaledHeight, false);

       view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
           @Override
           public boolean onPreDraw() {
               if(scaledBitmap.isRecycled()){
                   return true;
               }
               int viewHeight = view.getMeasuredHeight();
               Log.i("offsetheightview",String.valueOf(viewHeight));
               int offset = (scaledBitmap.getHeight() - viewHeight) / 2;
               Log.i("offsetheight",String.valueOf(offset));
               Bitmap finallyBitmap = Bitmap.createBitmap(scaledBitmap, 0, offset, scaledBitmap.getWidth(),
                       scaledBitmap.getHeight() - offset * 2 );
               if (!finallyBitmap.equals(scaledBitmap)) {//如果返回的不是原图，则对原图进行回收
                   scaledBitmap.recycle();
                   System.gc();
               }
               ((ImageView)view).setImageBitmap(finallyBitmap);
               return true;

           }
       });
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideHandler.removeCallbacksAndMessages(null);
    }


}
