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

import java.util.ArrayList;
import java.util.List;

import lktower.zhwilson.com.viewseries.R;
import lktower.zhwilson.com.viewseries.coordinate.entity.LeafData;

/**
 * Created by zhwilson on 2017/12/28.
 * 仿写一个加载进度条
 */
public class LoadingProgress extends View {
    private static final int RADIUS = 20;//进度条内部圆角半径，单位：dp
    private static final int MARGIN = 3;//进度条与外部圆角进度条Rect的margin，单位：dp
    private static final int CIRCLE_STROKE_WIDTH = 2;//圆圈线宽，单位：dp
    private static final int OUT_RECT_COLOR = Color.parseColor("#fce49b");//外部进度条Rect颜色
    private static final int PROGRESS_RECT_COLOR = Color.parseColor("#ffa800");//进度条颜色
    private static final int ROUND_COLOR = Color.parseColor("#fbcd4e");//背景圆的颜色
    private static final int STROKE_COLOR = Color.parseColor("#ffffff");//背景圆圈的边框颜色
    private static final int LEAVES_NUM = 10;//叶子的数量
    private static final int LEAF_WIDTH = 11;//叶子的宽度，单位dp
    private static final int LEAF_HEIGHT = 6;//叶子的高度，单位dp
    private static final int LEAF_FLOAT_STEP = 5;//叶子飘动步长，单位dp

    private float radius = dp2px(RADIUS);//进度条内部圆角半径，单位：px
    private float margin = dp2px(MARGIN);//进度条与外部圆角进度条Rect的margin，单位：px
    private float circleStrokeWidth = dp2px(CIRCLE_STROKE_WIDTH);//圆圈线宽，单位：px
    private int outRectColor = OUT_RECT_COLOR;
    private int progressRectColor = PROGRESS_RECT_COLOR;
    private int roundColor = ROUND_COLOR;
    private int strokeColor = STROKE_COLOR;
    private int leavesNum = LEAVES_NUM;
    private float leafWidth = dp2px(LEAF_WIDTH);
    private float leafHeight = dp2px(LEAF_HEIGHT);

    private int width;//view宽度
    private int height;//view高度

    private Paint outRectPaint;//外部圆角进度条Paint
    private Paint circlePaint;//圆圈Paint
    private Paint roundPaint;//圆形Paint
    private Paint progressPaint;//进度条Paint

    private Bitmap fans;
    Matrix fansMatrix = new Matrix();

    private float progress = 0;//当前的进度
    private PictureDrawable progressPictureDrawable;

    private Bitmap leaf;
    private List<LeafData> leaves;
    private int floatingLeaves = 1;//记录飘飞的叶子（用于开始时，一片一片的产生叶子）
    private float floatingStep = dp2px(LEAF_FLOAT_STEP);
    private LeafData lastLeaf;//记录产生的最后一片叶子

    public LoadingProgress(Context context) {
        this(context, null);
    }

