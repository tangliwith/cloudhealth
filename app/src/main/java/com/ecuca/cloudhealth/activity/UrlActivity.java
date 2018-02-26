package com.ecuca.cloudhealth.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.ProgressBar;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.APIWebviewTBS;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Administrator on 2016/10/27.
 */
public class UrlActivity extends BaseActivity {


    private com.tencent.smtt.sdk.WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void setContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.aty_url);
        showTitleBack();
        String title = getIntent().getStringExtra("title");
        if (title == null) {
            setTitleText("");
        } else {
            setTitleText(title);
        }
        APIWebviewTBS.getAPIWebview().initTBSActivity(this);   //api借口注册二次封装
        loadWebvieUrl("https://m.1688.com/");

    }

    private void loadWebvieUrl(String url) {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.aty_url_webview);
        com.tencent.smtt.sdk.WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       webView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        webView.loadUrl(url);
//        webView.loadData(url, "text/html; charset=UTF-8", null);
        webView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {
             @Override
               public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {
                 view.loadUrl(url);
                  return true;
              }

            @Override
            public void onReceivedError(com.tencent.smtt.sdk.WebView var1, int var2, String var3, String var4) {
                progressBar.setVisibility(View.GONE);
                ToastMessage("网页加载失败");
            }

            //重写此方法可以让webview处理https请求。
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.java_obj.getSource('<head>'+" +
                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                super.onPageFinished(view, url);
            }




        });


        //进度条
        webView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {
            @Override
            public void onProgressChanged(com.tencent.smtt.sdk.WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
        });
    }
    /**
     * 逻辑处理
     * @author linzewu
     */
    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            Log.d("html=", html);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  确保注销配置能够被释放
        if (this.webView != null) {
            webView.destroy();
        }
    }
    public String convertToString(InputStream inputStream) {
        StringBuffer string = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                string.append(line + "\n");
            }
        } catch (IOException e) {
        }
        return string.toString();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
        webView.pauseTimers();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.resumeTimers();
        webView.onResume();
    }


}
