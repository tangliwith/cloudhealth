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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class ChooseConsultationTypeDialog extends Dialog {


    @BindView(R.id.rb_type_1)
    RadioButton rbType1;
    @BindView(R.id.rb_type_2)
    RadioButton rbType2;
    @BindView(R.id.rb_type_3)
    RadioButton rbType3;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.rg_group)
    RadioGroup radioGroup;

    OnChooseConsultationTypeDialogListener chooseConsultationTypeDialogListener;

    public ChooseConsultationTypeDialog(@NonNull Activity context) {
        super(context);
        initView(context);
    }

    public ChooseConsultationTypeDialog(@NonNull Activity context, @StyleRes int themeResId, OnChooseConsultationTypeDialogListener chooseConsultationTypeDialogListener) {
        super(context, themeResId);
        this.chooseConsultationTypeDialogListener = chooseConsultationTypeDialogListener;
        initView(context);
    }

    protected ChooseConsultationTypeDialog(@NonNull Activity context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(final Activity context) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.dia_choose_consultation_type);
//        ButterKnife.bind(this);
//        Window window = this.getWindow();
//        window.setWindowAnimations(R.style.AlertChooserAnimation);
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.height = MyApplication.mHeight - 1;
//        params.gravity = Gravity.CENTER;
//        window.setAttributes(params);
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        window.setBackgroundDrawable(new ColorDrawable());
//
//        setCanceledOnTouchOutside(false);


        View view = getLayoutInflater().inflate(R.layout.dia_choose_consultation_type,
                null);
        ButterKnife.bind(this,view);
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.color.app_red);
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
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int checkID = radioGroup.getCheckedRadioButtonId();
                if (checkID == rbType1.getId()) {
                    chooseConsultationTypeDialogListener.onClick(1);
                    dismiss();
                } else if (checkID == rbType2.getId()) {
                    chooseConsultationTypeDialogListener.onClick(2);
                    dismiss();
                } else if (checkID == rbType3.getId()) {
                    chooseConsultationTypeDialogListener.onClick(3);
                    dismiss();
                }

            }
        });

    }


    public interface OnChooseConsultationTypeDialogListener {
        //1,2,3
        void onClick(int type);
    }

}
