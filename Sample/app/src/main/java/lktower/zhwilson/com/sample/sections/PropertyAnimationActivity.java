package lktower.zhwilson.com.sample.sections;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import lktower.zhwilson.com.sample.R;
import lktower.zhwilson.com.viewseries.coordinate.AnimatorView;

/**
 * Created by zhwilson on 2017/9/8.
 */
public class PropertyAnimationActivity extends Activity {
    private AnimatorView animatorView;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        icon = (ImageView) findViewById(R.id.iamge);
        animatorView = (AnimatorView) findViewById(R.id.object_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                doAnimate();
                objectAnimator4();
            }
        }, 1000);
    }

    private void doAnimate() {
        propertyAnimator();
    }

    //属性动画 ViewPropertyAnimator

    private void propertyAnimator() {
//        icon.animate().translationX(500).setDuration(2000);
//        icon.animate().translationY(500).setDuration(2000);
//        icon.animate().translationYBy(500).setDuration(2000);
//        icon.setTranslationX(500);
//        icon.animate().translationZ(500).setDuration(2000);
//        icon.animate().rotation(180).setDuration(2000);
//        icon.animate().rotationX(180).setDuration(2000);
//        icon.animate().rotationXBy(180).setDuration(2000);
//        icon.animate().rotationY(180).setDuration(2000);
//        icon.animate().scaleX(0.5f).setDuration(2000);
        icon.animate().alpha(0.5f).setDuration(2000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationStart(Animator animation) {

            }
        }).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
    }

    private void objectAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(animatorView, "length", 0, 500f);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new LinearInterpolator());//线性插值器
//        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());//先加速再减速插值器(使用的正弦 / 余弦曲线)
//        objectAnimator.setInterpolator(new FastOutSlowInInterpolator());//先加速再减速插值器（使用的贝塞尔曲线）
//        objectAnimator.setInterpolator(new AccelerateInterpolator());//加速插值器(使用的是指数曲线)
//        objectAnimator.setInterpolator(new FastOutLinearInInterpolator());//加速插值器(使用的是贝塞尔曲线)
//        objectAnimator.setInterpolator(new DecelerateInterpolator());//减速插值器
//        objectAnimator.setInterpolator(new LinearOutSlowInInterpolator());//减速插值器
//        objectAnimator.setInterpolator(new AnticipateInterpolator());//先回拉一下再进行正常动画轨迹插值器
//        objectAnimator.setInterpolator(new OvershootInterpolator());//动画会超过目标值一些插值器
//        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());//开始前回拉，最后超过一些然后回弹插值器
//        objectAnimator.setInterpolator(new BounceInterpolator());//在目标值处弹跳插值器
//        objectAnimator.setInterpolator(new CycleInterpolator(3.0f));//进行x个周期的变换然后恢复原状插值器
//        Path interpolatorPath = new Path();
////        interpolatorPath.quadTo(1, 0, 1, 1);
//        interpolatorPath.lineTo(0.25f, 0.25f);
//        interpolatorPath.moveTo(0.25f, 1.5f);
//        interpolatorPath.lineTo(1, 1);
//        objectAnimator.setInterpolator(new PathInterpolator(interpolatorPath));//自定义动画完成度 / 时间完成度曲线插值器
        //TypeEvaluator
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationStart(Animator animation) {

            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        objectAnimator.addPauseListener(new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {

            }

            @Override
            public void onAnimationResume(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    private void objectAnimator2() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animatorView, "color", 0xffff0000, 0xff00ff00);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setDuration(3000);
        objectAnimator.start();

    }

    private void objectAnimator3() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(icon, holder1, holder2, holder3);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    private void objectAnimator4() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(icon, holder1, holder2, holder3);
        objectAnimator1.setDuration(3000);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(icon, "translationX", 0, 400f);
        objectAnimator2.setDuration(3000);
        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(objectAnimator1, objectAnimator2);
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.start();
    }

    private void obejectAnimator5() {
        //动画分段处理
        //public static Keyframe ofFloat(float fraction, float value)
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 0);
        Keyframe keyframe3 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("", keyframe1, keyframe2, keyframe3);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(icon, holder);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            doAnimate();
        }
        return super.onTouchEvent(event);
    }
}
