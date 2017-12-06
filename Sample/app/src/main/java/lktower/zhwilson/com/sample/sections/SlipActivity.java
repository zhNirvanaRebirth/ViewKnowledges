package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.SlipableView;

/**
 * Created by zhwilson on 2017/4/26.
 */
public class SlipActivity extends Activity {
    private SlipableView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip);
        sv = (SlipableView) findViewById(R.id.sv);
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.setScroll();
            }
        });

    }
}
