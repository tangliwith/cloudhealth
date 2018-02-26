package com.ecuca.cloudhealth.activity.me;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.ecuca.cloudhealth.Entity.AllAddressEntity;
import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.UserInfoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.Utils.GetJsonDataUtil;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.dialog.photodialog.AlertChooser;
import com.google.gson.Gson;
import com.yalantis.ucrop.UCrop;
import com.zhy.http.okhttp.OkHttpUtils;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2018/1/11.
 */

public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.rel_choose_head)
    RelativeLayout relChooseHead;
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.rb_sex_man)
    RadioButton rbSexMan;
    @BindView(R.id.rb_sex_woman)
    RadioButton rbSexWoman;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.rel_choose_birthday)
    RelativeLayout relChooseBirthday;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rel_choose_address)
    RelativeLayout relChooseAddress;
    @BindView(R.id.et_address_details)
    EditText etAddressDetails;
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

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    TimeSelector timeSelector;
    private File testFile,avatarFile, test1File, test2File;
    private Uri myUri;
    int imageChooseMode; //图片选择模式

    private List<AllAddressEntity.DataBean> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<List<List<String>>> options3Items = new ArrayList<>();

    int province_id;
    int city_id;
    int county_id;

    Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000) {
                showProgressDialog("加载中");
            } else if (msg.what == 1001) {
                disProgressDialog();
            }
        }
    };

    boolean is_full;
    @Override
    protected void setContentView() {
        is_full=getIntent().getBooleanExtra("is_full",false);
        setContentView(R.layout.aty_user_info);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("个人信息");
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                initJsonData();
            }
        }).start();
        Calendar a = Calendar.getInstance();
        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                time = time.substring(0, time.indexOf(" "));
                tvBirthday.setText(time);
            }
        }, String.valueOf(a.get(Calendar.YEAR) - 80) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE) + 1) + " 10:34", String.valueOf(a.get(Calendar.YEAR)) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE)) + " 17:34");
        timeSelector.setMode(TimeSelector.MODE.YMD);//

        getUserInfo();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        relChooseBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeSelector.show();
            }
        });


        relChooseHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooseMode = 1;
                displayWindow();
            }
        });
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
        relChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (options1Items.size() == 0) {

                    return;
                }

                OptionsPickerView pvOptions = new OptionsPickerView.Builder(UserInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getName() + " "
                                + options2Items.get(options1).get(option2) + " "
                                + options3Items.get(options1).get(option2).get(options3);
                        tvAddress.setText(tx);
                        province_id = options1Items.get(options1).getId();
                        city_id = options1Items.get(options1).getCitys().get(option2).getId();
                        county_id = options1Items.get(options1).getCitys().get(option2).getCountys().get(options3).getId();
                    }
                })
                        .setTitleText("地区选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .build();
                pvOptions.setPicker(options1Items, options2Items, options3Items);//添加数据源
                pvOptions.show();
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subUserInfo();
            }
        });
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



                    if (imageChooseMode == 1) {
                        avatarFile=getFileByUri(resultUri);
                        imgHead.setImageURI(resultUri);
                    } else if (imageChooseMode == 2) {
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

    private void initJsonData() {//解析数据
        handler.sendEmptyMessage(1000);
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "all_address_json.json");//获取assets目录下的json文件数据

        AllAddressEntity entity = new Gson().fromJson(JsonData, AllAddressEntity.class);//builder读取了JSON中的数据。

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = entity.getData();

        for (int i = 0; i < entity.getData().size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<List<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < entity.getData().get(i).getCitys().size(); c++) {//遍历该省份的所有城市
                String CityName = entity.getData().get(i).getCitys().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (entity.getData().get(i).getCitys().get(c).getCountys() == null
                        || entity.getData().get(i).getCitys().get(c).getCountys().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < entity.getData().get(i).getCitys().get(c).getCountys().size(); d++) {//该城市对应地区所有数据
                        String AreaName = entity.getData().get(i).getCitys().get(c).getCountys().get(d).getName();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        handler.sendEmptyMessage(1001);

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
    private void getUserInfo() {

        Map<String, String> m = new HashMap<>();

        HttpUtils.getInstance().post(m, "user/get_user_info", new AllCallback<UserInfoEntity>(UserInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
                finish();

            }

            @Override
            public void onResponse(UserInfoEntity response) {

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
    private void setUserInfo(UserInfoEntity.DataBean bean) {

        GlideUtils.LoadImg(imgHead, bean.getAvatar_url());
        etNickName.setText(bean.getNick_name() == null ? "" : bean.getNick_name());
        if (bean.getSex() == 1) {
            rbSexMan.setChecked(true);
            rbSexWoman.setChecked(false);
        } else {
            rbSexMan.setChecked(false);
            rbSexWoman.setChecked(true);
        }
        tvBirthday.setText(DateUtils.dataToYmd(bean.getBirthday() == null ? "0" : bean.getBirthday()));




        String address = "";
        if (bean.getProvince() != null) {
            province_id =bean.getProvince().getId();
            address += bean.getProvince().getName_chs() + " ";
        }
        if (bean.getCity() != null) {
            city_id =bean.getCity().getId();
            address += bean.getCity().getName_chs() + " ";
        }
        if (bean.getArea() != null) {
            county_id =bean.getArea().getId();
            address += bean.getArea().getName_chs() + " ";
        }
        tvAddress.setText(address);
        etAddressDetails.setText(bean.getAddress() == null ? "" : bean.getAddress());
        etMedicalRecord.setText(bean.getMedical_history() == null ? "" : bean.getMedical_history());
        etAllergyRecord.setText(bean.getAllergic_history() == null ? "" : bean.getAllergic_history());
        GlideUtils.LoadImg(imgTest1, bean.getLaboratory_sheet_url());
        GlideUtils.LoadImg(imgTest2, bean.getMedical_imaging_url());
        etRemarks.setText(bean.getMessage() == null ? "" : bean.getMessage());

        if (!TextUtils.isEmpty(bean.getBirthday())) {
            Calendar a = Calendar.getInstance();
            timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
                @Override
                public void handle(String time) {
                    time = time.substring(0, time.indexOf(" "));
                    tvBirthday.setText(time);
                }
            }, DateUtils.dataToY(String.valueOf(bean.getBirthday())) + "-" + DateUtils.dataToM(String.valueOf(bean.getBirthday())) + "-" + DateUtils.dataToD(String.valueOf(bean.getBirthday())) + " 10:34", String.valueOf(a.get(Calendar.YEAR)) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE)) + " 17:34");
            timeSelector.setMode(TimeSelector.MODE.YMD);//
        }

        scrollView.fullScroll(ScrollView.FOCUS_DOWN);

    }


    /**
     * 提交用户信息
     */
    private void subUserInfo() {




        Map<String, String> m = new HashMap<>();


        m.put("nick_name", etNickName.getText().toString().trim());
        m.put("sex", rbSexMan.isChecked()?"1":"2");
        m.put("birthday", DateUtils.getTime(tvBirthday.getText().toString().trim()+" 00:00:00"));
        m.put("province", province_id+"");
        m.put("city", city_id+"");
        m.put("area", county_id+"");
        m.put("address", etAddressDetails.getText().toString().trim());
        m.put("medical_history", etMedicalRecord.getText().toString().trim());
        m.put("allergic_history", etAllergyRecord.getText().toString().trim());

        m.put("message", etRemarks.getText().toString().trim());


        HashMap<String, File> file = new HashMap<>();
        if(avatarFile!=null)
        file.put("avatar_file",avatarFile);
        if(test1File!=null)
        file.put("laboratory_sheet",test1File);
        if(test2File!=null)
        file.put("medical_imaging",test2File);

        HttpUtils.getInstance().postFile(m, file, "user/update_users_file", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {
                if(response!=null){
                    if(response.getCode()==200){
                        ToastMessage(response.getMsg());
                        if(is_full){
                            finish();
                        }
                        else{
                            getUserInfo();
                        }
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
