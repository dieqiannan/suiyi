package com.dqn.bubbleball;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyCircleView extends View {
    private final static String TAG = "MyCircleView";
    //画笔
    private Paint mPaint;

    //圆的半径
    private float mRadius = 50f;


    private int mColor;

    // View宽
    private float mWidth = 0;
    // View高
    private float mHeight = 0;

    private Context mContext;

    public MyCircleView(Context context) {
        this(context, null);
        mContext = context;
    }

    //自定义veiw在布局中使用，必须实现的一个构造器
    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //构造一个paint
        mContext = context;

        // 获取用户配置属性
        TypedArray tya = context.obtainStyledAttributes(attrs, R.styleable.MyCircleView);

        mColor = tya.getColor(R.styleable.MyCircleView_myCircleView_cColor, Color.BLUE);

        tya.recycle();
    }

    public void setColor(int color) {
        mColor = color;
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null) {
            init();
        }

        mRadius = getWidth() * 1f / 2;
        mWidth = getWidth();
        mHeight = getHeight();

        canvas.drawCircle(mWidth*1f/2, mHeight*1f/2, mRadius, mPaint);
    }


}
