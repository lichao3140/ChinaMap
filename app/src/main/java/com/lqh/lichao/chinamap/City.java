package com.lqh.lichao.chinamap;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 市级
 * Created by Administrator on 2017-10-16.
 */

public class City {
    private Path path;
    private int color;
    private String name;
    private boolean isSelected;

    private Paint paint;
    private View view;
    private float fraction;

    private PathMeasure pathMeasure;
    private ValueAnimator animator;

    public City(String name, Path path, int color, View view) {
        this.name = name;
        this.path = path;
        this.color = color;
        this.view = view;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathMeasure = new PathMeasure(path, false);
    }

    public void onDraw(Canvas canvas) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);

        canvas.drawPath(path, paint);

        paint.setStrokeWidth(2);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);


    }

    public void animationPath(boolean isSelected) {
        if (isSelected) {
            animator = ValueAnimator.ofFloat(0, 1);
            animator.setDuration(5000);
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    fraction = animation.getAnimatedFraction();
                    view.invalidate();
                }
            });
            animator.start();
        } else {
            if (animator != null) {
                animator.cancel();
            }
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
