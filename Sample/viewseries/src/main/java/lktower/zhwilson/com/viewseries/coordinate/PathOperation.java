package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
//        Path path = new Path();
//        path.lineTo(300, 300);
//        //moveTo和setLastPoint的区别，moveTo不会对之前的操作造成影响，setLastPoint会对之前的操作造成影响（具体表现为会改变调用setLastPoint之前path的最后一个点的位置， 同时使倒数第二个点与最后一个点的连线随最后一个点的位置的改变而改变， 而moveTo仅仅是改变下一次操作的起点）
//        path.moveTo(100, 200);
////        path.setLastPoint(100, 200);
//        path.lineTo(300, 0);
////        canvas.drawPath(path, pathPaint);
//        path.setLastPoint(400, 500);
//        path.lineTo(600, 600);
//        path.close();
//        canvas.drawPath(path, pathPaint);

        /**
         * path 绘制基本图形
         * 添加内容	addRect, addRoundRect, addOval, addCircle, addPath, addArc, arcTo	添加(矩形， 圆角矩形， 椭圆， 圆， 路径， 圆弧) 到当前Path (注意addArc和arcTo的区别)
         */

        Path path = new Path();
        /**
         * Path.Direction是一个枚举，在绘制图形的时候记录各个点的顺序。 Path.Direction.CCW 逆时针； Path.Direction.CW 顺时针
         * 比如，一个Rect（200， 200， 400， 400）；
         * 如果是顺时针添加，依次的点为（200， 200）， （400， 200）， （400， 400）， （200， 400）
         * 如果是逆时针添加，依次的点为（200， 200）， （200， 400）， （400， 400）， （400， 200）
         *
         * 可通过setLastPoint测试
         */

        path.addRect(200, 200, 400, 400, Path.Direction.CW);
////        path.addRect(200, 200, 400, 400, Path.Direction.CCW);
        path.setLastPoint(300, 300);
//        canvas.drawPath(path, pathPaint);

        //将两个path进行合并
//        Path srcPath = new Path();
//        srcPath.addCircle(400, 400, 200, Path.Direction.CW);

//        path.addPath(srcPath);
        //dx:在添加srcPaht之前将srcPath在x轴上进行的位移长度；dy:在添加srcPaht之前将srcPath在y轴上进行的位移长度
//        path.addPath(srcPath, 100, 100);
        //matrix:在添加srcPath之前将srcPath进行matrix变化
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(-100, -100);
//        path.addPath(srcPath, matrix);
//        canvas.drawPath(path, pathPaint);

        //向path添加圆弧：startAngle：开始角度； sweepAngle：扫过的角度
        //addArc:添加一个圆弧到path	直接添加一个圆弧到path中; arcTo:添加一个圆弧到path	添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
        RectF rectF = new RectF(200, 200, 600, 600);
//        path.addArc(rectF, 0, 100);
        //forceMoveTo:是否使用moveTo； true：将最后一个点移动到圆弧起点，即不连接最后一个点和圆弧起点； false：不移动，连接最后一个点与圆弧起点
        path.arcTo(rectF, 0, 180, true);
        canvas.drawPath(path, pathPaint);
    }
}
