package com.ecuca.cloudhealth.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.appmanager.AppManager;
import com.ecuca.cloudhealth.dialog.SpotsDialog;


public abstract class BaseActivity extends AppCompatActivity {
    protected SpotsDialog dialog;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        // 将activity对象添加到程序堆栈
        AppManager.getAppManager().addActivity(this);
        dialog = new SpotsDialog(this);
        setContentView();
        initUI();
        initData();
        initEvent();
        startFunction();
    }

    @Override
    protected void onPause() {
        hideKeyboard();
        super.onPause();

    }

    /**
     * hide
     */
    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                    0);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 将activity对象移出堆栈

        AppManager.getAppManager().finishActivity(this);
    }

    // 设置头像的内容界面
    protected abstract void setContentView();

    // 实例化UI控件
    protected abstract void initUI();

    // 初始化UI数据
    protected abstract void initData();

    // 初始化事件
    protected abstract void initEvent();

    // 开始执行功能
    protected abstract void startFunction();

    /**
     * 弹出消息提示框
     *
     * @param Title
     * @param Message
     * @param onClickListener
     */
    protected void showAlertDialogMessage(String Title, String Message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setPositiveButton("好", onClickListener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 返回该类的对象
     */
    protected AppCompatActivity getActivity() {
        return this;
    }

    /**
     * 返回上一个界面
     */
    protected void goBack() {
        this.finish();
    }

    /**
     * 获取组建的实例
     */
    protected <T extends View> T getID(int id) {
        return (T) findViewById(id);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if(!BackPressed)
//            goBack();
//            return true;
//        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
//            return true;
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//    }

    boolean BackPressed=false;
    public  void isOnBackPressed(boolean b){
        BackPressed=b;
    }

    protected  void ToastMessage(String Message){

        Toast.makeText(getActivity(),Message, Toast.LENGTH_SHORT).show();
    }
    protected  void ToastMessageLong(String Message){

        Toast.makeText(getActivity(),Message, Toast.LENGTH_SHORT).show();
    }
    /**
     * 设置程序标题
     */
    protected void setTitleText(String title) {
        TextView mTvTitle = getID(R.id.app_title);
        mTvTitle.setText(title);
    }


    /**
     * 设置title的中间View
     *
     * @param v
     * @return
     */

    protected View setTitleCenter(View v) {
        LinearLayout mCenterLin = getID(R.id.lin_title_center);
        mCenterLin.removeAllViews();
        mCenterLin.addView(v);
        return mCenterLin;
    }

    /**
     * 返回上级界面
     */
    protected void showTitleBack() {
        LinearLayout mLinBack = getID(R.id.lin_title_lelft);
        ImageView mImgBack = getID(R.id.img_title_left);
        mImgBack.setVisibility(View.VISIBLE);
        mLinBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }
    /**
     * 返回上级界面
     */
    protected void showTitleBack(OnClickListener onClickListener) {
        LinearLayout mLinBack = getID(R.id.lin_title_lelft);
        ImageView mImgBack = getID(R.id.img_title_left);
        mImgBack.setImageResource(R.mipmap.icon_title_back);
        mLinBack.setOnClickListener(onClickListener);
    }


    /**
     * 标题右边图片
     */
    protected void setTitleRightImg(int id, OnClickListener listener) {
        LinearLayout mLinRight = getID(R.id.lin_title_right);
        ImageView mImgRight = getID(R.id.img_title_right);
        mImgRight.setImageResource(id);
        mLinRight.setOnClickListener(listener);

    }

    /**
     * 设置Title右边的文字 图片 点击事件
     *
     * @param str
     * @param image_id
     * @param listener
     */
    protected void setTitleRightText(String str, int image_id,
                                     OnClickListener listener) {
        LinearLayout mLinRight = getID(R.id.lin_title_right);
        TextView mTvRight = getID(R.id.app_title_right);
        ImageView mIvRight = getID(R.id.img_title_right);
        mIvRight.setVisibility(View.VISIBLE);
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(str);
        mIvRight.setBackgroundResource(image_id);
        mLinRight.setOnClickListener(listener);

    }

    /**
     * 设置标题右边文字
     */
    protected void setTitleRightText(String str, OnClickListener listener) {
        TextView mTvRight = getID(R.id.app_title_right);
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(str);
        mTvRight.setOnClickListener(listener);

    }

    /**
     * 设置标题右边文字
     *
     * @param str
     */
    public void setTitleRightTextName(String str) {
        TextView mTvRight = getID(R.id.app_title_right);
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(str);

    }
//    /**
//     * 设置隐藏阴影
//     */
//    public void hideShadow() {
//        View v = getID(R.id.v_shadow);
//        v.setVisibility(View.GONE);
//
//    }
//    /**
//     * 设置显示阴影
//     */
//    public void showShadow() {
//        View v = getID(R.id.v_shadow);
//        v.setVisibility(View.VISIBLE);
//
//    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void showActivity(Class<?> classz, boolean finish) {
        startActivity(new Intent(this, classz));
        if (finish) {
            BaseActivity.this.finish();
        }
    }

    protected ProgressDialog progressDialog;

    protected void showProgressDialog(String str) {
        progressDialog = getProgressDialog();
        progressDialog.setMessage(str);
        progressDialog.show();
    }
    protected void disProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
//                    progressShow = false;
                }
            });
        }
        return progressDialog;
    }
}
