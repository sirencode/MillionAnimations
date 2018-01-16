package com.example.shenyonghe.millionanimations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class TestAnimationActivity extends Activity {
    CountDownProgressView countDownView;
    private ImageView fh;
    private ImageView clock;
    private ImageView finishReborn;
    private ImageView liveVolume;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testanimation);
        countDownView = findViewById(R.id.countdownProgressView);
        fh = findViewById(R.id.iv_fuhuo);
        clock = findViewById(R.id.iv_clock);
        finishReborn = findViewById(R.id.iv_finish_reborn);
        liveVolume = findViewById(R.id.iv_volume);
        liveVolume.setBackgroundResource(R.drawable.volume_animation);
        AnimationDrawable liveVolumeBackground = (AnimationDrawable) liveVolume.getBackground();
        liveVolumeBackground.start();
        findViewById(R.id.btn_start_down).setOnClickListener(v -> {
            clock.setVisibility(View.GONE);
            countDownView.reStart();
        });

        countDownView.setProgressListener(progress -> {
            if (progress == 120) {
                countDownView.setVisibility(View.GONE);
                clock.setVisibility(View.VISIBLE);
                clock.setBackgroundResource(R.drawable.clock_animation);
                AnimationDrawable animationDrawable = (AnimationDrawable) clock.getBackground();
                animationDrawable.start();
            }
        });

        findViewById(R.id.btn_start_fh).setOnClickListener(v -> {
            startFuHuoAnimation();
        });

        findViewById(R.id.btn_setP).setOnClickListener(v -> {
            ProgressBarAnimation progressBarAnimation = findViewById(R.id.progressBar);
            progressBarAnimation.setProgress(70);
        });
    }

    private void startFuHuoAnimation() {
        float gap = fh.getHeight() / (float) 5;
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(fh, "translationX", 0, -gap, gap, -gap, 0, 0, 0, 0, 0);
        animator1.setDuration(1000);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(fh, "translationY", 0.0f, fh.getHeight() * 4);
        animator2.setDuration(300);
        animator2.setInterpolator(new LinearInterpolator());

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(fh, "scaleX", 1.0f, 2);
        animator3.setDuration(200);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(fh, "scaleY", 1.0f, 2);
        animator4.setDuration(200);

        ObjectAnimator animator5 = ObjectAnimator.ofFloat(fh, "alpha", 1.0f, 0);
        animator5.setDuration(300);

        AnimatorSet set = new AnimatorSet();
        set.play(animator2).after(animator1);
        set.play(animator3).after(animator2);
        set.play(animator3).with(animator4);
        set.play(animator5).after(animator3);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finishAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void finishAnimation() {
        finishReborn.setBackgroundResource(R.drawable.reborn_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) finishReborn.getBackground();
        animationDrawable.start();
        finishReborn.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishReborn.setVisibility(View.INVISIBLE);
            }
        }, 1000);
    }

}
