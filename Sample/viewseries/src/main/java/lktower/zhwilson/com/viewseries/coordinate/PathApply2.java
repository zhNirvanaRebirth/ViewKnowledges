package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2017/1/11.
 */
public class PathApply2 extends View {
    private int width, height;

    private float offsetRadio = 0.5322f;

    private float offset;

    private float tailLength;

    private float cubicRadius;

    private RowCubicPoint point1 = new RowCubicPoint();
    private ColCubicPoint point2 = new ColCubicPoint();
    private RowCubicPoint point3 = new RowCubicPoint();
    private ColCubicPoint point4 = new ColCubicPoint();

    private Path cubicPath;
    private Paint cubicPaint;

    private Path animPath;

    private PathMeasure pathMeasure;
    private Matrix matrix;

    private float currentPos;

    public PathApply2(Context context) {
        this(context, null);
    }

    public PathApply2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathApply2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cubicRadius = dp2px(1);
        tailLength = dp2px(10);
        offset = cubicRadius * offsetRadio;
        calculateCubicPoint();

        cubicPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cubicPaint.setStyle(Paint.Style.FILL);
        cubicPaint.setStrokeWidth(1);
        cubicPaint.setColor(Color.BLACK);
        animPath = new Path();
        initCubicPath();
        initPathMeasure();
        matrix = new Matrix();
    }

    private void calculateCubicPoint() {
        point1.x = 0;
        point1.y = cubicRadius;
        point1.calculate();

        point2.x = cubicRadius;
        point2.y = 0;
        point2.calculate();

        point3.x = 0;
        point3.y = -cubicRadius;
        point3.calculate();

        point4.x = -(cubicRadius + tailLength);
        point4.y = 0;
        point4.calculate();
    }

    private void initCubicPath() {
        cubicPath = new Path();
        cubicPath.moveTo(point1.x, point1.y);
        cubicPath.cubicTo(point1.right.x, point1.right.y, point2.bottom.x, point2.bottom.y, point2.x, point2.y);
        cubicPath.cubicTo(point2.top.x, point2.top.y, point3.right.x, point3.right.y, point3.x, point3.y);
        cubicPath.cubicTo(point3.left.x, point3.left.y, point4.top.x, point4.top.y, point4.x, point4.y);
        cubicPath.cubicTo(point4.bottom.x, point4.bottom.y, point1.left.x, point1.left.y, point1.x, point1.y);
    }

    private void initPathMeasure() {
        Path path = makeConvexArrow(100, 400);
//        path.addCircle(0, 0, 200, Path.Direction.CW);
        pathMeasure = new PathMeasure(path, true);
    }

    private Path makeConvexArrow(float length, float height) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(length / 4f, 0.0f);
        path.lineTo(length, height / 2.0f);
        path.lineTo(length / 4f, height);
        path.lineTo(0.0f, height);
        path.lineTo(length * 3f / 4f, height / 2f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        return path;
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
        animPath.reset();
        currentPos += 0.005;
        if (currentPos > 1)
            currentPos = 0;
        pathMeasure.getMatrix(pathMeasure.getLength() * currentPos, matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);

        animPath.addPath(cubicPath, matrix);
        canvas.drawPath(animPath, cubicPaint);
        postInvalidate();
    }

    public class RowCubicPoint {
        public float x, y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public void calculate() {
            left.x = x - offset;
            left.y = y;
            right.x = x + offset;
            right.y = y;
        }
    }

    public class ColCubicPoint {
        public float x, y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public void calculate() {
            top.x = x;
            top.y = y - offset;
            bottom.x = x;
            bottom.y = y + offset;
        }
    }

    private float dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return scale * dp + 0.5f;
    }
}
