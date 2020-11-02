package com.power.home.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Xfermode;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.power.home.common.Constant;

import java.lang.ref.WeakReference;

/**
 * Created by zhp on 2019/5/8
 */
public class AutoPollRecyclerView extends RecyclerView {


    private int index = 0;
    AutoPollTask autoPollTask;
    private boolean running; //表示是否正在自动轮询
    private boolean canRun;//表示是否可以自动轮询

    public AutoPollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        autoPollTask = new AutoPollTask(this);
    }


    static class AutoPollTask implements Runnable {

        private final WeakReference mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(AutoPollRecyclerView reference) {
            this.mReference = new WeakReference(reference);
        }

        @Override
        public void run() {
            AutoPollRecyclerView recyclerView = (AutoPollRecyclerView) mReference.get();
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.smoothScrollToPosition(++recyclerView.index);
                recyclerView.postDelayed(recyclerView.autoPollTask, Constant.TIME_AUTO_POLL);
            }
            /*if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(2, 2);
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL);
            }*/
        }

    }


    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running)
            stop();
        canRun = true;
        running = true;
        postDelayed(autoPollTask, Constant.TIME_AUTO_POLL);
    }

    public void stop() {
        running = false;
        removeCallbacks(autoPollTask);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (running)
                    stop();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                if (canRun)
                    start();
                break;
        }
        return super.onTouchEvent(e);
    }

    // 实现渐变效果

    Paint mPaint;

    private int layerId;

    private LinearGradient linearGradient;

    private int preWidth = 0;// Recyclerview宽度动态变化时，监听每一次的宽度

    public void doTopGradualEffect(final int itemViewWidth) {

        mPaint = new Paint();

        // dst_in 模式，实现底层透明度随上层透明度进行同步显示（即上层为透明时，下层就透明，并不是上层覆盖下层)

        final Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        mPaint.setXfermode(xfermode);

        addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(canvas, parent, state);

                // 当linearGradient为空即第一次绘制 或 Recyclerview宽度发生改变时，重新计算透明位置

                if (linearGradient == null || preWidth != parent.getWidth()) {

                    // 透明位置从最后一个 itemView 的一半处到 Recyclerview 的最右边

                    linearGradient = new LinearGradient(parent.getWidth() - (itemViewWidth / 2), 0.0f, parent.getWidth(), 0.0f, new int[]{Color.BLACK, 0}, null, Shader.TileMode.CLAMP);

                    preWidth = parent.getWidth();

                }

                mPaint.setXfermode(xfermode);

                mPaint.setShader(linearGradient);

                canvas.drawRect(0.0f, 0.0f, parent.getRight(), parent.getBottom(), mPaint);

                mPaint.setXfermode(null);

                canvas.restoreToCount(layerId);

            }

            @Override

            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

                super.onDraw(c, parent, state);

                // 此处 Paint的参数这里传的null， 在传入 mPaint 时会出现第一次打开黑屏闪现的问题

                // 注意 saveLayer 不能省也不能移动到onDrawOver方法里

                layerId = c.saveLayer(0.0f, 0.0f, (float) parent.getWidth(), (float) parent.getHeight(), null, Canvas.ALL_SAVE_FLAG);

            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

                super.getItemOffsets(outRect, view, parent, state);

            }

        });

    }

}
