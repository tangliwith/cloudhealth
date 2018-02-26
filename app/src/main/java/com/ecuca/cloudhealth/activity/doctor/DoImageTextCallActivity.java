package com.ecuca.cloudhealth.activity.doctor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.DoImageTextCallEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideImageLoader;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.adapter.ImagePickerAdapter;
import com.ecuca.cloudhealth.dialog.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/28.
 */

public class DoImageTextCallActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.img_list_view)
    RecyclerView imgListView;
    @BindView(R.id.lin_add_img)
    LinearLayout linAddImg;
    @BindView(R.id.tv_next)
    TextView tvNext;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    ImagePicker imagePicker;
    private int maxImgCount = 8;               //允许选择图片最大数


    private int doctor_id;
    int order_id;
    @Override
    protected void setContentView() {
        order_id = getIntent().getIntExtra("order_id", 0);
        doctor_id = getIntent().getIntExtra("doctor_id", 0);
        setContentView(R.layout.aty_do_image_text_call);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("添加图片");
    }
    protected void showTitleBack() {
        LinearLayout mLinBack = getID(R.id.lin_title_lelft);
        ImageView mImgBack = getID(R.id.img_title_left);
        mImgBack.setVisibility(View.VISIBLE);
        mLinBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        showAlertDialogMessage("提示", "是否要放弃咨询?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
            }
        });

    }
    @Override
    protected void initUI() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        imgListView.setLayoutManager(new GridLayoutManager(this, 4));
        imgListView.setHasFixedSize(true);
        imgListView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);
    }

    @Override
    protected void startFunction() {


        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
                subData();
            }
        });

    }


    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }

        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(DoImageTextCallActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(DoImageTextCallActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 提交数据
     */
    private void subData() {

        Map<String,String> strM=new HashMap<>();
        strM.put("order_id",String.valueOf(order_id));
        HashMap<String, File> m = new HashMap<>();

        String message=etContent.getText().toString().trim();
        if(TextUtils.isEmpty(message)){
            ToastMessage("请输入详细描述");
            return;
        }
        strM.put("message",message);
        if (selImageList.size() <= 0) {
            ToastMessage("请选择图片");
            return;
        }
        if (selImageList != null) {
            for (int i=0;i<selImageList.size();i++) {
                m.put("imgs"+i, new File(selImageList.get(i).path));
            }
        }
        showProgressDialog("提交中...");
        HttpUtils.getInstance().postFile(strM, m, "order/graphic_consultation", new AllCallback<DoImageTextCallEntity>(DoImageTextCallEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                disProgressDialog();
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(DoImageTextCallEntity response) {
                disProgressDialog();
                if (response != null) {

                    if(response.getCode()==200){
                        Intent intent=new Intent(DoImageTextCallActivity.this,DoImageTextCallTwoActivity.class);
                        intent.putExtra("type", 1);
                        intent.putExtra("log_id",response.getData());
                        DoImageTextCallActivity.this.startActivity(intent);
                        finish();
                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("提交失败");
                }
            }
        });
    }
}
