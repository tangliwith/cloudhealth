package com.ecuca.cloudhealth.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.ecuca.cloudhealth.R;

/**
 * Created by Administrator on 2016/6/20.
 */
public class SpotsDialog extends AlertDialog {

    private static final int DELAY = 150;
    private static final int DURATION = 1500;

    private int size;
    private AnimatedView[] spots;
    private AnimatorPlayer animator;

    public SpotsDialog(Context context) {
        this(context, R.style.SpotsDialogDefault);
    }

    public SpotsDialog(Context context, int theme) {
        super(context, theme);
    }

    public SpotsDialog(Context context, boolean cancelable,
                       OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        // 设置SelectPicPopupWindow弹出窗体动画效果
      //  window.setWindowAnimations(R.style.PopupAnimation);
        // // 设置SelectPicPopupWindow弹出窗体的背景
         window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = window.getAttributes();
        // 设置SelectPicPopupWindow弹出窗体的宽
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        // 设置SelectPicPopupWindow弹出窗体的高
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        setCanceledOnTouchOutside(false);
        initProgress();
    }

    @Override
    protected void onStart() {
        super.onStart();

        animator = new AnimatorPlayer(createAnimations());
        animator.play();
    }

    @Override
    protected void onStop() {
        super.onStop();

        animator.stop();
    }

    // ~

    private void initProgress() {
        ProgressLayout progress = (ProgressLayout) findViewById(R.id.progress);
        size = progress.getSpotsCount();
        spots = new AnimatedView[size];
        int size = getContext().getResources().getDimensionPixelSize(
                R.dimen.spot_size);
        int progressWidth = getContext().getResources().getDimensionPixelSize(
                R.dimen.progress_width);
        for (int i = 0; i < spots.length; i++) {
            AnimatedView v = new AnimatedView(getContext());
            v.setBackgroundResource(R.drawable.spot);
            v.setTarget(progressWidth);
            v.setXFactor(-1f);
            progress.addView(v, size, size);
            spots[i] = v;
        }
    }
    @SuppressLint("NewApi")
    private Animator[] createAnimations() {
        Animator[] animators = new Animator[size];
        for (int i = 0; i < spots.length; i++) {
            Animator move = ObjectAnimator.ofFloat(spots[i], "xFactor", 0, 1);
            move.setDuration(DURATION);
            move.setInterpolator(new HesitateInterpolator());
            move.setStartDelay(DELAY * i);
            animators[i] = move;
        }
        return animators;
    }
}
