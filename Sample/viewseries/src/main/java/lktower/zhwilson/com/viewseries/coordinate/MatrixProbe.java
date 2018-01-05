package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2018/1/2.
 * Matrix相关方法与原理理解
 */
public class MatrixProbe extends View {
    private int width;
    private int height;
    private Paint paint;
    private Paint linePaint;
    private Paint pointPaint;
    private Bitmap icon;

    private float[] dst;

    private int pointCount = 4;
    private float range = 20;//点击点20像素范围内

    private Camera camera;
    private boolean increaseZ = true;
    private float zPosition = 0;
    private float degree = 0;
    private boolean needRotate = false;
    private int axis = -1;
    private float density = 0;

    public MatrixProbe(Context context) {
        this(context, null);
    }

    public MatrixProbe(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixProbe(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MatrixProbe(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
        icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        density = getResources().getDisplayMetrics().density;
        camera = new Camera();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLACK);
        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setStrokeWidth(35);
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

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawColor(Color.CYAN);
////        canvas.translate(width*1.0f/2, height*1.0f/2);
//        Log.e("zhwilson", "1:" + canvas.getMatrix().toString());
//        Log.e("zhwilson", "------------------------------------------------------");
//        Matrix matrix = new Matrix();
////        matrix.preTranslate(-720,-1196);
////        matrix.postTranslate(-720,-1196);
////        matrix.preRotate(90);
////        matrix.postRotate(90);
//        Log.e("zhwilson", "2:" + matrix.toString());
//        matrix.preTranslate(200, 200);
//        Log.e("zhwilson", "3:" + matrix.toString());
//        matrix.preRotate(45);
//        matrix.postTranslate(0, 200);
////        Log.e("zhwilson", "4:" + matrix.toString());
//        canvas.concat(matrix);
//        canvas.drawLine(0, -1000, 0, 1000, linePaint);
//        canvas.drawLine(-1000, 0, 1000, 0, linePaint);
//        canvas.drawRect(-200, -200, 200, 200, paint);
//        Log.e("zhwilson", "------------------------------------------------------");
//        Log.e("zhwilson", "5:" + canvas.getMatrix().toString());

//        //mapPoints
//        float[] origin = {0, 0};
//        float[] now = new float[2];
//        Log.e("zhwilson", "width:" + width + "-height:" + height);
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(width*1.0f/2, height*1.0f/2);
//        matrix.preRotate(45);
//        matrix.mapPoints(now, origin);
//        Log.e("zhwilson", "now:" + Arrays.toString(now));
//        matrix.postRotate(-45);
//        matrix.mapPoints(now, origin);
//        Log.e("zhwilson", "now:" + Arrays.toString(now));

//        //setPolyToPoly
//        Matrix matrix = new Matrix();
//        float[] origin = {-icon.getWidth()/2, -icon.getHeight()/2, icon.getWidth()/2, -icon.getHeight()/2, icon.getWidth()/2, icon.getHeight()/2, -icon.getWidth()/2, icon.getHeight()/2};
//        float[] src = {0, 0, icon.getWidth(), 0, icon.getWidth(), icon.getHeight(), 0, icon.getHeight()};
//
//        matrix.postTranslate(width*1.0f/2, height*1.0f/2);
//        if (dst == null){
//            dst = new float[origin.length];
//            matrix.mapPoints(dst, origin);
//        }
//        matrix.setPolyToPoly(src, 0, dst, 0, pointCount);
//        canvas.drawPoints(dst,0, pointCount*2, pointPaint);
//        canvas.drawBitmap(icon, matrix, paint);
//        postInvalidate();

        //setRectToRect
//        Matrix matrix = new Matrix();
//        RectF src = new RectF(0, 0, icon.getWidth(), icon.getHeight());
//        RectF dst = new RectF(0, 0, width, height/4);
//        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.FILL);
//        canvas.drawBitmap(icon, matrix, paint);

        //Camera
//        Camera camera = new Camera();
//        Log.e("zhwilson", "x:" + camera.getLocationX());
//        Log.e("zhwilson", "y:" + camera.getLocationY());
//        Log.e("zhwilson", "z:" + camera.getLocationZ());

//        if (!increaseZ) zPosition += 1;
//        Log.e("zhwilson", "zPosition:" + zPosition);
//        camera.translate(0, 0, increaseZ ? 1 : -1);
////        Matrix matrix = new Matrix();
////        camera.getMatrix(matrix);
////        canvas.drawBitmap(icon, matrix, paint);
//
//        camera.applyToCanvas(canvas);
//        canvas.drawBitmap(icon, 0, 0, paint);
//        postInvalidate();

//        if (camera == null) {
//            camera = new Camera();
//            camera.translate(width * 1.0f / 2, -height * 1.0f / 2, 0);
//        } else {
//            //旋转
////            camera.rotateX(5);
//            camera.rotateY(10);
////            camera.rotateZ(8);
//        }
//        Matrix matrix = new Matrix();
//        camera.getMatrix(matrix);
//        Log.e("zhwilson", "matrix:" + matrix.toString());
//        canvas.concat(matrix);
//        canvas.drawBitmap(icon, -icon.getWidth()/2, -icon.getHeight()/2, paint);
//        postInvalidate();

//        Camera camera1 = new Camera();
//        camera1.rotateZ(degree++);
//        degree = degree%360;
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(width * 1.0f / 2, height * 1.0f / 2);
//        camera1.applyToCanvas(canvas);
//        canvas.concat(matrix);
//        canvas.drawBitmap(icon, -icon.getWidth()/2, -icon.getHeight()/2, paint);
//        postInvalidate();

//        camera.rotateY(10);
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(width * 1.0f / 2, height * 1.0f / 2);
//        Log.e("zhwilson", "matrix1:" + matrix.toString());
//        camera.getMatrix(matrix);
//        matrix.preTranslate(-icon.getWidth()/2, -icon.getHeight()/2);
//        matrix.postTranslate(icon.getWidth()/2, icon.getHeight()/2);
//        Log.e("zhwilson", "matrix2:" + matrix.toString());
//        canvas.concat(matrix);
//        canvas.drawBitmap(icon, 0, 0, paint);
//        postInvalidate();
        Log.e("zhwilson", "ondraw");
        canvas.save();
        canvas.translate(width * 1.0f / 2, height * 1.0f / 2);
        canvas.drawLine(0, -1000, 0, 1000, linePaint);
        canvas.drawLine(-1000, 0, 1000, 0, linePaint);
        canvas.restore();

        Matrix matrix = new Matrix();
        if (needRotate) {
            camera.save();
            switch (axis) {
                case 1:
                    camera.rotateX(degree);
                    break;
                case 2:
                    camera.rotateY(degree);
                    break;
                case 3:
                    camera.rotateZ(degree);
                    break;
            }
            degree += 10;
            if (degree > 360) {
                degree = 0;
                needRotate = false;
            }
            camera.getMatrix(matrix);
            camera.restore();

            float[] values = new float[9];
            matrix.getValues(values);
            values[6] = values[6] / density;
            values[7] = values[7] / density;
            matrix.setValues(values);

            matrix.preTranslate(-icon.getWidth() / 2, -icon.getHeight() / 2);
            matrix.postTranslate(width * 1.0f / 2, height * 1.0f / 2);
        } else {
            camera.save();
            camera.translate((width - icon.getWidth()) / 2, -(height - icon.getHeight()) / 2, 0);
            camera.getMatrix(matrix);
            camera.restore();
        }
        canvas.drawBitmap(icon, matrix, paint);
        if (needRotate) postInvalidate();
    }

    public void setAxis(int axis) {
        this.axis = axis;
    }

    public void startRotate() {
        needRotate = true;
        degree = 0;
        postInvalidate();
    }

    public void setIncreaseZ(boolean increaseZ) {
        this.increaseZ = increaseZ;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getActionMasked()){
//            case MotionEvent.ACTION_DOWN:
//                ifChangePoint(event.getX(), event.getY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//            case  MotionEvent.ACTION_UP:
//                if (index != -1){
//                    dst[index] = event.getX();
//                    dst[index + 1] = event.getY();
//                }
//                break;
//        }
//        return true;
        return super.onTouchEvent(event);
    }

    private int index = -1;

    private void ifChangePoint(float x, float y) {//通过按下时的位置判断是不是想修改poly中点的位置
        index = -1;
        RectF rectF = new RectF(x - range, y - range, x + range, y + range);
        for (int i = 0; i < dst.length; i += 2) {
            if (rectF.contains(dst[i], dst[i + 1])) index = i;
        }
        Log.e("zhwilson", "index:" + index);
    }
}
