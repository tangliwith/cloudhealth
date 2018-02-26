package com.ecuca.cloudhealth.Utils;

import android.util.Log;

import com.ecuca.cloudhealth.MyApplication;

/**
 * Created by Theron on 2017/10/11.
 */

public class LogUtil {

    /**
     * @param tag
     * @param content
     */
    public static  void e(String tag,String content){
        if(MyApplication.is_debug)
        Log.e(tag,content);
    }
    /**
     * @param tag
     * @param content
     */
    public static  void d(String tag,String content){
        if(MyApplication.is_debug)
            Log.d(tag,content);
    }
}
