package com.ecuca.cloudhealth.activity.me;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 评价咨询
 * Created by TuHuaLong on 2018/1/26.
 */

public class OrderEvaluateActivity extends BaseActivity {


    int order_id;
    @BindView(R.id.rb_1)
    RatingBar rb1;
    @BindView(R.id.rb_2)
    RatingBar rb2;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_sub)
    TextView tvSub;

    @Override
    protected void setContentView() {

        order_id = getIntent().getIntExtra("order_id", 0);
        setContentView(R.layout.aty_order_evaluate);
        ButterKnife.bind(this);
        setTitleText("评价咨询");
        showTitleBack();
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {
        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogMessage("提示", "是否提交当前评价？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        commentSale();
                    }
                });

            }
        });

    }

    /**
     * 提交评价
     */
    private void commentSale() {

        int effect = rb1.getNumStars();
        int attitude = rb2.getNumStars();


        String msg = etContent.getText().toString().trim();

        Map<String, String> m = new HashMap<>();
        m.put("order_id", String.valueOf(order_id));
        m.put("effect", effect + "");
        m.put("attitude", attitude + "");
        m.put("suggest", msg);

        HttpUtils.getInstance().post(m, "doctor/evaluate_logs", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        Intent intent=new Intent();
                        setResult(6002,intent);

                        finish();
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("评价失败");
                }
            }
        });
    }



}
