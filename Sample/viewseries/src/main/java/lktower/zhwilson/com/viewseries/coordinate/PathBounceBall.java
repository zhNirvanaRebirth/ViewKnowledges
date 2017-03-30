package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2017/3/29.
 */
public class PathBounceBall extends View {
    private Paint ballPaint;
    private int width, height;
    private PointF pointF;
    private HDataPoint topPoint, bottomPoint;
    private VDataPoint leftPoint, rightPoint;
    private float radius;
    private float proportion = 0.5522f;
    private float offset;
    private int distortion = 1;
    private boolean leftScale = true;

    public PathBounceBall(Context context) {
        this(context, null);
    }

    public PathBounceBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathBounceBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initData();
    }

    private void initPaint() {
        ballPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ballPaint.setStyle(Paint.Style.FILL);
        ballPaint.setColor(Color.RED);
        ballPaint.setStrokeWidth(5.0f);
    }

    private void initData() {
        radius = 50f;
        offset = radius * proportion;
        pointF = new PointF(200f, 200f);
        locatePoints();
    }

    private void locatePoints() {
        topPoint = new HDataPoint();
        topPoint.x = pointF.x;
        topPoint.y = pointF.y - radius;
        topPoint.calculateControlPoint();
        bottomPoint = new HDataPoint();
        bottomPoint.x = pointF.x;
        bottomPoint.y = pointF.y + radius;
        bottomPoint.calculateControlPoint();

        leftPoint = new VDataPoint();
        leftPoint.x = pointF.x - radius;
        leftPoint.y = pointF.y;
        leftPoint.calculateControlPoint();
        rightPoint = new VDataPoint();
        rightPoint.x = pointF.x + radius;
        rightPoint.y = pointF.y;
        rightPoint.calculateControlPoint();
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
        canvas.drawColor(Color.BLACK);
        deformation();
        Path path = new Path();
        path.moveTo(topPoint.x, topPoint.y);
        path.cubicTo(topPoint.right.x, topPoint.right.y, rightPoint.top.x, rightPoint.top.y, rightPoint.x, rightPoint.y);
        path.cubicTo(rightPoint.bottom.x, rightPoint.bottom.y, bottomPoint.right.x, bottomPoint.right.y, bottomPoint.x, bottomPoint.y);
        path.cubicTo(bottomPoint.left.x, bottomPoint.left.y, leftPoint.bottom.x, leftPoint.bottom.y, leftPoint.x, leftPoint.y);
        path.cubicTo(leftPoint.top.x, leftPoint.top.y, topPoint.left.x, topPoint.left.y, topPoint.x, topPoint.y);
        canvas.drawPath(path, ballPaint);
        postInvalidate();
    }

    private void deformation() {
        if (leftScale)
            distortion++;
        else distortion--;
        if (distortion < 0) {
            leftPoint.x = pointF.x - radius + distortion;
            leftPoint.calculateControlPoint();
        } else if (distortion > 0) {
            rightPoint.x = pointF.x + radius + distortion;
            rightPoint.calculateControlPoint();
        } else {
            leftPoint.x = pointF.x - radius;
            rightPoint.calculateControlPoint();
            rightPoint.x = pointF.x + radius;
            rightPoint.calculateControlPoint();
        }
        if (distortion > 20)
            leftScale = false;
        if (distortion < -20)
            leftScale = true;
    }

    /**
     * 水平数据点
     */
    public class HDataPoint {
        public float x, y;
        public PointF left, right;

        public HDataPoint() {
            left = new PointF();
            right = new PointF();
        }

        public void calculateControlPoint() {
            left.x = x - offset;
            left.y = y;

            right.x = x + offset;
            right.y = y;
        }
    }

    /**
     * 竖直数据点
     */
    public class VDataPoint {
        public float x, y;
        public PointF top, bottom;

        public VDataPoint() {
            top = new PointF();
            bottom = new PointF();
        }

        public void calculateControlPoint() {
            top.x = x;
            top.y = y - offset;

            bottom.x = x;
            bottom.y = y + offset;
        }
    }
}
