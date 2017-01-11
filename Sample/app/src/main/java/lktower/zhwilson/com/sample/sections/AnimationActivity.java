package lktower.zhwilson.com.sample.sections;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import lktower.zhwilson.com.sample.R;

/**
 * Created by zhwilson on 2016/12/29.
 */
public class AnimationActivity extends Activity implements View.OnClickListener {
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        init();
    }

    private void init() {
        title = (TextView) findViewById(R.id.title);
        Button down = (Button) findViewById(R.id.down);
        down.setOnClickListener(this);
        Button up = (Button) findViewById(R.id.up);
        up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.down:
                animDown();
                break;
            case R.id.up:
                animUp();
                break;
        }
    }

    @SuppressWarnings("ResourceType")
    private void animDown() {
//        Keyframe keyframe1 = Keyframe.ofFloat(-title.getHeight(), 0);
//        Keyframe keyframe2 = Keyframe.ofFloat(0, -title.getHeight()+500);
//        Keyframe keyframe3 = Keyframe.ofFloat(-title.getHeight()+500, 0);
//        Keyframe keyframe4 = Keyframe.ofFloat(0, -title.getHeight()+300);
//        Keyframe keyframe5 = Keyframe.ofFloat(-title.getHeight()+300, 0);
//        Keyframe keyframe6 = Keyframe.ofFloat(0, -title.getHeight()+100);
//        Keyframe keyframe7 = Keyframe.ofFloat(-title.getHeight()+100, 0);
//        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe(View.Y, keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6, keyframe7);
//        Animator anim_down = ObjectAnimator.ofPropertyValuesHolder(title, holder);
//        anim_down.setDuration(3000);
//        anim_down.start();

        Animator downAnim1 = ObjectAnimator.ofFloat(title, View.Y, -title.getHeight(), 0);
        Animator downAnim2 = ObjectAnimator.ofFloat(title, View.Y, 0, -500);
        Animator downAnim3 = ObjectAnimator.ofFloat(title, View.Y, -500, 0);
        Animator downAnim4 = ObjectAnimator.ofFloat(title, View.Y, 0, -300);
        Animator downAnim5 = ObjectAnimator.ofFloat(title, View.Y, -300, 0);
        Animator downAnim6 = ObjectAnimator.ofFloat(title, View.Y, 0, -100);
        Animator downAnim7 = ObjectAnimator.ofFloat(title, View.Y, -100, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.play(downAnim1).before(downAnim2);
        animatorSet.play(downAnim2).before(downAnim3);
        animatorSet.play(downAnim3).before(downAnim4);
        animatorSet.play(downAnim4).before(downAnim5);
        animatorSet.play(downAnim5).before(downAnim6);
        animatorSet.play(downAnim6).before(downAnim7);
        animatorSet.start();


//        Animator anim_down = AnimatorInflater.loadAnimator(this, R.animator.anim_down);
//        anim_down.setTarget(title);
//        anim_down.start();

//        Animator downAnim = ObjectAnimator.ofFloat(title, View.Y, -title.getHeight(), 0);
//        downAnim.setDuration(2000);
//        downAnim.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//        });
//        downAnim.start();
    }

    private void animUp() {
        Animator upAnim = ObjectAnimator.ofFloat(title, View.Y, 0, -title.getHeight());
        upAnim.setDuration(2000);
        upAnim.addListener(new Animator.AnimatorListener() {
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
        upAnim.start();
    }
}
