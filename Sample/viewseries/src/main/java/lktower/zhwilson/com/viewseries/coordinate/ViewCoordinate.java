package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhwilson on 2016/9/20.
 * 梳理移动设备中的坐标相关知识
 * <p/>
 * 在移动设备中，坐标原点在屏幕左上角，向右为x轴正方形，先下为y轴正方向
 * <p/>
 * 了解了坐标系知识可以帮助知晓当前view的位置
 */
public class ViewCoordinate extends View {
    public ViewCoordinate(Context context) {
        super(context);
    }

    public ViewCoordinate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewCoordinate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("zhwilson", "viewContent与父view的距离:" + getLeft() + "-" + getTop());

        Log.i("zhwilson", "viewContent的size:" + w + "-" + h);
        /**
         * 通过计算出父view的高度与宽度+父view与最外层view的margin可以验证getleft， getright，gettop， getbottom是相对于父view的距离
         */
        int viewCapWidth = (getLeft() + w/2)*2;
        int viewCapHeight = (getTop() + h/2)*2;
        Log.i("zhwilson", "viewContent父view宽度：" + viewCapWidth);
        Log.i("zhwilson", "viewContent父view高度：" + viewCapHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("zhwilson", "viewContent水平触摸点：" + event.getX() + "-" + event.getRawX());
        Log.i("zhwilson", "viewContent竖直触摸点：" + event.getY() + "-" + event.getRawY());
//        return super.onTouchEvent(event);
        return false;
    }
}
