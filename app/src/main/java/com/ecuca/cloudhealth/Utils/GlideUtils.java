package com.ecuca.cloudhealth.Utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ecuca.cloudhealth.MyApplication;

/**
 * Created by Administrator on 2016/10/20.
 */
public class GlideUtils {


    public static void LoadImg(ImageView img, String url) {
        if (img == null | url == null) {
            return;
        }
        Glide.with(MyApplication.getContext()).load(url).into(img);
    }
}
