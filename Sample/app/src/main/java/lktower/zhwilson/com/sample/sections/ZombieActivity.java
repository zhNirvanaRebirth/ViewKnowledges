package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.ZombieRunrunrunView;

/**
 * Created by mshz on 2017/12/28.
 */
public class ZombieActivity extends Activity {
    private ZombieRunrunrunView zombie;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            zombie.zombieRunrunrun();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zombie_runrunrun);
        zombie = (ZombieRunrunrunView) findViewById(R.id.zombie_run);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();
    }
}
