package com.ecuca.cloudhealth.activity.me;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.ContactFullInfoEntity;
import com.ecuca.cloudhealth.Entity.UserInfoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.dialog.photodialog.AlertChooser;
import com.yalantis.ucrop.UCrop;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class ContactFullInfoActivity extends BaseActivity {
    @BindView(R.id.et_medical_record)
    EditText etMedicalRecord;
    @BindView(R.id.et_allergy_record)
    EditText etAllergyRecord;
    @BindView(R.id.img_test_1)
    ImageView imgTest1;
    @BindView(R.id.rel_choose_test_1)
    RelativeLayout relChooseTest1;
    @BindView(R.id.img_test_2)
    ImageView imgTest2;
    @BindView(R.id.rel_choose_test_2)
    RelativeLayout relChooseTest2;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.tv_save)
    TextView tvSave;



    private File testFile, test1File, test2File;
    private Uri myUri;
    int imageChooseMode; //图片选择模式
    int uid;
    @Override
    protected void setContentView() {

        uid=getIntent().getIntExtra("uid",0);
        setContentView(R.layout.aty_contact_full_info);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("完善信息");
    }

    @Override
    protected void initUI() {

        getContactInfo();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {
        relChooseTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooseMode = 2;
                displayWindow();
            }
        });
        relChooseTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooseMode = 3;
                displayWindow();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subUserInfo();
            }
        });
    }

    private void displayWindow() {
        //选择头像
        if (imageChooseMode == 1) {
            testFile = new File(getCacheDir(), "avatarFile" + System.currentTimeMillis() / 1000 + ".png");
            myUri = Uri.fromFile(testFile);
        } else if (imageChooseMode == 2) {
            testFile = new File(getCacheDir(), "test1File" + System.currentTimeMillis() / 1000 + ".png");
            myUri = Uri.fromFile(testFile);
        } else if (imageChooseMode == 3) {
            testFile = new File(getCacheDir(), "test2File" + System.currentTimeMillis() / 1000 + ".png");
            myUri = Uri.fromFile(testFile);
        } else {

            return;
        }

        AlertChooser.Builder alertChooser = new AlertChooser.Builder(
                getActivity());
        alertChooser.setNegativeItem("取消",
                new AlertChooser.OnItemClickListener() {

                    @Override
                    public void OnItemClick(AlertChooser chooser) {
                        // TODO Auto-generated method stub
                        chooser.dismiss();
                    }
                });
        alertChooser.addItem("拍照", new AlertChooser.OnItemClickListener() {

            @Override
            public void OnItemClick(AlertChooser chooser) {
                // TODO Auto-generated method stub
                openCarmera();
                chooser.dismiss();
            }
        });
        alertChooser.addItem("相册", new AlertChooser.OnItemClickListener() {

            @Override
            public void OnItemClick(AlertChooser chooser) {
                // TODO Auto-generated method stub
                openGallery();
                chooser.dismiss();
            }
        });
        alertChooser.show();
    }

    Uri photoUri;

    /**
     *
     */
    protected void openCarmera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, filename);

        photoUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        this.startActivityForResult(intent, 1004);
    }

    /**
     *
     */
    protected void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        this.startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    if (intent != null) {
                        Uri uri = intent.getData();
                        Log.e("Test", "uri:" + uri);
                        if (uri != null) {
                            UCrop.of(uri, myUri)
                                    .withAspectRatio(1, 1)
                                    .withMaxResultSize(1000, 1000)
                                    .start(this);
                        }
                    }
                    break;
                case 1004:
                    if (intent != null) {
                        Uri uri = intent.getData();
                        Log.e("Test", "uri:" + uri);
                        if (uri != null) {
                            UCrop.of(uri, myUri)
                                    .withAspectRatio(1, 1)
                                    .withMaxResultSize(1000, 1000)
                                    .start(this);

                        }
                    } else {

                        UCrop.of(photoUri, myUri)
                                .withAspectRatio(1, 1)
                                .withMaxResultSize(1000, 1000)
                                .start(this);
                    }
                    break;
                case UCrop.REQUEST_CROP:

                    Uri resultUri = UCrop.getOutput(intent);



                     if (imageChooseMode == 2) {
                        test1File=getFileByUri(resultUri);
                        imgTest1.setImageURI(resultUri);
                    } else if (imageChooseMode == 3) {
                        test2File=getFileByUri(resultUri);
                        imgTest2.setImageURI(resultUri);
                    }
                    break;
            }

        }

    }

    /**
     * @param uri
     * @return
     */
    public File getFileByUri(Uri uri) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);

                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {

            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {

        }
        return null;
    }

    /**
     * 获取用户信息
     */
    private void getContactInfo() {

        Map<String, String> m = new HashMap<>();
        m.put("uid",uid+"");

        HttpUtils.getInstance().post(m, "user/get_evpi", new AllCallback<ContactFullInfoEntity>(ContactFullInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
                finish();

            }

            @Override
            public void onResponse(ContactFullInfoEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {

                        setUserInfo(response.getData());
                    } else {
                        ToastMessage(response.getMsg());
                        finish();
                    }
                } else {
                    ToastMessage("数据异常");
                    finish();
                }
            }
        });

    }

    /**
     * 设置用户信息
     *
     * @param bean
     */
    private void setUserInfo(ContactFullInfoEntity.DataBean bean) {


        etMedicalRecord.setText(bean.getMedical_history() == null ? "" : bean.getMedical_history());
        etAllergyRecord.setText(bean.getAllergic_history() == null ? "" : bean.getAllergic_history());
        GlideUtils.LoadImg(imgTest1, bean.getLaboratory_sheet_url());
        GlideUtils.LoadImg(imgTest2, bean.getMedical_imaging_url());
        etRemarks.setText(bean.getMessage() == null ? "" : bean.getMessage());


    }


    /**
     * 提交用户信息
     */
    private void subUserInfo() {




        Map<String, String> m = new HashMap<>();
        m.put("uid",uid+"");


        if(TextUtils.isEmpty(etMedicalRecord.getText().toString().trim())){
            ToastMessage("请输入病史");
            return;
        }


        m.put("medical_history", etMedicalRecord.getText().toString().trim());

        if(TextUtils.isEmpty(etAllergyRecord.getText().toString().trim())){
            ToastMessage("请输入过敏史");
            return;
        }
        m.put("allergic_history", etAllergyRecord.getText().toString().trim());

        m.put("message", etRemarks.getText().toString().trim());


        HashMap<String, File> file = new HashMap<>();

        if(test1File!=null)
            file.put("laboratory_sheet",test1File);

        if(test2File!=null)
            file.put("medical_imaging",test2File);



        HttpUtils.getInstance().postFile(m, file, "user/evpi", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {
                if(response!=null){
                    if(response.getCode()==200){
                        ToastMessage(response.getMsg());

                         Intent intent=new Intent();
                        setResult(5001,intent);
                        finish();
                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                }
                else{
                    ToastMessage("保存失败");
                }

            }
        });




    }

}
