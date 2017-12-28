package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2017/12/25.
 * Canvas相关操作
 */
public class CanvasProbe extends View {
    private int width;
    private int height;
    private Paint paint;

    public CanvasProbe(Context context) {
        this(context, null);
    }

    public CanvasProbe(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasProbe(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.translate(width * 1.0f / 2, height * 1.0f / 2);

//        canvas.drawLine(0, -height*1.0f/2, 0, height*1.0f/2, paint);
//        canvas.drawLine(-width*1.0f/2, 0, width*1.0f/2, 0, paint);
//
//        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        canvas.drawCircle(100, 100, 50, paint);
//
//        canvas.save();
//        canvas.scale(2, 2, 100, 0);
//        canvas.drawCircle(100, 100, 50, paint);
//        canvas.restore();

//        canvas.drawCircle(0, 0, 500, paint);
//        canvas.save();
//        for (int i = 0; i < 10; i++){
//            canvas.scale(0.9f, 0.9f);
//            paint.setStrokeWidth((float) (5/Math.pow(0.9f, i+1)));
//            canvas.drawCircle(0, 0, 500, paint);
//        }
//        canvas.restore();

//        canvas.save();
////        canvas.rotate(90);
//        canvas.rotate(90, 50, 100);
//        canvas.drawCircle(100, 100, 50, paint);
//        canvas.restore();

//        canvas.drawCircle(0, 0, 500, paint);
//        canvas.save();
//        canvas.scale(0.9f, 0.9f);
//        canvas.drawCircle(0, 0, 500, paint);
//        canvas.restore();
//
//        canvas.save();
//        float step = 10;
//        float degrees = 0;
//        while (degrees < 360){
//            canvas.rotate(step);
//            canvas.drawLine(500, 0, 500*0.9f, 0, paint);
//            degrees = degrees + step;
//        }
//        canvas.restore();

//        canvas.drawCircle(100, 100, 50, paint);
//        canvas.save();
//        canvas.skew(1, 0);
//        canvas.drawCircle(100, 100, 50, paint);
//        canvas.restore();

//        record();
//        PictureDrawable pictureDrawable = new PictureDrawable(picture);
//        pictureDrawable.setBounds(0, 0, (int) (picture.getWidth()*1.0f/2), picture.getHeight());
//        pictureDrawable.draw(canvas);
//        canvas.drawPicture(picture);
//        canvas.drawPicture(picture, new Rect(200, 0, 600, 400));


        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);

//        Matrix matrix = new Matrix();
//        matrix.postTranslate(-icon.getWidth()*1.0f/2, -icon.getHeight()*1.0f/2);
//        matrix.postRotate(90);
//        canvas.save();
//        canvas.concat(matrix);
//        canvas.drawBitmap(icon, new Matrix(), new Paint());
//        canvas.restore();
        Rect src = new Rect(0, 0, (int) (icon.getWidth() * 1.0f / 2), (int) (icon.getHeight() * 1.0f / 2));
        RectF dst = new RectF(100, 100, icon.getWidth() + 100, icon.getHeight() + 100);
        canvas.drawBitmap(icon, src, dst, paint);
    }

//    Picture picture = new Picture();
//    private void record(){
//        Canvas canvas = picture.beginRecording(200, 200);
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.BLUE);
//        canvas.translate(100, 100);
//        canvas.drawCircle(0, 0, 100, paint);
//        picture.endRecording();
//    }
}
