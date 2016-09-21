package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2016/9/21.
 * view中的颜色相关知识
 *
 * (RGB通道) 最终颜色 = 绘制的颜色 + (1 - 绘制颜色的透明度) × Canvas上的原有颜色
 */
public class ViewColor extends View{
    private Paint paint;
    //控件的宽度
    private int viewWidth;

    //控件的高度
    private int viewHeight;
    public ViewColor(Context context) {
        super(context);
        init();
    }

    public ViewColor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewColor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        /**
         * D指原本在Canvas上的内容dst，S指绘制输入的内容src，a指alpha通道，c指RGB各个通道
         */
        //ADD	Saturate(S + D)
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        //CLEAR	[0, 0]
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //SRC_IN	[Sa * Da, Sc * Da]
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //SRC_ATOP	[Da, Sc * Da + (1 - Sa) * Dc]
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        paint.setStyle(Paint.Style.FILL);//填充
        paint.setAntiAlias(true);//抗锯齿
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(viewWidth/2, viewHeight/2);

        paint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 100, paint);

        paint.setColor(Color.BLUE);
        Rect rect = new Rect(0, 0, 200, 150);
        canvas.drawRect(rect, paint);
    }
}
