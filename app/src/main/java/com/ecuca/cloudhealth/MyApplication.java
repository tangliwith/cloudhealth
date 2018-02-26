package com.ecuca.cloudhealth;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.ecuca.cloudhealth.Entity.LoginEntity;
import com.ecuca.cloudhealth.Utils.APIWebviewTBS;
import com.ecuca.cloudhealth.Utils.LocationService;
import com.ecuca.cloudhealth.service.MyPushIntentService;
import com.google.gson.Gson;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MyApplication extends Application {
    private static MyApplication instance;
    private SharedPreferences sp;
    public static int mWidth;
    public static int mHeight;
    public static  boolean is_debug=true;
    APIWebviewTBS mAPIWebviewTBS;
    public LocationService locationService;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        locationService = new LocationService(getApplicationContext());
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
        sp = getSharedPreferences("sp_info", MODE_PRIVATE);
        //init demo helper
        mAPIWebviewTBS= APIWebviewTBS.getAPIWebview();
        mAPIWebviewTBS.initTbs(getApplicationContext());
//
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        //sdk开启通知声音
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
//        // sdk关闭通知声音
////		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//        // 通知声音由服务端控制
////		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);
//
////		mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
////		mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//
////        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
//
//
//            //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//
//                Log.e("Test","token:"+deviceToken);
//            }
//
//            public void onFailure(String s, String s1) {
//
//            }
//        });
    }



    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return "";
        }

    }

    public String getSharedPreferences(String name, String deflaut) {
        return sp.getString(name, deflaut);

    }

    public void setSharedPreferences(String name, String values) {
        sp.edit().putString(name, values).commit();

    }


    public static Context getContext() {
        return instance;
    }

    // 构造方法
    // 实例化一次
    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    public LoginEntity.DataBean getUserInfo(){
        LoginEntity.DataBean bean=null;
        try{
            bean=new Gson().fromJson(getSharedPreferences("user_info",""),LoginEntity.DataBean.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 存放用户信息
     * @param bean
     */
    public void saveUserInfo(LoginEntity.DataBean bean){
        setSharedPreferences("user_info","");
        setSharedPreferences("user_info",new Gson().toJson(bean));
    }

    /**
     *  清除用户信息
     */
    public void removeUserInfo(){
        setSharedPreferences("user_info","");
    }

    /**
     * 获取用户是否登陆
     * @return
     */
    public boolean getUserIsLogin(){

        LoginEntity.DataBean bean=getUserInfo();
        if(bean!=null){

            return true;
        }
        else{
            return false;
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    // 获取当前版本的版本号
    public String getVersionName() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    // 获取当前版本的版本号
    public int getVersionCode() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isMobileNO(String mobiles) {

        return match(PHONE_PATTERN, mobiles);
    }

    /**
     * 136.     * 匹配函数
     * 137.     * @param regex
     * 138.     * @param input
     * 139.     * @return
     * 140.
     */
    private static boolean match(String regex, String input) {
        return Pattern.matches(regex, input);
    }

    /**
     * 11.     * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173
     * 12.     *
     **/
    private static final String CHINA_TELECOM_PATTERN = "(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)";

    /**
     * 16.     * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709
     * 17.     *
     **/
    private static final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";

    /**
     * 21.     * 中国移动号码格式验证
     * 22.     * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184
     * 23.     * ,187,188,147,178,1705
     * 24.     *
     * 25.
     **/
    private static final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";

    /**
     * 29.     * 仅手机号格式校验
     * 30.
     */
    private static final String PHONE_PATTERN = new StringBuilder(300).append(CHINA_MOBILE_PATTERN)
            .append("|")
            .append(CHINA_TELECOM_PATTERN)
            .append("|")
            .append(CHINA_UNICOM_PATTERN)
            .toString();
}