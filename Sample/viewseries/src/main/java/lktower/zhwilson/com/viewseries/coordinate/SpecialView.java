package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhwilson on 2018/1/23.
 * 特殊控件的点击处理
 */
public class SpecialView extends View {
    private static final int MIN_RADIUS = 120;
    private static final int MIDDLE_RADIUS = 150;
    private static final int MAX_RADIUS = 280;
    private Region upRegion;
    private Path upPath;
    private Region downRegion;
    private Path downPath;
    private Region leftRegion;
    private Path leftPath;
    private Region rightRegion;
    private Path rightPath;
    private Region okRegion;
    private Path okPath;

    private Paint arcPaint;
    private Paint linePaint;
    private Paint pointPaint;
    private Paint pathPaint;

    private int width;
    private int height;
    private float offsetWidth;
    private float offsetHeight;

    private float pointX = -1;
    private float pointY = -1;
    private int index = -1;

    public SpecialView(Context context) {
        this(context, null);
    }

    public SpecialView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpecialView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SpecialView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    private void initPaint() {
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.RED);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStrokeWidth(20);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
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
        offsetWidth = width * 1.0f / 2;
        offsetHeight = height * 1.0f / 2;
        regionLocate();
    }

    private void regionLocate() {
        Region region = new Region(0, 0, width, height);
        RectF bigRectF = new RectF(offsetWidth - MAX_RADIUS, offsetHeight - MAX_RADIUS, offsetWidth + MAX_RADIUS, offsetHeight + MAX_RADIUS);
        RectF smallRectF = new RectF(offsetWidth - MIDDLE_RADIUS, offsetHeight - MIDDLE_RADIUS, offsetWidth + MIDDLE_RADIUS, offsetHeight + MIDDLE_RADIUS);
        okPath = new Path();
        okRegion = new Region();
        okPath.addCircle(offsetWidth, offsetHeight, MIN_RADIUS, Path.Direction.CW);
        okRegion.setPath(okPath, region);

        upPath = new Path();
        upRegion = new Region();
        upPath.addArc(bigRectF, -50, -80);
        upPath.arcTo(smallRectF, -130, 80, false);
        upPath.close();
        upRegion.setPath(upPath, region);

        rightPath = new Path();
        rightRegion = new Region();
        rightPath.addArc(bigRectF, -40, 80);
        rightPath.arcTo(smallRectF, 40, -80, false);
        rightPath.close();
        rightRegion.setPath(rightPath, region);

        downPath = new Path();
        downRegion = new Region();
        downPath.addArc(bigRectF, 50, 80);
        downPath.arcTo(smallRectF, 130, -80, false);
        downPath.close();
        downRegion.setPath(downPath, region);

        leftPath = new Path();
        leftRegion = new Region();
        leftPath.addArc(bigRectF, 140, 80);
        leftPath.arcTo(smallRectF, 220, -80, false);
        leftPath.close();
        leftRegion.setPath(leftPath, region);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
//        drawArc(canvas);
        drawAxis(canvas);
        drawBtn(canvas, index);
        drawPoint(canvas);
    }

    private void drawBtn(Canvas canvas, int p) {
        canvas.save();
        pathPaint.setColor(p == 1 ? Color.RED : Color.GRAY);
        canvas.drawPath(upPath, pathPaint);
        pathPaint.setColor(p == 2 ? Color.RED : Color.GRAY);
        canvas.drawPath(rightPath, pathPaint);
        pathPaint.setColor(p == 3 ? Color.RED : Color.GRAY);
        canvas.drawPath(downPath, pathPaint);
        pathPaint.setColor(p == 4 ? Color.RED : Color.GRAY);
        canvas.drawPath(leftPath, pathPaint);
        pathPaint.setColor(p == 0 ? Color.RED : Color.GRAY);
        canvas.drawPath(okPath, pathPaint);
        canvas.restore();
    }

    private void drawPoint(Canvas canvas) {
        if (pointX == -1 || pointY == -1) return;
        canvas.save();
        canvas.drawPoint(pointX, pointY, pointPaint);
        canvas.restore();
    }

    private void drawAxis(Canvas canvas) {
        canvas.save();
        canvas.translate(offsetWidth, offsetHeight);
        canvas.drawLine(0, -offsetHeight, 0, offsetHeight, linePaint);
        canvas.drawLine(-offsetWidth, 0, offsetWidth, 0, linePaint);
        canvas.restore();
    }

    private void drawArc(Canvas canvas) {
        int saveCount = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        arcPaint.setColor(Color.RED);
        canvas.drawArc(100, 100, 500, 500, -45f, 90, true, arcPaint);
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        arcPaint.setXfermode(xfermode);
        arcPaint.setColor(Color.GREEN);
        canvas.drawArc(0, 0, 600, 600, -45f, 90, true, arcPaint);
        arcPaint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pointX = event.getX();
                pointY = event.getY();
                index = calIndex((int) pointX, (int) pointY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                pointX = event.getX();
                pointY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                pointX = -1;
                pointY = -1;
                index = -1;
                invalidate();
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }

    private int calIndex(int x, int y) {
        int index = -1;
        if (okRegion.contains(x, y)) {
            index = 0;
        } else if (upRegion.contains(x, y)) {
            index = 1;
        } else if (rightRegion.contains(x, y)) {
            index = 2;
        } else if (downRegion.contains(x, y)) {
            index = 3;
        } else if (leftRegion.contains(x, y)) {
            index = 4;
        }
        return index;
    }
}
