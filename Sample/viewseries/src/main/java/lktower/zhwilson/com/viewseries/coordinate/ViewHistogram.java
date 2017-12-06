package lktower.zhwilson.com.viewseries.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lktower.zhwilson.com.viewseries.coordinate.entity.HistogramData;

/**
 * Created by zhwilson on 2017/12/6.
 * 柱状图
 * 剩下就是文字的绘制了
 */
public class ViewHistogram extends View {
    private int width;//控件的宽度
    private int height;//控件的高度
    private Paint linePaint;//坐标画笔
    private Paint textPaint;//文字画笔
    private Paint pillarPaint;//柱子画笔

    private int backColor = Color.BLUE;//view背景颜色
    private int lineColor = Color.GRAY;//坐标轴颜色
    private int textColor = Color.WHITE;//文字颜色
    private int pillarColor = Color.GREEN;//柱子颜色

    private float lineWidth = 5.0f;//坐标轴线宽
    private float textSize = 15.0f;//文字大小
    private float pillarWidth = 50.0f;//柱子宽度
    private float padding = 25.0f;//柱状图的padding
    private float minPillarMargin = 5.0f;//柱子之间最小的间距
    private float highestPillarTopPadding = 20.0f;//最高柱子的高度与纵轴顶点的距离
    private float highestPillarHeight;//最高柱子的高度

    private float x_axis;//横坐标长度
    private float y_axis;//纵坐标长度

    private List<HistogramData> datas;
    private float maxDataNum;

    public ViewHistogram(Context context) {
        this(context, null);
    }

    public ViewHistogram(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewHistogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineWidth);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        pillarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pillarPaint.setColor(pillarColor);
    }

    public void setDatas(List<HistogramData> datas) {
        for (HistogramData data : datas) {
            maxDataNum = maxDataNum < data.getNum() ? data.getNum() : maxDataNum;
        }
        if (this.datas == null)
            this.datas = new ArrayList<>();
        this.datas.clear();
        for (HistogramData data : datas) {
            data.setProportion(data.getNum() / maxDataNum);
            this.datas.add(data);
        }
        postInvalidate();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return super.getSuggestedMinimumHeight();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return super.getSuggestedMinimumWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSpec(widthMeasureSpec, true), measureSpec(heightMeasureSpec, false));
    }

    private int measureSpec(int measureSpec, boolean isWidth) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            if (isWidth) {
                result = size < getSuggestedMinimumWidth() ? getSuggestedMinimumWidth() : size;
            } else {
                result = size < getSuggestedMinimumHeight() ? getSuggestedMinimumHeight() : size;
            }
        }

        return result;
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
        canvas.drawColor(backColor);
        canvas.save();
        canvas.translate(padding, height - padding);//记得此时y轴还是往下为正
        drawAxis(canvas);
        drawPillar(canvas);
        canvas.restore();
    }

    //画坐标轴
    private void drawAxis(Canvas canvas) {
        x_axis = width - padding * 2;
        y_axis = height - padding * 2;
        canvas.drawLine(0, 0, x_axis, 0, linePaint);//x轴
        canvas.drawLine(0, 0, 0, -y_axis, linePaint);//y轴
    }

    //画柱子 这里我们设置最大数据的柱子的高度的比例是1，且比纵轴低20px
    private void drawPillar(Canvas canvas) {
        if (datas == null || datas.size() == 0)
            return;
        int pillarNum = datas.size();
        float pillarMargin = minPillarMargin;
        float minNeedWidth = pillarNum * (pillarWidth + minPillarMargin) + minPillarMargin;//柱状图需要的最小宽度
        if (minNeedWidth > x_axis) {//画不完柱子，此时减少柱子的宽度
            pillarWidth = (x_axis - minPillarMargin * (pillarNum + 1)) / pillarNum;
            //TODO 这里后面处理下当柱子宽度实在太小，就提示用户不要这么整！
        } else {
            pillarMargin = (x_axis - pillarWidth * pillarNum) / (pillarNum + 1);
        }
        highestPillarHeight = height - padding * 2 - highestPillarTopPadding;
        for (int i = 0; i < pillarNum; i++) {
            canvas.save();
            canvas.translate((i + 1) * pillarMargin + i * pillarWidth, 0);
            canvas.drawRect(0, -highestPillarHeight * datas.get(i).getProportion(), pillarWidth, 0, pillarPaint);
            canvas.restore();
        }
    }
}
