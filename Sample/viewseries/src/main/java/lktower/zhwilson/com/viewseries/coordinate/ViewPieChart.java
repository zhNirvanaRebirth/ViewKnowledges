package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lktower.zhwilson.com.viewseries.coordinate.entity.PieChartData;

/**
 * Created by zhwilson on 2017/12/11.
 * 饼状图
 */
public class ViewPieChart extends View {
    private static int PIE_RADIUS = 100;//饼状图半径，单位dp
    private static int CHAR_SIZE = 16;//字符的大小，单位sp
    private static int PIE_NOTE_DISTANCE = 30;//注释文字与圆左/右侧的距离，单位dp
    private static int NOTE_LINE_LENGTH = 10;//注释线的长度，单位dp
    private static int NOTE_STR_MAX_WIDTH = 30;//注释文字最大宽度，单位dp
    private static int MAIN_PART_TRANS = 8;//占比最大的扇形与圆心的距离，单位dp
    private static int PIE_BACK_COLOR = Color.parseColor("#506e79");//view背景颜色
    private static int NOTE_TEXT_COLOR = Color.WHITE;//注释文字颜色
    private static int PIE_PADDING = 5;//padding

    private float pieRadius = dp2px(PIE_RADIUS);//饼状图半径，单位px
    private float noteLineLength = dp2px(NOTE_LINE_LENGTH);//注释线长度，单位px
    private float pieNoteDistance = dp2px(PIE_NOTE_DISTANCE);//注释文字与圆左/右侧的距离，单位px
    private float noteStrMaxWidth = dp2px(NOTE_STR_MAX_WIDTH);//注释文字最大宽度，单位px
    private float mainPartTrans = dp2px(MAIN_PART_TRANS);////占比最大的扇形与圆心的距离，单位px
    private float piePadding = dp2px(PIE_PADDING);

    private float charSize = sp2px(CHAR_SIZE);//注释字符的大小，单位px
    private int noteTextColor = NOTE_TEXT_COLOR;
    private int pieBackColor = PIE_BACK_COLOR;

    private boolean drawNote = true;//是否绘制注释文字
    private boolean withMainPartTrans = true;//是否独立占比最大的扇形

    private int width;//view宽
    private int height;//view高

    private Paint notePaint;//注释文字画笔
    private Paint noteLinePaint;//注释线画笔
    private Paint arcPaint;//扇形区域画笔

    private List<PieChartData> datas;

    public ViewPieChart(Context context) {
        this(context, null);
    }

    public ViewPieChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        notePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        notePaint.setTextSize(charSize);
        notePaint.setColor(noteTextColor);

        noteLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        noteLinePaint.setColor(noteTextColor);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStyle(Paint.Style.FILL);
    }

    public void setDatas(List<PieChartData> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (this.datas == null)
            this.datas = new ArrayList<>();
        float maxProportion = 0;
        int maxProportionIndex = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (maxProportion < datas.get(i).getProportion()) {
                maxProportion = datas.get(i).getProportion();
                maxProportionIndex = i;
            }
        }
        datas.get(maxProportionIndex).setMainPart(true);
        this.datas.clear();
        this.datas.addAll(datas);
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
            result = size;
        } else {
            result = (int) (isWidth ? Math.min(size, getSuggestedMinimumWidth()) + piePadding * 2 : Math.min(size, getSuggestedMinimumHeight()) + piePadding * 2);
        }
        return result;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) (pieRadius * 2 + (drawNote ? pieNoteDistance * 2 + noteStrMaxWidth * 2 : 0) + (withMainPartTrans ? mainPartTrans : 0));
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) (pieRadius * 2 + (drawNote ? notePaint.getFontSpacing() * 2 : 0) + (withMainPartTrans ? mainPartTrans : 0));
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
        canvas.drawColor(pieBackColor);
        canvas.translate(width * 1.0f / 2, height * 1.0f / 2);
        if (datas == null || datas.size() == 0)
            return;
        RectF rectF = new RectF(-pieRadius, -pieRadius, pieRadius, pieRadius);
        drawArc(canvas, rectF);
        if (drawNote) drawNote(canvas);
    }

    //添加占比注释  注释线绘制思路：分段，扇形边缘一段，注释文字的地方一段，然后再连接着连段的端点，区别在于文字的影响（本来注释文字那一段应该和扇形边缘那段的端点在一条水平线上，但是因为两组注释的文字可能遮挡，所以可能会移动文字的位置，导致这两段的端点不在一条水平线上，这时就出现了三段线段）
    private void drawNote(Canvas canvas) {
        float fontSpacing = notePaint.getFontSpacing();
        RectF rightRectF = new RectF();//用来记录右侧有文字的区域
        RectF leftRectF = new RectF();//用来记录左侧有文字的区域
        boolean leftRecord = false;
        for (int i = 0; i < datas.size(); i++) {
            //先画扇形边缘注释线
            PointF start = new PointF();
            PointF end = new PointF();
            double radians = datas.get(i).getCentralAngle() * Math.PI / 180;
            start.x = (float) (pieRadius * Math.cos(radians));
            start.y = (float) (pieRadius * Math.sin(radians));
            end.x = (float) (start.x + noteLineLength * Math.cos(radians));
            end.y = (float) (start.y + noteLineLength * Math.sin(radians));
            canvas.drawLine(start.x, start.y, end.x, end.y, noteLinePaint);

            //绘制注释文字，当扇形的边缘中心在圆的右侧（0-90和270-360度），则注释文字绘制在右侧，否则在左边
            PointF nodePointF = new PointF();
            PointF textPointF = new PointF();
            textPointF.y = end.y;
            if (radians < Math.PI / 2 || radians > Math.PI * 3 / 2) {//右侧
                if (i == 0) {
                    rightRectF.top = textPointF.y - fontSpacing;
                    rightRectF.bottom = textPointF.y;
                }
                if (radians < Math.PI / 2) {
                    textPointF.y = textPointF.y - fontSpacing < rightRectF.bottom ? rightRectF.bottom + fontSpacing : textPointF.y;
                    rightRectF.bottom = textPointF.y;
                } else {
                    textPointF.y = textPointF.y > rightRectF.top ? rightRectF.top : textPointF.y;
                    rightRectF.top = textPointF.y - fontSpacing;
                }
                textPointF.x = pieRadius + pieNoteDistance;
                notePaint.setTextAlign(Paint.Align.LEFT);
                nodePointF.x = textPointF.x - noteLineLength;
            } else {
                if (!leftRecord) {
                    leftRectF.top = textPointF.y - fontSpacing;
                    leftRectF.bottom = textPointF.y;
                }
                textPointF.y = textPointF.y > leftRectF.top ? leftRectF.top : textPointF.y;
                leftRectF.top = textPointF.y - fontSpacing;
                textPointF.x = -(pieRadius + pieNoteDistance);
                notePaint.setTextAlign(Paint.Align.RIGHT);
                nodePointF.x = textPointF.x + noteLineLength;
            }
            nodePointF.y = textPointF.y;
            canvas.drawLine(textPointF.x, textPointF.y, nodePointF.x, nodePointF.y, noteLinePaint);
            canvas.drawText(datas.get(i).getNote(), textPointF.x, textPointF.y, notePaint);
        }
    }
    //画扇形
    private void drawArc(Canvas canvas, RectF rectF) {
        float startAngle = 0.0f;
        float sweepAngle;
        for (int i = 0; i < datas.size(); i++) {
            sweepAngle = datas.get(i).getProportion() / 100 * 360;
            arcPaint.setColor(datas.get(i).getColor());
            float centralAngle = startAngle + sweepAngle / 2;
            if (datas.get(i).isMainPart()) {
                canvas.save();
                canvas.translate((float) (mainPartTrans * Math.cos(centralAngle * Math.PI / 180)), (float) (mainPartTrans * Math.sin(centralAngle * Math.PI / 180)));
                canvas.drawArc(rectF, startAngle, sweepAngle, true, arcPaint);
                canvas.restore();
            } else {
                canvas.drawArc(rectF, startAngle, sweepAngle, true, arcPaint);
            }
            datas.get(i).setCentralAngle(centralAngle);
            startAngle += sweepAngle;
        }

    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return density * dp + 0.5f;
    }

    private float sp2px(float sp) {
        float density = getResources().getDisplayMetrics().scaledDensity;
        return density * sp;
    }
}

