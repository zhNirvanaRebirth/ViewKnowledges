package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.EventDispatchView;

/**
 * Created by mshz on 2018/1/9.
 */
public class EventDispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        setContentView(R.layout.activity_event_dispatch);
        EventDispatchView eventDispatchView = (EventDispatchView) findViewById(R.id.event_dispatch_view);
        eventDispatchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("zhwilson", "View onTouch:" + event.getActionMasked());
                return false;
            }
        });

//        eventDispatchView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.e("zhwilson", "View onLongClick");
//                return false;
////                return true;
//            }
//        });
//
//        eventDispatchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("zhwilson", "View onClick");
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("zhwilson", "Activity dispatchTouchEvent:" + ev.getActionMasked());
//        return true;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zhwilson", "Activity onTouchEvent:" + event.getActionMasked());
//        return true;
        return super.onTouchEvent(event);
    }
}
