package lktower.zhwilson.com.viewseries.coordinate.entity;

/**
 * Created by zhwilson on 2017/12/13.
 * 扇形图数据
 */
public class PieChartData {
    private String note;//注释
    private float proportion;//百分比
    private int color;//扇形区域颜色

    private boolean mainPart = false;//是否占比最大
    private float centralAngle;//扇形边缘中心角度

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getProportion() {
        return proportion;
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isMainPart() {
        return mainPart;
    }

    public void setMainPart(boolean mainPart) {
        this.mainPart = mainPart;
    }

    public float getCentralAngle() {
        return centralAngle;
    }

    public void setCentralAngle(float centralAngle) {
        this.centralAngle = centralAngle;
    }
}
