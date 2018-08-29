package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by zhwilson on 2018/1/10.
 * 事件分发ViewGroup
 */
public class EventDispatchViewGroup extends RelativeLayout {
    public EventDispatchViewGroup(Context context) {
        super(context);
    }

    public EventDispatchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventDispatchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EventDispatchViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("zhwilson", "ViewGroup dispatchTouchEvent:" + ev.getActionMasked());
//        return true;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("zhwilson", "ViewGroup onInterceptTouchEvent:" + ev.getActionMasked());
//        if (ev.getAction() == MotionEvent.ACTION_MOVE){
//            Log.e("zhwilson", "ViewGroup onInterceptTouchEvent: action is ACTION_MOVE, do by myself!");
//            return true;
//        }
//        return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zhwilson", "ViewGroup onTouchEvent:" + event.getActionMasked());
//        return true;
        return super.onTouchEvent(event);
    }
}
