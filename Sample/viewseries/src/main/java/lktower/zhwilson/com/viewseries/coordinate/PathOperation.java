package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2016/10/10.
 * path的基本操作
 */
public class PathOperation extends View {

    public PathOperation(Context context) {
        super(context);
    }

    public PathOperation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PathOperation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        /**
         * 绘制路径	drawPath	绘制路径，绘制贝塞尔曲线时也需要用到该函数
         */

        /**
         * 移动起点	moveTo	移动下一次操作的起点位置
         *
         * 连接直线	lineTo	添加上一个点到当前点之间的直线到Path
         *
         * 设置终点	setLastPoint	重置当前path中最后一个点位置，如果在绘制之前调用，效果和moveTo相同
         *
         * 闭合路径	close	连接第一个点连接到最后一个点，形成一个闭合区域
         */
        Paint pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setStrokeWidth(5);
        pathPaint.setColor(Color.BLACK);
        pathPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.lineTo(300, 300);
        path.lineTo(300, 0);
        canvas.drawPath(path, pathPaint);
    }
}
