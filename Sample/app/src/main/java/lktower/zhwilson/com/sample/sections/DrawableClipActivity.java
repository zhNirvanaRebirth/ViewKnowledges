package lktower.zhwilson.com.sample.sections;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.DrawableClip;

/**
 * Created by zhwilson on 2017/1/4.
 */
public class DrawableClipActivity extends Activity {
    private DrawableClip drawableClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_clip);
        init();
    }

    private void init() {
        ImageView imageView = (ImageView) findViewById(R.id.dc);
        drawableClip = new DrawableClip(imageView.getDrawable());
        imageView.setImageDrawable(drawableClip);
    }
}
