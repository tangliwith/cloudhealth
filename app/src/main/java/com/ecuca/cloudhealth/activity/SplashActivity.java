package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;


/**
 * Created by Theron on 2017/11/16.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.aty_splash);
        doStartActivity();
    }


    /**
     * 跳转
     */
    private void doStartActivity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                if (TextUtils.isEmpty(MyApplication.getInstance().getSharedPreferences("is_first", ""))) {
//                    Intent intent = new Intent(SplashActivity.this, WelcomeGuideActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else{
                    if (MyApplication.getInstance().getUserIsLogin()) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        finish();
                    } else {

                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
          //      }


            }
        }).start();
    }


}
