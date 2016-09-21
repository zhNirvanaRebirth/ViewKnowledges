package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;

import lktower.zhwilson.com.sample.R;

/**
 * Created by zhwilson on 2016/9/21.
 * 自定义View/ViewGroup流程
 */
public class ViewProcessActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_process);
    }
}
