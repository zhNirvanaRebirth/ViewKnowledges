package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2017/12/18.
 * Paint详解
 */
public class PaintProbe extends View {
    private int width;
    private int height;
    private Paint paint;

    public PaintProbe(Context context) {
        this(context, null);
    }

    public PaintProbe(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintProbe(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
        canvas.drawColor(Color.WHITE);
        canvas.translate(width * 1.0f / 2, height * 1.0f / 2);
//        String text = "鲁迅加油";
//        paint.setTextSize(150);
//        float offset = paint.measureText(text)/2;
//        int[] color = {Color.parseColor("#2ad018"), Color.parseColor("#fe002e"), Color.parseColor("#d5207f"), Color.parseColor("#0b6dd8")};
//        float[] position = {0f, 0.5f, 0.5f, 1f};
//        LinearGradient linearGradient = new LinearGradient(-offset, 0, offset, 0, color, position, Shader.TileMode.CLAMP);
//        paint.setShader(linearGradient);
//        canvas.drawText(text, -offset, 0, paint);

//        LinearGradient linearGradient = new LinearGradient(-200, 0, 200, 0, Color.RED, Color.BLUE, Shader.TileMode.REPEAT);
//        paint.setShader(linearGradient);
//        canvas.drawRect(-width/2, -height/2, width/2, height/2, paint);

//        int[] colors = {Color.parseColor("#2ad018"), Color.parseColor("#fe002e"), Color.parseColor("#0b6dd8"), Color.parseColor("#d5207f")};
//        float[] stops = {0.5f, 0.75f, 0.75f, 1.0f};

//        int[] colors = {Color.parseColor("#2ad018"), Color.parseColor("#fe002e")};
//        float[] stops = {0f, 1.0f};
//        RadialGradient radialGradient1 = new RadialGradient(0, 0, 400, colors, stops, Shader.TileMode.MIRROR);
//        paint.setShader(radialGradient1);
//        canvas.drawCircle(0, 0, 800, paint);

//        RadialGradient radialGradient2 = new RadialGradient(0, 0, 400, Color.parseColor("#2ad018"), Color.parseColor("#fe002e"), Shader.TileMode.CLAMP);
//        paint.setShader(radialGradient2);
//        canvas.drawCircle(0, 0, 800, paint);

//        int[] colors = {Color.parseColor("#2ad018"), Color.parseColor("#fe002e"), Color.parseColor("#0b6dd8"), Color.parseColor("#d5207f")};
//        float[] positions = {0.5f, 0.75f, 0.75f, 1.0f};
//        int[] colors = {Color.parseColor("#2ad018"), Color.parseColor("#fe002e")};
//        float[] positions = {0.5f, 0.75f};
//        SweepGradient sweepGradient1 = new SweepGradient(0, 0, colors, positions);
//        paint.setShader(sweepGradient1);

//        SweepGradient sweepGradient2 = new SweepGradient(0, 0, Color.parseColor("#2ad018"), Color.parseColor("#fe002e"));
//        paint.setShader(sweepGradient2);
//        canvas.drawCircle(0, 0, 800, paint);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
//        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        paint.setShader(bitmapShader);
//        canvas.drawRect(-width/2, -height/2, width/2, height/2, paint);

//        int[] colors1 = {Color.parseColor("#2ad018"), Color.parseColor("#fe002e"), Color.parseColor("#0b6dd8"), Color.parseColor("#d5207f")};
//        float[] positions1 = {0.25f, 0.5f, 0.75f, 1.0f};
//        SweepGradient sweepGradient1 = new SweepGradient(0, 0, colors1, positions1);
//        paint.setShader(sweepGradient1);

        //test
//        int[] colors2 = {Color.parseColor("#d5207f"), Color.parseColor("#2ad018"), Color.parseColor("#fe002e"), Color.parseColor("#0b6dd8"), Color.parseColor("#d5207f")};
//        float[] positions2 = {0f, 0.25f, 0.5f, 0.75f, 1.0f};
//        SweepGradient sweepGradient2 = new SweepGradient(0, 0, colors2, positions2);
//        paint.setShader(sweepGradient2);
//        canvas.drawCircle(0, 0, 600, paint);

//        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.src);
//        Bitmap dist = BitmapFactory.decodeResource(getResources(), R.drawable.pureback);
//        Log.e("zhwilson:", "dist size:" + dist.getWidth() + "-" + dist.getHeight());
//        Log.e("zhwilson:", "src size:" + src.getWidth() + "-" + src.getHeight());
//        int saveCount = canvas.saveLayer(-width*1.0f/2, -height*1.0f/2, width*1.0f/2, height*1.0f/2, paint);
//        canvas.drawBitmap(dist, -dist.getWidth()/2, -dist.getHeight()/2, paint);
//        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        paint.setXfermode(xfermode);
//        canvas.drawBitmap(src, -src.getWidth()/2, -src.getHeight()/2, paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(saveCount);

//        int saveCount = canvas.saveLayer(-width*1.0f/2, -height*1.0f/2, width*1.0f/2, height*1.0f/2, paint);
//        int saveCount = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(-200, -200, 200, 200, paint);
//        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        paint.setXfermode(xfermode);
//        paint.setColor(Color.RED);
//        canvas.drawCircle(200, 200, 200, paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(saveCount);

//        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.back);
//        canvas.drawBitmap(back, -back.getWidth()/2, -back.getHeight()/2, paint);

//        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.back);

//        LightingColorFilter lightingColorFilter = new LightingColorFilter(Color.parseColor("#00ffff"), Color.parseColor("#000000"));//除去红色
//        paint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(back, -back.getWidth()/2, -back.getHeight()/2, paint);

//        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN);
//        paint.setColorFilter(porterDuffColorFilter);
//        canvas.drawBitmap(back, -back.getWidth()/2, -back.getHeight()/2, paint);

//        paint.setStrokeWidth(50);
//        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeJoin(Paint.Join.MITER);
//        Path path= new Path();
//        path.moveTo(-200, -200);
//        path.lineTo(200, -200);
//        path.lineTo(0, 0);
//        path.close();
//        canvas.drawPath(path, paint);

//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        PathEffect pathEffect = new CornerPathEffect(20);

//        PathEffect pathEffect = new DiscretePathEffect(15, 15);

//        float[] intervals = {20, 10, 10, 5};
//        PathEffect pathEffect = new DashPathEffect(intervals, 20);

//        Path path = new Path();
//        path.addCircle(0, 0, 10, Path.Direction.CW);
//        PathEffect pathEffect = new PathDashPathEffect(path, 40, 0, PathDashPathEffect.Style.TRANSLATE);

//        PathEffect pathEffect1 = new DiscretePathEffect(40, 10);
//        float[] intervals = {20, 10, 10, 5};
//        PathEffect pathEffect2 = new DashPathEffect(intervals, 0);
//        PathEffect pathEffect = new SumPathEffect(pathEffect1, pathEffect2);

//        PathEffect pathEffect1 = new DiscretePathEffect(40, 20);
//        float[] intervals = {20, 10};
//        PathEffect pathEffect2 = new DashPathEffect(intervals, 0);
//        PathEffect pathEffect = new ComposePathEffect(pathEffect1, pathEffect2);
//        paint.setPathEffect(pathEffect);
//        canvas.drawRect(-200, -200, 200, 200, paint);
//        Path path = new Path();
//        path.moveTo(-200, 0);
//        path.lineTo(0, -200);
//        path.lineTo(200, 0);
//        canvas.drawPath(path, paint);

//        paint.setShadowLayer(10, 10, 10, Color.BLUE);
//        canvas.drawRect(-200, -200, 200, 200, paint);

        canvas.drawRect(-200, -700, 200, -300, paint);

        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(100, BlurMaskFilter.Blur.OUTER);
        paint.setMaskFilter(blurMaskFilter);

        canvas.drawRect(-200, -200, 200, 200, paint);
    }
}
