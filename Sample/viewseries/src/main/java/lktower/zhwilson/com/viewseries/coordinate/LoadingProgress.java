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
 * Created by zhwilson on 2017/12/28.
 * 仿写一个加载进度条
 */
public class LoadingProgress extends View {
    private static final int RADIUS = 20;//进度条内部圆角半径，单位：dp
    private static final int MARGIN = 3;//进度条与外部圆角进度条Rect的margin，单位：dp
    private static final int CIRCLE_STROKE_WIDTH = 2;//圆圈线宽，单位：dp
    private static final int OUT_RECT_COLOR = Color.parseColor("#fce49b");//外部进度条Rect颜色
    private static final int PROGRESS_RECT_COLOR = Color.parseColor("#ffa800");//进度条颜色
    private static final int ROUND_COLOR = Color.parseColor("#fbcd4e");//背景圆的颜色
    private static final int STROKE_COLOR = Color.parseColor("#ffffff");//背景圆圈的边框颜色

    private float radius = dp2px(RADIUS);//进度条内部圆角半径，单位：px
    private float margin = dp2px(MARGIN);//进度条与外部圆角进度条Rect的margin，单位：px
    private float circleStrokeWidth = dp2px(CIRCLE_STROKE_WIDTH);//圆圈线宽，单位：px
    private int outRectColor = OUT_RECT_COLOR;
    private int progressRectColor = PROGRESS_RECT_COLOR;
    private int roundColor = ROUND_COLOR;
    private int strokeColor = STROKE_COLOR;

    private int width;//view宽度
    private int height;//view高度

    private Paint outRectPaint;//外部圆角进度条Paint
    private Paint circlePaint;//圆圈Paint
    private Paint roundPaint;//圆形Paint
    private Paint progressPaint;//进度条Paint

    private Bitmap fans;
    Matrix fansMatrix = new Matrix();

    private float progress = 98;//当前的进度
    private PictureDrawable progressPictureDrawable;

    public LoadingProgress(Context context) {
        this(context, null);
    }

    public LoadingProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LoadingProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
        fans = BitmapFactory.decodeResource(getResources(), R.drawable.fans);
    }

    private void initPaint() {
        outRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outRectPaint.setColor(outRectColor);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(strokeColor);
        circlePaint.setStrokeWidth(circleStrokeWidth);
        circlePaint.setStyle(Paint.Style.STROKE);

        roundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        roundPaint.setColor(roundColor);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressRectColor);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) (radius * 2 + margin * 2);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) (radius * 2 + margin * 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = isWidth ? Math.max(getSuggestedMinimumWidth(), size) : Math.max(getSuggestedMinimumHeight(), size);
        } else {
            result = isWidth ? Math.max(getSuggestedMinimumWidth(), size) : Math.min(getSuggestedMinimumHeight(), size);
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        initProgressPicture();
    }

    private void initProgressPicture() {
        Picture picture = new Picture();
        Canvas canvas = picture.beginRecording(width, (int) (radius * 2));
        canvas.drawRoundRect(margin, margin, width - margin, radius * 2 - margin, radius, radius, progressPaint);
        picture.endRecording();
        progressPictureDrawable = new PictureDrawable(picture);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.translate(0, height * 1.0f / 2 - radius - margin);
        //绘制外部进度条rect
        canvas.drawRoundRect(0, 0, width, (radius * 2 + margin * 2), radius + margin, radius + margin, outRectPaint);
        //绘制进度条
        drawProgress(canvas);
        //绘制进度条右侧的圆圈
        canvas.drawCircle(width - radius - margin, radius + margin, radius + margin - circleStrokeWidth / 2, circlePaint);
        //绘制进度条右侧的圆
        canvas.drawCircle(width - radius - margin, radius + margin, radius + margin - circleStrokeWidth, roundPaint);
        //绘制圆圈内的扇叶
        drawFans(canvas);

        postInvalidate();
    }

    private void drawFans(Canvas canvas) {
        canvas.save();
        canvas.translate(width - radius - margin, margin + radius);
        fansMatrix.postRotate(10);
        canvas.concat(fansMatrix);
        canvas.drawBitmap(fans, new Rect(0, 0, fans.getWidth(), fans.getHeight()), new RectF(-radius, -radius, radius, radius), new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.restore();
    }

    private void drawProgress(Canvas canvas) {
        float current = (width - margin * 2) * progress / 100;//当前需要画的进度条长度
        canvas.save();
        canvas.translate(0, margin);
        progressPictureDrawable.setBounds(0, 0, (int) current, (int) (radius * 2));
        progressPictureDrawable.draw(canvas);
        canvas.restore();
    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return density * dp + 0.5f;
    }
}
