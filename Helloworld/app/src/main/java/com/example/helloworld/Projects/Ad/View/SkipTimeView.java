package com.example.helloworld.Projects.Ad.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.helloworld.R;

/**
 * Created by hanchenghai on 2018/3/20.
 */

public class SkipTimeView extends View {


    //文字画笔
    TextPaint mTextPaint;
    //内部实心环
    Paint circleP;
    //外部圆环
    Paint outerP;

    //文案
    String content = "跳过";
    int padding = 5;
    //内圆直径
    int inner;
    //外圆直径
    int outter;

    //外圈的角度
    int degrees;
    //外圈描绘区域
    RectF outerRect;

    public void setListener(OnTimeClickListener listener) {
        mListener = listener;
    }

    OnTimeClickListener mListener;

    public SkipTimeView(Context context, AttributeSet attrs) {
        super(context);
        //获取xml的属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SkipTimeView);
        int inner = array.getColor(R.styleable.SkipTimeView_innerColor, Color.BLACK);
        int outer = array.getColor(R.styleable.SkipTimeView_circleColor, Color.GREEN);


        mTextPaint = new TextPaint();
        //抗锯齿
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(Color.WHITE);

        //内圈画笔
        circleP = new Paint();
        circleP.setFlags(Paint.ANTI_ALIAS_FLAG);
        circleP.setColor(Color.BLUE);

        //外圈
        outerP = new Paint();
        outerP.setFlags(Paint.ANTI_ALIAS_FLAG);
        outerP.setColor(Color.GREEN);
        outerP.setStyle(Paint.Style.STROKE);
        outerP.setStrokeWidth(2);

        //文字宽度
        float text_width = mTextPaint.measureText(content);
        //内圆直径
        inner = (int) text_width + padding * 2;
        //外圆直径
        outter = inner + 2 * padding;

        outerRect = new RectF(padding / 2, padding / 2, outter - padding / 2, outter - padding / 2);
        //使用完一定要回收
        array.recycle();
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //告诉系统控件最后的大小
        setMeasuredDimension(outter, outter);
    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //内部圆
        canvas.drawCircle(outter / 2, outter / 2, inner / 2, circleP);

        //旋转-90
        canvas.save();
        canvas.rotate(-90f, outter / 2, outter / 2);
        canvas.drawArc(outerRect, 0f, degrees, false, outerP);
        canvas.restore();


        float y = (canvas.getHeight() / 2);
        float de = mTextPaint.descent();
        float as = mTextPaint.ascent();


        canvas.drawText(content, 2 * padding, y - (de + as) / 2, mTextPaint);

    }




    public void setProgress(int total, int now) {
        int space = 360 / total;
        degrees = space * now;
        //刷新UI线程
        invalidate();
        //子线程
        //postInvalidate();
    }


    public SkipTimeView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                setAlpha(0.3f);
                break;
            }
            case MotionEvent.ACTION_UP: {
                setAlpha(1.0f);
                if (mListener != null) {
                    mListener.onClickTime(this);
                }
                break;
            }
        }
        return true;
    }
}
