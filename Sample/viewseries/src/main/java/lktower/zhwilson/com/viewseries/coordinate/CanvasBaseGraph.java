package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2016/9/22.
 *
 * canvas基本图形的绘制
 */
public class CanvasBaseGraph extends View{
    private Context context;
    private int width;//控件的宽度
    private int height;//控件的高度
    public CanvasBaseGraph(Context context) {
        super(context);
        this.context = context;
    }

    public CanvasBaseGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CanvasBaseGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
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
        width = w;
        height = h;
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

//        Paint circlePaint = new Paint();
//        circlePaint.setColor(Color.CYAN);
//        circlePaint.setStrokeWidth(5);
//        circlePaint.setAntiAlias(true);
//        circlePaint.setStyle(Paint.Style.STROKE);
//        circlePaint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(400, 400, 200, circlePaint);

//        Paint arcPaint = new Paint();
//        arcPaint.setColor(Color.YELLOW);
//        arcPaint.setStrokeWidth(5);
//        arcPaint.setStyle(Paint.Style.STROKE);
//        arcPaint.setStyle(Paint.Style.FILL);
        //startAngle:开始角度  sweepAngle：扫过的角度（不是终止角度）userCenter：是否包含中心点  api:21
//        canvas.drawArc(200, 200, 600, 600, 0, 90, false, arcPaint);
//        RectF rectF = new RectF(200, 200, 600, 600);
//        canvas.drawArc(rectF, -90, 90, false, arcPaint);

        //demo
//        Paint dPaint = new Paint();
//        dPaint.setAntiAlias(true);
//        dPaint.setStyle(Paint.Style.FILL);
//
//        RectF rectF = new RectF(200, 200, 600, 600);
//        dPaint.setColor(Color.BLACK);
//        canvas.drawArc(rectF, -90, 200, true, dPaint);
//        dPaint.setColor(Color.WHITE);
//        canvas.drawArc(rectF, 100, 90, true, dPaint);
//        dPaint.setColor(Color.RED);
//        canvas.drawArc(rectF, 190, 50, true, dPaint);
//        dPaint.setColor(Color.GRAY);
//        canvas.drawArc(rectF, 240, 30, true, dPaint);

        /**
         * 绘制图片	drawBitmap, drawPicture	绘制位图和图片
         */
        //绘制矢量图picture
        //使用Picture前请关闭硬件加速，以免引起不必要的问题！
        //Picture是将一些canvas操作存储在picture中， 录制的内容不会直接显示在屏幕上，只是存储而且
//        Picture mp = new Picture();
//        Canvas pCanvas = mp.beginRecording(width, height);//开始录制
//        Paint picPaint = new Paint();
//        picPaint.setColor(Color.BLACK);
//        picPaint.setStrokeWidth(5);
//        picPaint.setAntiAlias(true);
//        pCanvas.drawCircle(width/2, height/2, width > height ? height/4 : width/4, picPaint);
//        mp.endRecording();//结束录制
        //Picture内容绘制方法
        //1.picture.draw
        //2.canvas.drawPicture
        // 3.将picture封装成PictureDrawable， 使用PictureDrawable的draw方法

//        mp.draw(canvas);//会对canvas的状态造成影响
//        canvas.drawPicture(mp);//直接绘制picture内容
//        canvas.drawPicture(mp, new Rect(0, 0, mp.getWidth(), 200));//在指定矩形框中绘制picture的内容，如果矩形框的高度（宽度）小于picture内容的高度（宽度），则进行相应的压缩，反之放大
//        PictureDrawable drawable = new PictureDrawable(mp);
//        drawable.setBounds(0, 0, mp.getWidth()/2, mp.getHeight()/2);//绘制区域，必须设置，否则没有内容绘制，只绘制相关区域内容，不会对内容进行缩放
//        drawable.draw(canvas);

        //绘制位图（Bitmap）
        //bitmap的获取方式
        //1、创建Bitmap
        //2、通过BitmapDrawable获取（网络图片，资源文件，内存图片）
        //3、通过BitmapFactory获取（网络图片，资源文件，内存图片）
        //BitmapFactory获取Bitmap
        //1.获取资源文件图片
        //drawable/raw
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        //assets
//        Bitmap bitmap = null;
//        try {
//            InputStream inputStream = context.getAssets().open("icon.png");
//            bitmap = BitmapFactory.decodeStream(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (bitmap == null)
//            return;
        //网络图片，也是通过流的方式
//        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
//        canvas.drawBitmap(bitmap, 200, 400, new Paint());
        //Rect src:指定绘制图片的区域(就是从图片上截取要绘制的区域)；Rect dst：指定在屏幕上显示（绘制）的区域（将从图片上截取的区域绘制在屏幕指定的区域），可能会造成图片截取区域缩放
//        Rect src = new Rect(bitmap.getWidth()/4, bitmap.getHeight()/4, bitmap.getWidth()*3/4, bitmap.getHeight()*3/4);
//        Rect dst = new Rect(0, 0, width/2, height/2);
//        canvas.drawBitmap(bitmap, src, dst, new Paint());

        //图片绘制demo
//        Bitmap bitmap = null;
//        try {
//            InputStream inputStream = context.getAssets().open("zombies.jpg");
//            bitmap = BitmapFactory.decodeStream(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (bitmap == null)
//            return;
//        int srcLeft = 0;
//        int srcTop = 0;
//        int bw = bitmap.getWidth();
//        int bh = bitmap.getHeight();
//        Paint bp = new Paint();
//        Rect dst = new Rect(-bw/22, -bh/4, bw/22, bh/4);
//        canvas.translate(width/2, height/2);
//        while (true){
//            Rect src = new Rect(srcLeft, srcTop, srcLeft + bw/11, srcTop + bh/2);
//            canvas.drawBitmap(bitmap, src, dst, bp);
//            srcLeft += bw/11;
//            if (srcLeft == bw && srcTop == 0){
//                srcTop += bh/2;
//                srcLeft = 0;
//            } else {
//                srcTop = 0;
//                srcLeft = 0;
//            }
//        }

        /**
         * 绘制文本	drawText, drawPosText, drawTextOnPath	依次为 绘制文字、绘制文字时指定每个文字位置、根据路径绘制文字
         */
        String text = "尼大业";
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(sp2px(16));
        textPaint.setColor(Color.BLACK);
        //float x:文字left在x轴方向上距离坐标原点的距离；float y:文字bottom在y轴方向上距离坐标原点的距离
//        canvas.drawText(text, 200, 200, textPaint);
        //int start:截取文本的开始下标； int end:截取文本的结束下标；start和end是一个前开后闭的区间（即：包含start下标，不包含end下标）；下标从0开始；float x:文字left在x轴方向上距离坐标原点的距离；float y:文字bottom在y轴方向上距离坐标原点的距离
//        canvas.drawText(text, 1, 2, 200, 200, textPaint);//绘制“大”字 当end>text.length（end=text.length不会）, 会出现IndexOutOfBoundsException
//        char[] text1 = {'z', 'h', 'o', 'u'};
        //int index:字符数组开始下标；int count:绘制的字符数量；float x:文字left在x轴方向上距离坐标原点的距离；float y:文字bottom在y轴方向上距离坐标原点的距离
//        canvas.drawText(text1, 1, 2, 200, 200, textPaint);//当index+count>text.length, 会出现IndexOutOfBoundsException
        canvas.drawPosText(text, new float[]{200, 200, 300, 300}, textPaint);//必须指定每个文字的位置， 否则会出现ArrayIndexOutOfBoundsException
    }

    private int sp2px(float sp){
        float scale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale);
    }
}
