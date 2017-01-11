package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2016/12/29.
 */
public class ViewPathMeasure extends View {
    private int width;
    private int height;
    private Paint circlePaint;
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private Matrix matrix;
    private float currentPos = 0.0f;
    private Bitmap arrow;

    private float start = 0.0f;
    private float end = 0.0f;

    public ViewPathMeasure(Context context) {
        this(context, null);
    }

    public ViewPathMeasure(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPathMeasure(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10.0f);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);

        matrix = new Matrix();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrow, options);
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
        canvas.drawColor(Color.WHITE);
        canvas.translate(width / 2, height / 2);

//        Path path = new Path();
//        path.addCircle(0, 0, 200, Path.Direction.CW);
//        PathMeasure pathMeasure = new PathMeasure(path, false);
//
//        currentPos += 0.005;
//        if (currentPos > 1)
//            currentPos = 0;


//        pathMeasure.getPosTan(pathMeasure.getLength() * currentPos, pos, tan);
//        matrix.reset();
//        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);
//        matrix.postRotate(degree, arrow.getWidth() / 2, arrow.getHeight() / 2);
//        matrix.postTranslate(pos[0] - arrow.getWidth() / 2, pos[1] - arrow.getHeight() / 2);
//
//        pathMeasure.getMatrix(pathMeasure.getLength()*currentPos, matrix, PathMeasure.POSITION_MATRIX_FLAG|PathMeasure.TANGENT_MATRIX_FLAG);
//        matrix.preTranslate( - arrow.getWidth() / 2,  - arrow.getHeight() / 2);
//
//
//        canvas.drawPath(path, circlePaint);
//        canvas.drawBitmap(arrow, matrix, circlePaint);

        Path path = new Path();
        Path dist = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure(path, true);
        start += 0.01f;
        if (start > 1)
            start = 0;
        end = (float) Math.sin(start * Math.PI) * pathMeasure.getLength() / 10 + 0.05f;
        pathMeasure.getSegment(start*pathMeasure.getLength(), start*pathMeasure.getLength()+end, dist, true);

        canvas.drawPath(dist, circlePaint);
        postInvalidate();
    }
}