    public LoadingProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LoadingProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
        fans = BitmapFactory.decodeResource(getResources(), R.drawable.fans);
        leaf = BitmapFactory.decodeResource(getResources(), R.drawable.leaf);
    }

    private void initPaint() {
        outRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outRectPaint.setColor(outRectColor);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(strokeColor);
        circlePaint.setStrokeWidth(circleStrokeWidth);
        circlePaint.setStyle(Paint.Style.STROKE);

        roundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        roundPaint.setColor(roundColor);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressRectColor);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) (radius * 2 + margin * 2);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) (radius * 2 + margin * 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = isWidth ? Math.max(getSuggestedMinimumWidth(), size) : Math.max(getSuggestedMinimumHeight(), size);
        } else {
            result = isWidth ? Math.max(getSuggestedMinimumWidth(), size) : Math.min(getSuggestedMinimumHeight(), size);
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        initProgressPicture();
        initLeaves();
    }

    private void initLeaves() {
        leaves = new ArrayList<>();
        float offset = (float) Math.sqrt(leafWidth * leafWidth + leafHeight * leafHeight);
        for (int i = 0; i < leavesNum; i++) {
            LeafData leaf = new LeafData();
            double random = Math.random();
            leaf.rangeY = (float) (random * (radius - leafHeight) + leafHeight - offset / 2);
            leaf.reverse = random > Math.random() ? true : false;
            leaf.period = Math.random() < 0.5 ? width - radius - margin * 2 : (width - radius - margin * 2) / 2;
            leaf.p.x = width - radius - margin;
            leaf.p.y = radius + margin;
            leaves.add(leaf);
        }
    }

    private void initProgressPicture() {
        Picture picture = new Picture();
        Canvas canvas = picture.beginRecording(width, (int) (radius * 2));
        canvas.drawRoundRect(margin, margin, width - margin, radius * 2 - margin, radius, radius, progressPaint);
        picture.endRecording();
        progressPictureDrawable = new PictureDrawable(picture);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.translate(0, height * 1.0f / 2 - radius - margin);
        //绘制外部进度条rect
        canvas.drawRoundRect(0, 0, width, (radius * 2 + margin * 2), radius + margin, radius + margin, outRectPaint);
        //画树叶
        drawLeaves(canvas);
        //绘制进度条
        drawProgress(canvas);
        //绘制进度条右侧的圆圈
        canvas.drawCircle(width - radius - margin, radius + margin, radius + margin - circleStrokeWidth / 2, circlePaint);
        //绘制进度条右侧的圆
        canvas.drawCircle(width - radius - margin, radius + margin, radius + margin - circleStrokeWidth, roundPaint);
        //绘制圆圈内的扇叶
        drawFans(canvas);
        postInvalidate();
    }

    private void drawFans(Canvas canvas) {
        canvas.save();
        canvas.translate(width - radius - margin, margin + radius);
        fansMatrix.postRotate(10);
        canvas.concat(fansMatrix);
        canvas.drawBitmap(fans, new Rect(0, 0, fans.getWidth(), fans.getHeight()), new RectF(-radius + circleStrokeWidth, -radius + circleStrokeWidth, radius - circleStrokeWidth, radius - circleStrokeWidth), new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.restore();
    }

    private void drawProgress(Canvas canvas) {
        progress += 0.5f;
        progress = Math.min(progress, 100);
        float current = (width - margin * 2) * progress / 100;//当前需要画的进度条长度
        canvas.save();
        canvas.translate(0, margin);
        progressPictureDrawable.setBounds(0, 0, (int) current, (int) (radius * 2));
        progressPictureDrawable.draw(canvas);
        canvas.restore();
    }

    private void drawLeaves(Canvas canvas) {
        for (int i = 0; i < floatingLeaves; i++) {
            canvas.save();
            canvas.translate(leaves.get(i).p.x, leaves.get(i).p.y);
            leaves.get(i).matrix.postRotate(10);
            canvas.concat(leaves.get(i).matrix);
            canvas.drawBitmap(leaf, new Rect(0, 0, leaf.getWidth(), leaf.getHeight()), new RectF(-leafWidth / 2, -leafHeight / 2, leafWidth / 2, leafHeight / 2), new Paint(Paint.ANTI_ALIAS_FLAG));
            calculateLeafPosition(leaves.get(i));
            increaseLeaf();
            canvas.restore();
        }
    }

    private void calculateLeafPosition(LeafData leaf) {
        leaf.p.x -= floatingStep;
        leaf.floatingDest += floatingStep;
        if (leaf.p.x <= margin) {
            leaf.p.x = width - radius - margin;
            leaf.floatingDest = 0;
        }
        float y = (float) (Math.sin((leaf.floatingDest / leaf.period) * Math.PI * 2) * leaf.rangeY);
        y = leaf.reverse ? -y : y;
        leaf.p.y = y + radius + margin;
    }

    private void increaseLeaf() {
        lastLeaf = leaves.get(floatingLeaves - 1);
        if (floatingLeaves < leavesNum) {
            if (lastLeaf.p.x < width - radius * 2 - margin * 2) {
                floatingLeaves++;
                lastLeaf = leaves.get(floatingLeaves - 1);
            }
        }
    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return density * dp + 0.5f;
    }
}
