package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2017/12/28.
 * 使用一张UI图通过绘制不同区域实现帧动画
 */
public class ZombieRunrunrunView extends View {
    private Bitmap zombie;
    private float rectW;
    private float rectH;
    private Rect src;
    private RectF dst;

    private int width;
    private int height;

    private int index = 0;
    private PointF srcPoint = new PointF();
    private float density;

    public ZombieRunrunrunView(Context context) {
        this(context, null);
    }

    public ZombieRunrunrunView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZombieRunrunrunView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = getResources().getDisplayMetrics().density;
        zombie = BitmapFactory.decodeResource(getResources(), R.drawable.zombie);
        rectW = (zombie.getWidth() - 3 * density) * 1.0f / 11 - 1 * density;
        rectH = (zombie.getHeight() - 1 * density) * 1.0f / 2 - 1 * density;

        dst = new RectF(-rectW / 2, -rectH / 2, rectW / 2, rectH / 2);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void zombieRunrunrun() {
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.translate(width * 1.0f / 2, height * 1.0f / 2);
        if (index == 0) {
            srcPoint.x = 2 * density;
            srcPoint.y = 1 * density;
        } else if (index == 11) {
            srcPoint.x = 2 * density;
            srcPoint.y = 2 * density + rectH;
        } else {
            srcPoint.x = srcPoint.x + rectW + 1 * density;
        }
        src = new Rect((int) srcPoint.x, (int) srcPoint.y, (int) (srcPoint.x + rectW), (int) (srcPoint.y + rectH));
        canvas.drawBitmap(zombie, src, dst, new Paint(Paint.ANTI_ALIAS_FLAG));
        index++;
        if (index == 22)
            index = 0;
    }
}
