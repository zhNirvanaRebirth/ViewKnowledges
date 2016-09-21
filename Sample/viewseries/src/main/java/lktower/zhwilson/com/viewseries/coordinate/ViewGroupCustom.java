package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zhwilson on 2016/9/21.
 * <p/>
 * 自定义ViewGroup流程
 * <p/>
 * 1、构造函数：执行初始化操作
 * <p/>
 * 2、onMeasure 测量ViewGroup大小
 * <p/>
 * 3、onSizeChanged 确定ViewGroup的大小
 * <p/>
 * 4、onLayout 确定子view布局
 */
public class ViewGroupCustom extends ViewGroup {
    public ViewGroupCustom(Context context) {
        super(context);
    }

    public ViewGroupCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 确定子View布局位置
     *
     * @param b
     * @param left
     * @param top
     * @param right
     * @param bottom(均是相对于父布局)
     */
    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
        //onLayout一般是循环取出子View，然后经过计算得出各个子View位置的坐标值，然后用child.layout(l, t, r, b);函数设置子View位置。
        int childCount = getChildCount();

        //有问题：获取不到子view的相关属性（大小），后面改正
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                child.layout(20, 10, 220+childWidth, 110+childHeight);
            }
        }
    }
}
