package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2017/12/7.
 * 圆形进度条
 */
public class CircleProgress extends View {
    private static int SUGGEST_MINI_CIRCLE_RADIUS = 50;//圆形进度条最小半径，单位dp
    private static int CIRCLE_STROKE_WIDTH = 5;//圆形进度条线宽，单位dp
    private static int CIRCLE_MARGIN = 10;//圆形进度条的外边距，单位dp

    private int width;//view的宽
    private int height;//view的高
    private int backColor = Color.parseColor("#fbfbfb");//背景颜色
    private int circleStrokeColor = Color.parseColor("#e8e8e8");//进度条背景圆圈颜色

    private float circleRadius = dp2px(SUGGEST_MINI_CIRCLE_RADIUS);//圆形进度条最小半径，单位px
    private float circleStrokeWidth = dp2px(CIRCLE_STROKE_WIDTH);//圆形进度条线宽，单位px
    private float circleMargin = dp2px(CIRCLE_MARGIN);//圆形进度条的外边距，单位px

    private Paint circleStrokeBackPaint;
    private Paint progressPaint;
    private int[] progressStrokeColors = {
            Color.parseColor("#f84004"),
            Color.parseColor("#a51fb2"),
            Color.parseColor("#03bcd0"),
            Color.parseColor("#f9a930"),
            Color.parseColor("#16f602")
    };
    private float progress = 0.0f;

    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        circleStrokeBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleStrokeBackPaint.setStrokeWidth(circleStrokeWidth);
        circleStrokeBackPaint.setColor(circleStrokeColor);
        circleStrokeBackPaint.setStyle(Paint.Style.STROKE);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStrokeWidth(circleStrokeWidth);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) (circleRadius * 2 + circleStrokeWidth * 2 + circleMargin * 2);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) (circleRadius * 2 + circleStrokeWidth * 2 + circleMargin * 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSpec(widthMeasureSpec, true), measureSpec(heightMeasureSpec, false));
    }

    private int measureSpec(int measureSpec, boolean isWidth) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = isWidth ? Math.max(size, getSuggestedMinimumWidth()) : Math.max(size, getSuggestedMinimumHeight());
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
        }
        return result;
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
        canvas.drawColor(backColor);
        canvas.translate(width * 1.0f / 2, height * 1.0f / 2);
        //画背景圆圈
        canvas.drawCircle(0, 0, circleRadius, circleStrokeBackPaint);
        //画进度
        drawProgress(canvas);
        postInvalidate();
    }

    private void drawProgress(Canvas canvas) {
        switch (((int) progress) / 10) {
            case 0:
            case 1:
                progressPaint.setColor(progressStrokeColors[0]);
                break;
            case 2:
            case 3:
                progressPaint.setColor(progressStrokeColors[1]);
                break;
            case 4:
            case 5:
                progressPaint.setColor(progressStrokeColors[2]);
                break;
            case 6:
            case 7:
                progressPaint.setColor(progressStrokeColors[3]);
                break;
            case 8:
            case 9:
            case 10:
                progressPaint.setColor(progressStrokeColors[4]);
                break;
        }
        canvas.drawArc(-circleRadius, -circleRadius, circleRadius, circleRadius, -90, 360 * (progress / 100), false, progressPaint);
    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return density * dp + 0.5f;
    }
}
