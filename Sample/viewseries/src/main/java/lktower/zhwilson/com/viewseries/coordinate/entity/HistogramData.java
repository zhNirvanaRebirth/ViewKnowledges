package lktower.zhwilson.com.viewseries.coordinate.entity;

/**
 * Created by zhwilson on 2017/12/6.
 * 柱状图数据
 */
public class HistogramData {
    private float num;//数量
    private String name;//名字
    private float proportion;//高度比例，画柱子的时候使用

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProportion() {
        return proportion;
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }
}
