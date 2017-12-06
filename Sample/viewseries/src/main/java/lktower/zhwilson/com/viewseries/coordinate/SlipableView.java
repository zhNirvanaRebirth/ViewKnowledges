package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zhwilson on 2017/4/26.
 * 仿写可滑动删除的item
 * SlipableView作为item中最外层viewGroup，包含两个child，一个是可以滑动的view（SlipperyView），一个是包含如删除，标记什么的linearlayout
 */
public class SlipableView extends ViewGroup {
    private Context mContext;
    private int mWidth, mHeight;//控件的大小
    private Scroller scroller;

    public SlipableView(Context context) {
        this(context, null);
    }

    public SlipableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlipableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("zhwilson", "SlipableView init");
        mContext = context;
        init();
    }

    private void init() {
        scroller = new Scroller(mContext);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("zhwilson", "SlipableView onMeasure");
        //执行父view的onMeasure
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量孩子们
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("zhwilson", "SlipableView onSizeChanged");
        //执行父view的onSizeChanged
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("zhwilson", "SlipableView onLayout");
        int childCount = getChildCount();
        if (childCount != 2)//只能有两个子view
            new Throwable("SlipableView can only contain two child!");

        View childFirst = getChildAt(0);
        childFirst.layout(mWidth - childFirst.getMeasuredWidth(), 0, mWidth, childFirst.getMeasuredHeight());

        View childSecond = getChildAt(1);
        childSecond.layout(0, 0, mWidth, mHeight);
        scroller.setFinalX(100);
    }

    public void setScroll() {
        getChildAt(1).scrollTo(100, 0);
    }

}
