package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2017/4/10.
 * Matrix 多边形（Polygon）的控制
 */
public class MatrixPoly extends View {
    private Bitmap bitmap;
    private Matrix matrix;
    private int width, height;

    public MatrixPoly(Context context) {
        this(context, null);
    }

    public MatrixPoly(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixPoly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ffff);
        matrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        float scale = width * 1.0f / bitmap.getWidth() > height * 1.0f / bitmap.getHeight() ? height * 1.0f / bitmap.getHeight() : width * 1.0f / bitmap.getWidth();
        calcuPoly();
        matrix.postTranslate(100, 200);
//             matrix.postScale(scale, scale);
        canvas.drawBitmap(bitmap, matrix, new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    private void calcuPoly() {
        float bitmapW = bitmap.getWidth();
        float bitmapH = bitmap.getHeight();
        float[] src = {
                100, 0,
                200, 0,
                200, bitmapH,
                100, bitmapH
        };
        float[] dst = {
                100, 20,
                200, 0,
                200, bitmapH,
                100, bitmapH - 20
        };
        //此方法的操作都是setXxx:设置，会覆盖掉之前的数值，导致之前的操作失效。控制点越多，在一些简单的变换（如：平移），需要处理的点就变得更多，增加了计算
        //pointCount: 0:相当于对matrix reset;
        // 1:相当于translate 控制点是左上角点，所以设置其他点是没有用的
        //2:相当于平移、缩放，旋转，控制点是左上和右上
        //3:相当于平移、缩放，旋转，错切，控制点是左上，右上和右下
        //4:可以做任意拉伸
        matrix.setPolyToPoly(src, 0, dst, 0, 4);
    }
}
