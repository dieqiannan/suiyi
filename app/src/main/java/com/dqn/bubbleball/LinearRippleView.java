package com.dqn.bubbleball;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 加载动效,
 *
 */
public class LinearRippleView extends LinearLayout {
    private final static String TAG = "LinearRippleView";
    private Context mContext;

    private int child = 0;
    private float mStartScale = 0.5f;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                start();
            } else if (msg.what == 2) {
                startAnim();
            }else if (msg.what == 3) {
                child = 0;
                mHandler.removeCallbacksAndMessages(null);
                setAnimNomal();
            }
        }
    };
    private int childCount;
    private boolean isAnimSmall_end;
    private boolean isAnimBig_end;

    public LinearRippleView(Context context) {
        this(context, null);
    }

    public LinearRippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        // 获取用户配置属性

        setOrientation(LinearLayout.HORIZONTAL);
        init();


    }

    private void init() {
        mContext = getContext();


        // 设置View的圆为半透明
        //setBackgroundColor(Color.TRANSPARENT);
        //setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setAnimNomal();
    }

    public void setAnimNomal() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.setScaleY(mStartScale);
            childAt.setScaleX(mStartScale);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        //start();
    }

    public void start() {

        if (getChildAt(0) != null) {
            //开始动画
            startAnim();
        } else {
            mHandler.sendEmptyMessageDelayed(1, 100);
        }

    }

    public void startAnim() {

        childCount = getChildCount();

        if (child < childCount) {


        }else {
            //child = childCount -1;
            //animLast();
            child = 0;
        }

        if(child ==0){
            animLast(childCount-1);
            anim(child);
            child++;
            mHandler.sendEmptyMessageDelayed(2, 200);
        }else  if (child > 0) {


            //还原
            int lastChild = child -1;
            animLast(lastChild);
            anim(child);
            child++;
            mHandler.sendEmptyMessageDelayed(2, 200);
        }

    }

    /**
     * 上一个还原
     */
    private void animLast(int child) {
        View childAt = getChildAt(child);

        childAt.animate()
                .scaleX(mStartScale)
                .scaleY(mStartScale)
                .setDuration(400)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        isAnimSmall_end = false;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        isAnimSmall_end = true;
                    }
                })
                .start();
    }

    /**
     * 放大
     * @param child
     */
    private void anim(int child) {
        View childAt = getChildAt(child);

        childAt.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(400)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        isAnimBig_end = false;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        isAnimBig_end = true;
                    }
                })
                .start();
    }


    public void clearAnim() {
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 还原
     */
    public void resore() {

        if(!isAnimBig_end || !isAnimBig_end){
            mHandler.sendEmptyMessageDelayed(3, 400);
        }else {
            setAnimNomal();
            child = 0;
            mHandler.removeCallbacksAndMessages(null);
        }
    }


}