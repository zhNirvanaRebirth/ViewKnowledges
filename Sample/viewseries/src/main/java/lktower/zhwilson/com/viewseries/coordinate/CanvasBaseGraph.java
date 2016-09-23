package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2016/9/22.
 *
 * canvas基本图形的绘制
 */
public class CanvasBaseGraph extends View{
    public CanvasBaseGraph(Context context) {
        super(context);
    }

    public CanvasBaseGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasBaseGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //view大小的测量
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //view大小确定
    }

    /**
     * 内容的绘制e
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 绘制颜色	drawColor, drawRGB, drawARGB	使用单一颜色填充整个画布
         */
//        canvas.drawColor(Color.YELLOW);//黄色
//        canvas.drawRGB(255, 0, 0);//红色
        canvas.drawARGB(127, 0, 255, 0);//半透明，绿色(看不出效果，因为没有对照底色)

        /**
         * 绘制基本形状	drawPoint, drawPoints, drawLine, drawLines, drawRect, drawRoundRect, drawOval, drawCircle, drawArc	依次为 点、线、矩形、圆角矩形、椭圆、圆、圆弧
         */
//        Paint pointPaint = new Paint();
//        pointPaint.setColor(Color.RED);
//        pointPaint.setStrokeWidth(10);
//        pointPaint.setAntiAlias(true);//去毛刺
//        canvas.drawPoint(100, 100, pointPaint);
//        canvas.drawPoints(new float[]{200, 200, 500, 500}, pointPaint);
//        canvas.drawPoints(new float[]{150, 150, 250, 250, 400, 400, 560, 560}, pointPaint);
        //offset 表示忽略几个点  count：从忽略的点开始画点，但是一个点需要两个值，所以count=1：不会画点，count=2：画一个点，count=3：画一个点
//        canvas.drawPoints(new float[]{150, 150, 250, 250, 400, 400, 560, 560}, 1, 4, pointPaint);

//        Paint linePaint = new Paint();
//        linePaint.setStrokeWidth(5);
//        linePaint.setColor(Color.BLACK);
//        canvas.drawLine(200, 200, 600, 200, linePaint);
//        canvas.drawLines(new float[]{150, 150, 452, 452, 200, 600, 600, 600}, linePaint);
//        canvas.drawLines(new float[]{150, 150, 452, 150, 150, 250, 452, 250, 100, 700, 600, 700}, 2, 4, linePaint);

//        Paint rectPaint = new Paint();
//        rectPaint.setStrokeWidth(5);
//        rectPaint.setStyle(Paint.Style.FILL);
//        rectPaint.setStyle(Paint.Style.STROKE);
//        rectPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        rectPaint.setColor(Color.WHITE);
//        rectPaint.setAntiAlias(true);
//        canvas.drawRect(200, 200, 600, 600, rectPaint);
//        Rect rect = new Rect(200, 200, 600, 600);
//        canvas.drawRect(rect, rectPaint);
//        RectF rectF = new RectF(200, 200, 600, 600);
//        canvas.drawRect(rectF, rectPaint);
        //rx:x轴的圆角半径 ry：y轴上的圆角半径（API：21）
//        canvas.drawRoundRect(200, 200, 600, 600, 20, 20, rectPaint);
//        RectF rectF = new RectF(200, 200, 600, 600);
//        canvas.drawRoundRect(rectF, 200, 200, rectPaint);

//        Paint ovalPaint = new Paint();
//        ovalPaint.setStrokeWidth(5);
//        ovalPaint.setColor(Color.RED);
//        ovalPaint.setStyle(Paint.Style.FILL);
//        ovalPaint.setStyle(Paint.Style.STROKE);
//        ovalPaint.setAntiAlias(true);
//        canvas.drawOval(200, 200, 600, 600, ovalPaint);
//        RectF rectF = new RectF(200, 200, 600, 400);
//        canvas.drawOval(rectF, ovalPaint);

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.CYAN);
        circlePaint.setStrokeWidth(5);
        circlePaint.setAntiAlias(true);
//        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(400, 400, 200, circlePaint);

//        Paint arcPaint = new Paint();
//        arcPaint.setColor(Color.YELLOW);
//        arcPaint.setStrokeWidth(5);
//        arcPaint.setStyle(Paint.Style.STROKE);
//        arcPaint.setStyle(Paint.Style.FILL);
        //startAngle:开始角度  sweepAngle：扫过的角度（不是终止角度）userCenter：是否包含中心点  api:21
//        canvas.drawArc(200, 200, 600, 600, 0, 90, false, arcPaint);
//        RectF rectF = new RectF(200, 200, 600, 600);
//        canvas.drawArc(rectF, -90, 90, false, arcPaint);
    }
}
