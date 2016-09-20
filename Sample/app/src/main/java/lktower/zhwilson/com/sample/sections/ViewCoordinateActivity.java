package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.ViewCoordinate;

/**
 * Created by zhwilson on 2016/9/20.
 * 测试验证view坐标系属性知识
 */
public class ViewCoordinateActivity extends Activity {
    //屏幕的宽，单位px
    private int screenWidth;
    //屏幕的高，单位px
    private int screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coordiante);
        screenSize();
        init();
    }
    private void screenSize(){
        //这里获取到的是0
        //获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.i("zhwilson", "statusBarHeight:" + statusBarHeight);
        //获取标题栏高度
        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        //statusBarHeight是上面所求的状态栏的高度
        int titleBarHeight = contentTop - statusBarHeight;
        Log.i("zhwilson", "titleBarHeight:" + titleBarHeight);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        Log.i("zhwilson", "screen:" + screenWidth + "-" + screenHeight);
    }

    private void init(){
        RelativeLayout viewCap = (RelativeLayout) findViewById(R.id.view_coordinate_cap);
        ViewCoordinate viewContent = (ViewCoordinate) findViewById(R.id.view_coordinate_content);
        Log.i("zhwilson", "viewCap到父view的距离：" + dp2px(20));
    }

    private int dp2px(int dp){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }
}
