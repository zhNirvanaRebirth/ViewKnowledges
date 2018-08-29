package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import lktower.zhwilson.com.viewseries.R;

/**
 * Created by zhwilson on 2018/1/16.
 * 可折叠的侧边栏
 */
public class FoldSideBarView extends View {
    private static final int SIDE_BAR_WIDTH = 200;//侧边栏默认宽度，单位dp
    private static final int FOLD_NUM = 6;//侧边栏折叠次数

    private static final String INSTANCE_STATE = "saved_instance";
    private static final String EXPAND_PROPORTION = "expand_proportion";
    private static final String EXPAND_AUTO = "expand_auto";

    private float sideBarWidth;
    private int foldNum;
    private int width;//view的宽度
    private int height;//view的高度

    private Bitmap background;
    private Bitmap sideBarBackground;
    private RectF backSrc;
    private RectF backDst;
    private Rect sideSrc;
    private RectF sideDst;
    private Paint backPaint;

    private float expandProportion = 0f;//展开比例，取值0~1
    private boolean expandAuto = false;

    private Camera camera;
    private Matrix matrix;
    private float density;
    private float[] values = new float[9];

    public FoldSideBarView(Context context) {
        this(context, null);
    }

    public FoldSideBarView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.FoldSideBarStyle);
    }

    public FoldSideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FoldSideBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FoldSideBar, defStyleAttr, defStyleRes);
        sideBarWidth = typedArray.getDimension(R.styleable.FoldSideBar_side_bar_width, dp2px(SIDE_BAR_WIDTH));
        foldNum = typedArray.getInteger(R.styleable.FoldSideBar_fold_num, FOLD_NUM);
        background = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.FoldSideBar_content_background, R.drawable.back));
        sideBarBackground = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.FoldSideBar_side_bar_background, R.drawable.ffff));
        typedArray.recycle();

        initPaint();
        density = getResources().getDisplayMetrics().density;
        camera = new Camera();
        matrix = new Matrix();
    }

    private void initPaint() {
        backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backPaint.setDither(true);
        backPaint.setFilterBitmap(true);
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
        sideBarWidth = Math.min(sideBarWidth, width);
        sizeRect();
    }

    private void sizeRect() {
        backSrc = new RectF(0, 0, background.getWidth(), background.getHeight());
        backDst = new RectF(0, 0, width, height);
        sideSrc = new Rect(0, 0, sideBarBackground.getWidth(), sideBarBackground.getHeight());
        sideDst = new RectF(0, 0, sideBarWidth, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景
        drawBackground(canvas);
        //侧边栏
        drawSideBar(canvas);
        postInvalidate();
    }

    private void drawSideBar(Canvas canvas) {
        if (expandProportion == 0) return;
        if (expandAuto) {
            if (expandProportion < 0.5f) {
                expandProportion -= 0.03f;
                if (expandProportion <= 0) {
                    expandProportion = 0;
                    expandAuto = false;
                }
            } else {
                expandProportion += 0.03f;
                if (expandProportion >= 1) {
                    expandProportion = 1;
                    expandAuto = false;
                }
            }
        }
        float singleProjectionW = sideBarWidth * expandProportion / foldNum;//经过折叠后每份投影宽度
        float singleW = sideBarWidth / foldNum;//折叠边栏每份的宽度
        float deg = (float) (Math.acos(singleProjectionW / singleW) * 180 / Math.PI);
        for (int i = 0; i < foldNum; i++) {
            canvas.save();
            matrix.reset();
            camera.save();
            camera.rotateY(i % 2 == 0 ? deg : -deg);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.getValues(values);
            values[6] = values[6] / density;
            values[7] = values[7] / density;
            matrix.setValues(values);
            matrix.preTranslate(-(singleW / 2 + i * singleW), -height * 1.0f / 2);
            matrix.postTranslate(singleProjectionW / 2 + i * singleProjectionW, height * 1.0f / 2);
            canvas.concat(matrix);
            canvas.clipRect(i * singleW - 0.5f, 0, (i + 1) * singleW + 0.5f, height);
            canvas.drawBitmap(sideBarBackground, sideSrc, sideDst, backPaint);
            if (i % 2 == 1)
                canvas.drawColor(Color.argb((int) (127 * (1 - expandProportion)), 0, 0, 0));
            canvas.restore();
        }
    }

    private void drawBackground(Canvas canvas) {
        if (background == null) return;
        canvas.save();
        matrix.reset();
        matrix.setRectToRect(backSrc, backDst, Matrix.ScaleToFit.CENTER);
        canvas.drawBitmap(background, matrix, backPaint);
        canvas.drawColor(Color.argb((int) (127 * expandProportion), 0, 0, 0));
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("zhwilson", "FoldSideBarView dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    private float lastPosition = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isExpand() || event.getX() < 88) return true;
                break;
            case MotionEvent.ACTION_MOVE:
                float position = event.getRawX();
                float expand = (position - lastPosition) * 0.8f;
                expandProportion = expandProportion + expand / sideBarWidth;
                expandProportion = expandProportion < 0 ? 0 : expandProportion;
                expandProportion = expandProportion > 1 ? 1 : expandProportion;
                lastPosition = position;
                break;
            case MotionEvent.ACTION_UP:
                if (expandProportion > 0 || expandProportion < 1) expandAuto = true;
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isExpand() {
        return expandProportion == 1;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putFloat(EXPAND_PROPORTION, expandProportion);
        bundle.putBoolean(EXPAND_AUTO, expandAuto);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            expandProportion = bundle.getFloat(EXPAND_PROPORTION);
            expandAuto = bundle.getBoolean(EXPAND_AUTO, false);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private float dp2px(float dp) {
        return getResources().getDisplayMetrics().density * dp + 0.5f;
    }
}
