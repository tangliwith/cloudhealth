package com.ecuca.cloudhealth.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * Created by Samson on 16/9/28.
 */
public class UpdateInfoService {

    ProgressDialog progressDialog;
    Handler handler;
    Context context;

    public UpdateInfoService(Context context) {
        this.context = context;
    }


    public void downLoadFile(final String url, final ProgressDialog pDialog, Handler h) {
        progressDialog = pDialog;
        handler = h;
        new Thread() {
            public void run() {
                OkHttpUtils//
                        .get()//
                        .url(url)//
                        .build()
                        //  .execute()
                        .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "qile.apk") {
                            @Override
                            public void onError(Call call, Exception e) {

                            }

                            @Override
                            public void onResponse(File response) {
                                down();
                            }

                            @Override
                            public void inProgress(float progress, long total) {
                                pDialog.setProgress((int) (100 * progress));
                            }
                        });
            }

        }.start();
    }

    void down() {
        handler.post(new Runnable() {
            public void run() {
                progressDialog.cancel();
                update();
            }
        });
    }

    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), "qile.apk")),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
