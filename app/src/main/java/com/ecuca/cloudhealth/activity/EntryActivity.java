package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.ecuca.cloudhealth.R;


/**
 * Created by Administrator on 2016/11/7.
 */
public class EntryActivity extends BaseActivity {

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1001) {
//                if (MyApplication.getInstance().getMemberId().isEmpty() || MyApplication.getInstance().getMemberId().equals("-1")) {
//                    Intent intent = new Intent(EntryActivity.this, LoginActivity.class);
//                    EntryActivity.this.startActivity(intent);
//                    EntryActivity.this.finish();
//                } else {
                    Intent intent = new Intent(EntryActivity.this, MainActivity.class);
                    EntryActivity.this.startActivity(intent);
                    EntryActivity.this.finish();
  //              }
            }
        }
    };

    @Override
    protected void setContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.aty_entry);


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1500);
                    handler.sendEmptyMessage(1001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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

    }
}
