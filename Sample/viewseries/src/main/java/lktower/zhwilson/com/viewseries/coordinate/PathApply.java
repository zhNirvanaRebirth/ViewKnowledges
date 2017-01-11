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
 * <p>
 * 关于path的应用，实现一个简单的动画
 */
public class PathApply extends View {
    //控件的宽高
    private int width, height;
    //贝塞尔圆半径
    private float cubicCircleRadiu;
    //贝塞尔圆圆心
    private PointF cubicCirclePoivt;
    //数据点与控制点的偏移量
    private float offsetRadio = 0.552f;
    private float offset;
    //旋转角度
    private double degree;
    //尾部的长度
    private float tailLength;
    //贝塞尔中的点
    private RowPathPoint point1 = new RowPathPoint();
    private RowPathPoint point3 = new RowPathPoint();
    private CosPathPoint point2 = new CosPathPoint();
    private CosPathPoint point4 = new CosPathPoint();


    private Path cubicPath;
    private Paint cubicPaint;
    private Paint dataPaint;
    private Paint controlPaint;

    //确定动画的路径
    private PathMeasure pathMeasure;

    private float currentPos = 0.0f;

    private Matrix matrix;

    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public PathApply(Context context) {
        this(context, null);
    }

    public PathApply(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathApply(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cubicCircleRadiu = dp2px(1.0f);
        cubicCirclePoivt = new PointF();
        offset = cubicCircleRadiu * offsetRadio;
        tailLength = dp2px(10);
        //确定贝塞尔相关点的位置
        cubicPointCalculate();

        cubicPath = new Path();
        cubicPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cubicPaint.setColor(Color.BLACK);
        cubicPaint.setStyle(Paint.Style.FILL);
        cubicPaint.setStrokeWidth(2);

        dataPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dataPaint.setColor(Color.GRAY);
        dataPaint.setStyle(Paint.Style.FILL);
        dataPaint.setStrokeWidth(5);

        controlPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        controlPaint.setColor(Color.RED);
        controlPaint.setStyle(Paint.Style.FILL);
        controlPaint.setStrokeWidth(5);

        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        pathMeasure = new PathMeasure(path, false);
        matrix = new Matrix();
    }

    private void cubicPointCalculate() {
        //数据点1
        point1.x = (float) (-cubicCircleRadiu * Math.sin(degree - Math.PI));
        point1.y = (float) (cubicCircleRadiu * Math.cos(degree - Math.PI));
        point1.cubicControlPointCalculate(1);

        //数据点2
        point2.x = (float) ((tailLength + cubicCircleRadiu) * Math.cos(degree - Math.PI));
        point2.y = (float) ((tailLength + cubicCircleRadiu) * Math.sin(degree - Math.PI));
        point2.cubicControlPointCalculate(2);

        //数据点3
        point3.x = (float) (cubicCircleRadiu * Math.sin(degree - Math.PI));
        point3.y = (float) (-cubicCircleRadiu * Math.cos(degree - Math.PI));
        point3.cubicControlPointCalculate(3);

        //数据点4
        point4.x = (float) (-cubicCircleRadiu * Math.cos(degree - Math.PI));
        point4.y = (float) (-cubicCircleRadiu * Math.sin(degree - Math.PI));
        point4.cubicControlPointCalculate(4);
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
        cubicPath.reset();
        canvas.translate(width / 2, height / 2);

        currentPos += 0.005;
        if (currentPos > 1)
            currentPos = 0;
        pathMeasure.getPosTan(pathMeasure.getLength() * currentPos, pos, tan);
        matrix.reset();
        degree = Math.atan2(tan[1], tan[0]);
        cubicPointCalculate();

//        canvas.drawPoint(point1.x, point1.y, dataPaint);
//        canvas.drawPoint(point2.x, point2.y, dataPaint);
//        canvas.drawPoint(point3.x, point3.y, dataPaint);
//        canvas.drawPoint(point4.x, point4.y, dataPaint);
//
//        canvas.drawPoint(point1.left.x, point1.left.y, controlPaint);
//        canvas.drawPoint(point2.top.x, point2.top.y, controlPaint);
//        canvas.drawPoint(point3.left.x, point3.left.y, controlPaint);
//        canvas.drawPoint(point4.top.x, point4.top.y, controlPaint);
//        canvas.drawPoint(point1.right.x, point1.right.y, controlPaint);
//        canvas.drawPoint(point2.bottom.x, point2.bottom.y, controlPaint);
//        canvas.drawPoint(point3.right.x, point3.right.y, controlPaint);
//        canvas.drawPoint(point4.bottom.x, point4.bottom.y, controlPaint);

        cubicPath.moveTo(point1.x, point1.y);
        cubicPath.cubicTo(point1.right.x, point1.right.y, point2.bottom.x, point2.bottom.y, point2.x, point2.y);
        cubicPath.cubicTo(point2.top.x, point2.top.y, point3.right.x, point3.right.y, point3.x, point3.y);
        cubicPath.cubicTo(point3.left.x, point3.left.y, point4.top.x, point4.top.y, point4.x, point4.y);
        cubicPath.cubicTo(point4.bottom.x, point4.bottom.y, point1.left.x, point1.left.y, point1.x, point1.y);

        matrix.postTranslate(pos[0], pos[1]);
        cubicPath.transform(matrix);

        canvas.drawPath(cubicPath, cubicPaint);
        postInvalidate();
    }

    /**
     * 这是绘制贝塞尔曲线时，水平方向数据点及其控制点的信息存储类
     */
    public class RowPathPoint {
        //左右两个控制点的坐标
        public PointF left = new PointF();
        public PointF right = new PointF();
        //数据点坐标
        public float x, y;

        //得到两边的数据点坐标
        public void cubicControlPointCalculate(int point) {
            if (point == 1) {
                left.x = (float) (x - offset * Math.cos(degree - Math.PI));
                left.y = (float) (y - offset * Math.sin(degree - Math.PI));
                right.x = (float) (x + offset * Math.cos(degree - Math.PI));
                right.y = (float) (y + offset * Math.sin(degree - Math.PI));
            } else {
                left.x = (float) (x - offset * Math.cos(degree - Math.PI));
                left.y = (float) (y - offset * Math.sin(degree - Math.PI));
                right.x = (float) (x + offset * Math.cos(degree - Math.PI));
                right.y = (float) (y + offset * Math.sin(degree - Math.PI));
            }
        }
    }

    /**
     * 这是绘制贝塞尔曲线时，竖直方向数据点及其控制点的信息存储类
     */
    public class CosPathPoint {
        //左右两个控制点的坐标
        public PointF top = new PointF();
        public PointF bottom = new PointF();
        //数据点坐标
        public float x, y;

        //得到上下的数据点坐标
        public void cubicControlPointCalculate(int point) {
            if (point == 2) {
                top.x = (float) (x + offset * Math.sin(degree - Math.PI));
                top.y = (float) (y - offset * Math.cos(degree - Math.PI));
                bottom.x = (float) (x - offset * Math.sin(degree - Math.PI));
                bottom.y = (float) (y + offset * Math.cos(degree - Math.PI));
            } else {
                top.x = (float) (x + offset * Math.sin(degree - Math.PI));
                top.y = (float) (y - offset * Math.cos(degree - Math.PI));
                bottom.x = (float) (x - offset * Math.sin(degree - Math.PI));
                bottom.y = (float) (y + offset * Math.cos(degree - Math.PI));
            }
        }
    }


    private float dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return scale * dp + 0.5f;
    }
}
