package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2017/12/22.
 * Android的一个启动动画
 * 动画原型：https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513919705595&di=0ec7e830d2248d3674b0bc155ab02fd0&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201410%2F15%2F1820110xk636i3a3uaxrlk.gif
 */
public class AndroidStartAnim extends View {
    private static final int STROKE_WIDTH = 10;//线宽，单位dp
    private static final int RADIUS = 30;//文字中圆的半径，单位dp
    private static final int MARGIN = 10;//文字之间的距离，单位dp
    private static final int CONTENT_HEIGHT = 100;//文字的高度，单位dp
    private static final int MIN_HEIGHT = 200;//最小高度，单位dp

    private static final int CONTENT_COLOR = Color.WHITE;//文字内容颜色

    private float strokeWidth = dp2px(STROKE_WIDTH);//线宽，单位px
    private float radius = dp2px(RADIUS);//文字中圆的半径，单位px
    private float margin = dp2px(MARGIN);//文字之间的距离，单位px
    private float contentHeight = dp2px(CONTENT_HEIGHT);//文字的高度，单位px
    private float minHeight = dp2px(MIN_HEIGHT);//最小高度，单位px

    private int contentColor = CONTENT_COLOR;

    private int width;//view的宽
    private int height;//view的高

    private Paint contentPiant;

    public AndroidStartAnim(Context context) {
        this(context, null);
    }

    public AndroidStartAnim(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AndroidStartAnim(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        contentPiant = new Paint(Paint.ANTI_ALIAS_FLAG);
        contentPiant.setStrokeWidth(strokeWidth);
        contentPiant.setColor(contentColor);
        contentPiant.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) minHeight;
    }

    @Override
    protected int getSuggestedMinimumWidth() {//根据"android"这几个字母来确定宽度，"a","n","d","o"的宽度是一个圆的宽度，"r"的宽度是一个半圆，"i"的宽度就是线宽
        return (int) (radius * 11 + strokeWidth * 6.5f + margin * 8);
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
            result = isWidth ? (size < getSuggestedMinimumWidth() ? getSuggestedMinimumWidth() : size) : (size < getSuggestedMinimumHeight() ? getSuggestedMinimumHeight() : size);
        } else {
            result = isWidth ? Math.min(size, getSuggestedMinimumWidth()) : Math.min(size, getSuggestedMinimumHeight());
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        pathAndPosition();
    }

    private Path pathA = new Path();//"android"中a的path
    private Path pathN = new Path();//"android"中n的path
    private Path pathFirstD = new Path();//"android"中第一个d的path
    private Path pathR = new Path();//"android"中r的path
    private Path pathO = new Path();//"android"中o的path
    private Path pathI = new Path();//"android"中i的path
    private Path pathLastD = new Path();//"android"中最后一个d的path

