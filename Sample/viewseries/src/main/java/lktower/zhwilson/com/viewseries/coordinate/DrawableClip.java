package lktower.zhwilson.com.viewseries.coordinate;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by zhwilson on 2017/1/4.
 * 控制图片以扇形区裁剪显示
 */
public class DrawableClip extends Drawable implements Drawable.Callback {
    private Drawable mDrawable;

    private Path mPath = new Path();

    private float percent = 0.0f;

    public DrawableClip(Drawable drawable) {
        mDrawable = drawable;
        if (mDrawable != null)
            mDrawable.setCallback(this);
    }

    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | mDrawable.getChangingConfigurations();
    }

    @Override
    public boolean getPadding(Rect padding) {
        return mDrawable.getPadding(padding);
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        mDrawable.setVisible(visible, restart);
        return super.setVisible(visible, restart);
    }

    //drawable
    @Override
    public void draw(Canvas canvas) {
        if (percent < 1)
            percent += 0.01f;
        if (percent > 1) percent = 1;
        mPath.reset();
        RectF rectF = new RectF(getBounds());
        double radius = Math.pow(Math.pow(rectF.right, 2) + Math.pow(rectF.bottom, 2), 0.5);
        mPath.moveTo(rectF.right / 2, rectF.bottom / 2);
        mPath.lineTo(rectF.right / 2, 0);
        if (percent > 0.125f)
            mPath.lineTo(rectF.right, 0);
        if (percent > 0.375f)
            mPath.lineTo(rectF.right, rectF.bottom);
        if (percent > 0.625f)
            mPath.lineTo(0, rectF.bottom);
        if (percent > 0.875f)
            mPath.lineTo(0, 0);
        mPath.lineTo((float) (rectF.right / 2.0f + radius * Math.sin(Math.PI * 2 * percent)), (float) (rectF.bottom / 2.0f - radius * Math.cos(Math.PI * 2 * percent)));
        mPath.close();
        if (percent >= 0 && percent < 1) {
            canvas.save();
            canvas.clipPath(mPath);
            mDrawable.draw(canvas);
            canvas.restore();
            invalidateSelf();
        }
        if (percent == 1) {
            canvas.save();
            canvas.clipPath(mPath);
            mDrawable.draw(canvas);
            canvas.restore();
        }
    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mDrawable.setColorFilter(colorFilter);
    }

    @SuppressLint("NewApi")
    @Override
    public void setTintList(ColorStateList tint) {
        mDrawable.setTintList(tint);
    }

    @SuppressLint("NewApi")
    @Override
    public void setTintMode(PorterDuff.Mode tintMode) {
        mDrawable.setTintMode(tintMode);
    }

    @Override
    public boolean isStateful() {
        return mDrawable.isStateful();
    }

    @Override
    protected boolean onStateChange(int[] state) {
        return mDrawable.setState(state);
    }

    @Override
    protected boolean onLevelChange(int level) {
        mDrawable.setLevel(level);
        invalidateSelf();
        return true;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mDrawable.setBounds(bounds);
    }

    @Override
    public int getIntrinsicHeight() {
        return mDrawable.getIntrinsicHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return mDrawable.getIntrinsicWidth();
    }

    public void setPercent(float percent) {
        if (percent > 1)
            percent = 1;
        if (percent < 0)
            percent = 0;
        if (this.percent != percent) {
            this.percent = percent;
            invalidateSelf();
        }
    }

    //callback
    @Override
    public void invalidateDrawable(Drawable who) {
        Callback callback = getCallback();
        if (callback != null)
            callback.invalidateDrawable(this);
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        Callback callback = getCallback();
        if (callback != null)
            callback.scheduleDrawable(this, what, when);
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        Callback callback = getCallback();
        if (callback != null)
            callback.unscheduleDrawable(this, what);
    }
}
