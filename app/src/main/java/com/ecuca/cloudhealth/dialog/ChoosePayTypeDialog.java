package com.ecuca.cloudhealth.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class ChoosePayTypeDialog extends Dialog {


    @BindView(R.id.rel_balance)
    RelativeLayout relBalance;
    @BindView(R.id.rel_wechat)
    RelativeLayout relWechat;
    @BindView(R.id.rel_ali)
    RelativeLayout relAli;

    OnChoosePayTypeListener onChoosePayTypeListener;
    public ChoosePayTypeDialog(@NonNull Activity context) {
        super(context);
        initView(context);
    }

    public ChoosePayTypeDialog(@NonNull Activity context, @StyleRes int themeResId,OnChoosePayTypeListener onChoosePayTypeListener) {
        super(context, themeResId);

        this.onChoosePayTypeListener=onChoosePayTypeListener;
        initView(context);
    }

    protected ChoosePayTypeDialog(@NonNull Activity context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(final Activity context) {
        View view = getLayoutInflater().inflate(R.layout.dia_choose_pay_type,
                null);
        ButterKnife.bind(this,view);
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = context.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        onWindowAttributesChanged(wl);
        relBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChoosePayTypeListener.onChooseBalance();
                dismiss();
            }
        });
        relWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onChoosePayTypeListener.onChooseWeChat();
                dismiss();
            }
        });
        relAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChoosePayTypeListener.onChooseAli();
                dismiss();
            }
        });

    }

    public interface  OnChoosePayTypeListener{

        void onChooseBalance();
        void onChooseWeChat();
        void onChooseAli();
    }


}