    private void pathAndPosition() {
        pathA.moveTo(margin + radius * 2 + strokeWidth / 2, -(radius * 2 + strokeWidth));
        pathA.lineTo(margin + radius * 2 + strokeWidth / 2, -(radius + strokeWidth / 2));
        pathA.addCircle(margin + radius + strokeWidth / 2, -(radius + strokeWidth / 2), radius, Path.Direction.CW);
        pathA.lineTo(margin + radius * 2 + strokeWidth / 2, 0);

        pathN.moveTo(margin * 2 + radius * 2 + strokeWidth * 3 / 2, 0);
        pathN.lineTo(margin * 2 + radius * 2 + strokeWidth * 3 / 2, -(radius + strokeWidth / 2));
        pathN.addArc(margin * 2 + radius * 2 + strokeWidth * 3 / 2, -(radius * 2 + strokeWidth / 2), margin * 2 + radius * 4 + strokeWidth * 3 / 2, 0, 180, 180);
        pathN.lineTo(margin * 2 + radius * 4 + strokeWidth * 3 / 2, 0);

        pathFirstD.moveTo(margin * 3 + radius * 6 + strokeWidth * 5 / 2, -contentHeight);
        pathFirstD.lineTo(margin * 3 + radius * 6 + strokeWidth * 5 / 2, -(radius + strokeWidth / 2));
        pathFirstD.addCircle(margin * 3 + radius * 5 + strokeWidth * 5 / 2, -(radius + strokeWidth / 2), radius, Path.Direction.CW);
        pathFirstD.lineTo(margin * 3 + radius * 6 + strokeWidth * 5 / 2, 0);

        pathR.moveTo(margin * 4 + radius * 6 + strokeWidth * 7 / 2, 0);
        pathR.lineTo(margin * 4 + radius * 6 + strokeWidth * 7 / 2, -(radius + strokeWidth / 2));
        pathR.addArc(margin * 4 + radius * 6 + strokeWidth * 7 / 2, -(radius * 2 + strokeWidth / 2), margin * 4 + radius * 8 + strokeWidth * 7 / 2, 0, 180, 90);

        pathO.addCircle(margin * 5 + radius * 8 + strokeWidth * 4, -(radius + strokeWidth / 2), radius, Path.Direction.CW);

        pathI.moveTo(margin * 6 + radius * 9 + strokeWidth * 5, 0);
        pathI.lineTo(margin * 6 + radius * 9 + strokeWidth * 5, -(contentHeight - strokeWidth * 3 / 2));

        pathLastD.moveTo(margin * 7 + radius * 11 + strokeWidth * 6, -contentHeight);
        pathLastD.lineTo(margin * 7 + radius * 11 + strokeWidth * 6, -(radius + strokeWidth / 2));
        pathLastD.addCircle(margin * 7 + radius * 10 + strokeWidth * 6, -(radius + strokeWidth / 2), radius, Path.Direction.CW);
        pathLastD.lineTo(margin * 7 + radius * 11 + strokeWidth * 6, 0);

        initParams();
    }

    PathMeasure pathMeasureA;

    private void initParams() {
        pathMeasureA = new PathMeasure(pathA, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.translate(0, height * 1.0f / 2 + radius + strokeWidth / 2);
        drawContent(canvas);
        postInvalidate();
    }

    private void drawContent(Canvas canvas) {
        drawA(canvas);
        drawN(canvas);
        drawFirstD(canvas);
        drawR(canvas);
        drawO(canvas);
        drawI(canvas);
        drawLastD(canvas);
    }

    Path dstA = new Path();
    private float dstAStop = 0;

    private void drawA(Canvas canvas) {
        dstAStop += 5f;
        if (dstAStop > pathMeasureA.getLength()) {
            dstAStop = pathMeasureA.getLength();
        }
        pathMeasureA.getSegment(0, dstAStop, dstA, true);
        if (dstAStop == pathMeasureA.getLength()) {
            pathMeasureA.nextContour();
            dstAStop = 0;
        }
        canvas.drawPath(dstA, contentPiant);
    }

    private void drawN(Canvas canvas) {

        canvas.drawPath(pathN, contentPiant);
    }

    private void drawFirstD(Canvas canvas) {

        canvas.drawPath(pathFirstD, contentPiant);
    }

    private void drawR(Canvas canvas) {

        canvas.drawPath(pathR, contentPiant);
    }

    private void drawO(Canvas canvas) {

        canvas.drawPath(pathO, contentPiant);
    }

    private void drawI(Canvas canvas) {

        canvas.drawPath(pathI, contentPiant);
        canvas.drawPoint(margin * 6 + radius * 9 + strokeWidth * 5, -(contentHeight - strokeWidth / 2), contentPiant);
    }

    private void drawLastD(Canvas canvas) {

        canvas.drawPath(pathLastD, contentPiant);
    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return density * dp + 0.5f;
    }

    private float sp2px(float sp) {
        float scaleDensity = getResources().getDisplayMetrics().scaledDensity;
        return scaleDensity * sp;
    }
}
