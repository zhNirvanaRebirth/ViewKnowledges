package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhwilson on 2017/4/26.
 */
public class SlipperyView extends ViewGroup {
    private Context mContext;

    public SlipperyView(Context context) {
        this(context, null);
    }

    public SlipperyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlipperyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("zhwilson", "SlipperyView init");
        mContext = context;
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("zhwilson", "SlipperyView onMeasure");
        //执行父view的onMeasure
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e("zhwilson", "SlipperyView onSizeChanged");
        //执行父view的onSizeChanged
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("zhwilson", "SlipperyView onLayout");
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }
}
