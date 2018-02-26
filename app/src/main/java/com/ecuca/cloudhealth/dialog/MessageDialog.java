package com.ecuca.cloudhealth.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class MessageDialog extends Dialog {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_closes)
    TextView tvCloses;
    @BindView(R.id.tv_oks)
    TextView tvOks;



    OnMessageListener onMessageListener;
    public MessageDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MessageDialog(@NonNull Context context, @StyleRes int themeResId,OnMessageListener onMessageListener) {
        super(context, themeResId);
        this.onMessageListener=onMessageListener;
        initView(context);
    }

    protected MessageDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(final Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dia_message);
        ButterKnife.bind(this);
        Window window = this.getWindow();
        window.setWindowAnimations(R.style.AlertChooserAnimation);
        WindowManager.LayoutParams params = window.getAttributes();

        params.height = MyApplication.mHeight-1;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setBackgroundDrawable(new ColorDrawable());
        tvCloses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMessageListener.onClickClose();
                dismiss();
            }
        });
        tvOks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMessageListener.onClickOk();
                dismiss();
            }
        });
    }


    public void show(String content) {
        super.show();
        tvContent.setText(content);
    }


    public void show(String content,String closeText,String okText) {
        super.show();
        tvContent.setText(content);
        tvCloses.setText(closeText);
        tvOks.setText(okText);
    }
    public interface OnMessageListener{
        void onClickOk();
        void onClickClose();
    }
}
