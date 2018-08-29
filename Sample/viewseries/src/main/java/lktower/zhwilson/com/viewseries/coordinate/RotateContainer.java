package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2018/1/5.
 * 3d旋转容器
 */
public class RotateContainer extends View {
    private Paint paint;
    private Paint textPaint;
    private Camera camera;

    private int width;
    private int height;
    private float rectWidth = dp2px(200);
    private float rectHeight = dp2px(50);
    private float xZ = (float) (rectHeight / (2 * Math.sqrt(3)));

    private float degree = 0;
    private float density = 0;

    private int[] colors = {Color.WHITE, Color.YELLOW, Color.BLUE};

    public RotateContainer(Context context) {
        this(context, null);
    }

    public RotateContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public RotateContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
        density = getResources().getDisplayMetrics().density;
        camera = new Camera();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
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
        canvas.drawColor(Color.parseColor("#242C37"));
        canvas.save();
        Matrix matrix1 = new Matrix();
        matrix1.postTranslate(width * 1.0f / 2, 600);
        canvas.concat(matrix1);
        paint.setColor(Color.parseColor("#88f4fC37"));
        canvas.drawRect(-rectWidth / 2, -rectHeight / 2, rectWidth / 2, rectHeight / 2, paint);
        canvas.restore();

        degree = 30;
        canvas.save();
        Matrix matrix = new Matrix();
        camera.save();
        camera.rotateX(degree);
        float aa = (float) (rectHeight * Math.sin(degree * Math.PI / 180));
        camera.translate(0, (float) (aa * Math.sin(degree * Math.PI / 180)), (float) (aa * Math.cos(degree * Math.PI / 180)));
        camera.getMatrix(matrix);
        camera.restore();
        matrix.postTranslate(width * 1.0f / 2, 600 - rectHeight / 2);
        canvas.concat(matrix);
        paint.setColor(colors[0]);
        canvas.drawRect(-rectWidth / 2, 0, rectWidth / 2, rectHeight, paint);
        canvas.restore();

        canvas.save();
        Matrix matrix2 = new Matrix();
        camera.save();
        float rotateDeg = 120 - degree;
        camera.rotateX(-rotateDeg);
        float bb = (float) (rectHeight * Math.sin(rotateDeg * Math.PI / 180));
        camera.translate(0, -(float) (bb * Math.sin(rotateDeg * Math.PI / 180)), (float) (bb * Math.cos(rotateDeg * Math.PI / 180)));
        camera.getMatrix(matrix2);
        camera.restore();
        matrix2.postTranslate(width * 1.0f / 2, 600 + rectHeight / 2);
        canvas.concat(matrix2);
        paint.setColor(colors[1]);
        canvas.drawRect(-rectWidth / 2, -rectHeight, rectWidth / 2, 0, paint);
        canvas.restore();
    }


    private float dp2px(float dp) {
        return getResources().getDisplayMetrics().density * dp + 0.5f;
    }
}
