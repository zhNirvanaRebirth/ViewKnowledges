package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.Rotation3dAnimation;

/**
 * Created by zhwilson on 2017/4/19.
 */
public class Camera3dRotationActivity extends Activity {
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_rotation);
        imageView = (ImageView) findViewById(R.id.picture);
        imageView1 = (ImageView) findViewById(R.id.picture1);
        imageView2 = (ImageView) findViewById(R.id.picture2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float centerX = v.getWidth() / 2;
                float centerY = v.getHeight() / 2;
                Log.d("zhwilson", "centerX:" + centerX + "-centerY:" + centerY);
                Rotation3dAnimation rotation3dAnimation = new Rotation3dAnimation(0, 180, centerX, centerY, 0f, true);
                rotation3dAnimation.setDuration(10000);
                rotation3dAnimation.setFillAfter(true);
                rotation3dAnimation.setInterpolator(new LinearInterpolator());
                v.startAnimation(rotation3dAnimation);

//                float centerX1 = imageView1.getWidth()/2;
//                float centerY1 = imageView1.getHeight()/2;
//                Rotation3dAnimation rotation3dAnimation1 = new Rotation3dAnimation(0, 180, centerX1, centerY1, 0f, false);
//                rotation3dAnimation1.setDuration(10000);
//                rotation3dAnimation1.setFillAfter(true);
//                rotation3dAnimation1.setInterpolator(new LinearInterpolator());
//                imageView1.startAnimation(rotation3dAnimation1);
//
//                float centerX2 = imageView2.getWidth()/2;
//                float centerY2 = imageView2.getHeight()/2;
//                Rotation3dAnimation rotation3dAnimation2 = new Rotation3dAnimation(0, 180, centerX2, centerY2, 0f, true);
//                rotation3dAnimation2.setDuration(10000);
//                rotation3dAnimation2.setFillAfter(true);
//                rotation3dAnimation2.setInterpolator(new LinearInterpolator());
//                imageView2.startAnimation(rotation3dAnimation2);
            }
        });

    }
}
