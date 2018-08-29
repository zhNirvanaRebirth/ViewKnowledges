package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.FoldSideBarView;

/**
 * Created by mshz on 2018/1/16.
 */
public class FoldSideBarActivity extends Activity {
    private FoldSideBarView sideBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fold_side_bar);
        sideBar = (FoldSideBarView) findViewById(R.id.side_bar);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("zhwilson", "FoldSideBarActivity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zhwilson", "FoldSideBarActivity onTouchEvent");
        return super.onTouchEvent(event);
    }
}
