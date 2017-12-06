package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.CoordinateProbe;

/**
 * Created by zhwilson on 2017/12/4.
 */
public class CoordinateActivity extends Activity implements ViewTreeObserver.OnGlobalLayoutListener {
    private CoordinateProbe coordinateProbe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);
        coordinateProbe = (CoordinateProbe) findViewById(R.id.coordinate);

        coordinateProbe.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        Log.e("zhwilson", "top:" + coordinateProbe.getTop()
                + "left:" + coordinateProbe.getLeft()
                + "right:" + coordinateProbe.getRight()
                + "bottom:" + coordinateProbe.getBottom());
    }
}
