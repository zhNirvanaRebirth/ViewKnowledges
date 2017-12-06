package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2017/9/8.
 */
public class AnimatorView extends View {
    private Paint paint;
    private float length = 0.0f;
    private int color = 0xffff0000;

    public AnimatorView(Context context) {
        this(context, null);
    }

    public AnimatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initPaint();
    }

    public AnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        RectF rectF = new RectF(100, 300, 100 + length, 350);
//        canvas.drawRect(rectF, paint);

        canvas.drawCircle(300f, 500f, 100f, paint);
    }
}
