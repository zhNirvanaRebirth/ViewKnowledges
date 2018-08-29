package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhilson on 2018/1/9.
 * 事件分发View
 */
public class EventDispatchView extends View {
    private Map<Integer, Integer> ids = new HashMap<>();

    public EventDispatchView(Context context) {
        super(context);
    }

    public EventDispatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventDispatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EventDispatchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("zhwilson", "View dispatchTouchEvent:" + event.getActionMasked());
//        return true;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zhwilson", "View onTouchEvent:" + event.getActionMasked());
//        Log.e("zhwilson", "View onTouchEvent: x:" + event.getX() + "-y:" + event.getY());
//        Log.e("zhwilson", "View onTouchEvent: rawX:" + event.getRawX() + "-rawY:" + event.getRawY());
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//            Log.e("zhwilson", "View onTouchEvent: do action ACTION_DOWN!");
//            return true;
//        }
//        return true;

        //multi pointer
        int pointerIndex = event.getActionIndex();//获取这个事件是哪个手指产生的
        int pointerCount = event.getPointerCount();//获取当前有几个手指在屏幕上

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN://主要/第一个手指按下
                Log.e("touch_event", "View onTouchEvent: ACTION_DOWN");
                Log.e("touch_event", "View onTouchEvent: pointerIndex：" + pointerIndex);
                Log.e("touch_event", "View onTouchEvent: pointerCount：" + pointerCount);
                break;
            case MotionEvent.ACTION_UP://主要/第一手指抬起
                Log.e("touch_event", "View onTouchEvent: ACTION_UP");
                Log.e("touch_event", "View onTouchEvent: pointerIndex：" + pointerIndex);
                Log.e("touch_event", "View onTouchEvent: pointerCount：" + pointerCount);
                break;
            case MotionEvent.ACTION_MOVE://只有有手指在移动，就收到ACTION_MOVE事件
                Log.i("touch_event", "View onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_POINTER_DOWN://非主要手指按下
                Log.e("touch_event", "View onTouchEvent: ACTION_POINTER_DOWN");
                Log.e("touch_event", "View onTouchEvent: pointerIndex：" + pointerIndex);
                Log.e("touch_event", "View onTouchEvent: pointerCount：" + pointerCount);
//                int pointerIndex = event.getActionIndex();
//                int pointerId = event.getPointerId(pointerIndex);
//                ids.put(pointerId, pointerId);
                break;
            case MotionEvent.ACTION_POINTER_UP://非主要手指抬起
                Log.e("touch_event", "View onTouchEvent: ACTION_POINTER_UP");
                Log.e("touch_event", "View onTouchEvent: pointerIndex：" + pointerIndex);
                Log.e("touch_event", "View onTouchEvent: pointerCount：" + pointerCount);
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
    }
}
