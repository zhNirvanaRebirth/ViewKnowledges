package lktower.zhwilson.com.viewseries.coordinate.entity;

import android.graphics.Matrix;
import android.graphics.PointF;

/**
 * Created by zhwilson on 2017/12/29.
 * 叶子数据
 */
public class LeafData {
    public PointF p = new PointF();//叶子的位置
    public float rangeY;//叶子在y轴上的最大值
    public boolean reverse = false;//是否翻转叶子的y轴值（就是把正玄值取反）
    public float floatingDest;//叶子飘动距离
    public float period;//叶子的周期长度
    public boolean cross = false;//跳过这片叶子，不绘制（避免后期的叶子拥挤状态）
    public Matrix matrix = new Matrix();//记录叶子的旋转变换
}
