package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.CircleProgress;

/**
 * Created by zhwilson on 2017/12/7.
 */
public class CircleProgressActivity extends Activity {
    private CircleProgress circleProgress;
    private float progress = 0.0f;
    private float step = 0.0f;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            circleProgress.setProgress(progress);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        circleProgress = (CircleProgress) findViewById(R.id.circle_progress);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < 100) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress += step;
                    step += 0.005f;
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();
    }

}
