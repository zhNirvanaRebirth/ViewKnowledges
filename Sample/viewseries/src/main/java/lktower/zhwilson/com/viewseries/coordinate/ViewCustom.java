package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2016/9/21.
 *
 * 自定义view的流程
 *
 * 1、构造函数：进行相关的初始化
 *
 * 2、onMeasure 测量view的大小
 *
 * 3、onSizeChanged 确定view的大小
 *
 * 4、onDraw 绘制内容
 *
 * 5、对外提供操作方法和监听回调
 */
public class ViewCustom extends View{
    public ViewCustom(Context context) {
        super(context);
    }

    public ViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 如果对View的宽高进行修改了，不要调用super.onMeasure(widthMeasureSpec,heightMeasureSpec);要调用setMeasuredDimension(widthsize,heightsize); 这个函数。
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //View的大小不仅由自身所决定，同时也会受到父控件的影响，为了我们的控件能更好的适应各种情况，一般会自己进行测量。
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//获取控件确切的宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//获取控件宽度的测量模式

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//获取控件确切的高度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//获取控件高度的测量模式
    }

    /**
     * 这个函数在视图大小发生改变时调用。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
