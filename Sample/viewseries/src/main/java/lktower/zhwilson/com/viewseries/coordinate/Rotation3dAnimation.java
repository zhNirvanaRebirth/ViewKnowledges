package lktower.zhwilson.com.viewseries.coordinate;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zhwilson on 2017/4/19.
 */
public class Rotation3dAnimation extends Animation {
    //旋转角度
    private float mStartDegree;
    private float mEndDegree;
    //旋转中心
    private float mCenterX;
    private float mCenterY;
    //相机的位置
    private float mDepthZ;
    //控制相机位置翻转 true:从0到depthZ false：从depthZ到0
    private boolean mReverse;

    //相机
    private Camera mCamera;

    public Rotation3dAnimation(float startDegree, float endDegree, float centerX, float centerY, float depthZ, boolean reverse) {
        mStartDegree = startDegree;
        mEndDegree = endDegree;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    /**
     * @param interpolatedTime The value of the normalized time (0.0 to 1.0) after it has been run through the interpolation function.
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float startDegree = mStartDegree;
        float degree = startDegree + ((mEndDegree - startDegree) * interpolatedTime);
        float centerX = mCenterX;
        float centerY = mCenterY;
        Camera camera = mCamera;
        Matrix matrix = t.getMatrix();
        //这里的matrix一定是当前view对应的矩阵，实际上是个单位矩阵
        camera.save();
        if (mReverse)
            camera.translate(0f, 0f, mDepthZ * interpolatedTime);
        else camera.translate(0f, 0f, mDepthZ * (1.0f - interpolatedTime));
        camera.rotateY(degree);
        //这里将camera的矩阵变换设置到当前view的矩阵上，比如这里的camera的旋转变换，将其变换设置到view的矩阵上，看上去就好像是当前view在变换，但是这里camera顺时针旋转，到view上，就变成了逆时针，不解释，你懂的
        camera.getMatrix(matrix);
        //调整中心
        /**
         * 应该是错误的结论
         *
         * 不要被这里的字面意思迷惑了，这里说的是调整旋转中心，只是看上去像是选择中心被修改了一样
         * 这里的重点是pre变换和post变换，在矩阵上对应的操作是右乘（pre）和左乘（post），但是这里不能这么理解！
         * 这里应该这么理解：camera.getMatrix(matrix);相当于matrix.set(....);
         * 所以matrix.preTranslate(-centerX, -centerY);表示在set之前进行平移操作（平移到相机位置），再进行set操作（这里对应的是相机的旋转）
         * matrix.postTranslate(centerX, centerY);表示在set操作之后（这里对应的是旋转），再进行平移
         * 所以对应的过程是，平移-旋转-平移回来，在相机旋转的时候，旋转中心恰好是view的中心，所以相对于相机（人眼看见的），view就是在绕着自己的中心旋转
         * ，旋转完成，再平移会view原来的位置，看上去就想是view在原位置绕着自己的中心旋转
         *
         ******* 真正的理解应该还是要从matrix在三维空间变换的左乘右乘去理解吧！*******
         */
        matrix.postTranslate(centerX, centerY);
        matrix.preTranslate(-centerX, -centerY);

        camera.restore();
    }

}
