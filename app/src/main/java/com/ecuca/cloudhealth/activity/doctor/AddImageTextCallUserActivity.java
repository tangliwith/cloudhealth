package com.ecuca.cloudhealth.activity.doctor;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.ecuca.cloudhealth.Entity.AddContactEntity;
import com.ecuca.cloudhealth.Entity.AllAddressEntity;
import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GetJsonDataUtil;
import com.ecuca.cloudhealth.View.CheckedImageView;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.me.UserInfoActivity;
import com.ecuca.cloudhealth.dialog.photodialog.AlertChooser;
import com.google.gson.Gson;
import com.yalantis.ucrop.UCrop;

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
 * Created by tuhualong on 2017/12/28.
 */

public class AddImageTextCallUserActivity extends BaseActivity {

    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.lin_choose_id_card_type)
    LinearLayout linChooseIdCardType;
    @BindView(R.id.et_id_card)
    EditText etIdCard;
    @BindView(R.id.rb_sex_man)
    RadioButton rbSexMan;
    @BindView(R.id.rb_sex_woman)
    RadioButton rbSexWoman;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.lin_choose_birthday)
    LinearLayout linChooseBirthday;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rel_choose_address)
    RelativeLayout relChooseAddress;
    @BindView(R.id.et_address_details)
    EditText etAddressDetails;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_msg_code)
    EditText etMsgCode;
    @BindView(R.id.sw_is_default)
    Switch swIsDefault;
    @BindView(R.id.tv_id_card_type)
    TextView tvIdCardType;
    @BindView(R.id.lin_choose_avatar)
    LinearLayout linChooseAvatar;

    private File testFile;
    private Uri myUri;
    int type = 1;
    TimeSelector timeSelector;
    private List<AllAddressEntity.DataBean> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<List<List<String>>> options3Items = new ArrayList<>();

    int province_id;
    int city_id;
    int county_id;

    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_add_image_text_call_user);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("添加新档案");
    }

    @Override
    protected void initUI() {
        Calendar a = Calendar.getInstance();
        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                time = time.substring(0, time.indexOf(" "));
                tvBirthday.setText(time);
            }
        }, String.valueOf(a.get(Calendar.YEAR) - 100) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE) + 1) + " 10:34", String.valueOf(a.get(Calendar.YEAR)) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE)) + " 17:34");
        timeSelector.setMode(TimeSelector.MODE.YMD);//
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initJsonData();
            }
        }).start();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

        linChooseIdCardType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showIdCardType();
            }
        });

        linChooseBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSelector.show();
            }
        });

        relChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (options1Items.size() == 0) {

                    return;
                }

                OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddImageTextCallUserActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
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

        linChooseAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayWindow();
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subData();
            }
        });
    }


    /**
     * 选择证件类型
     */
    private void showIdCardType() {

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
        alertChooser.addItem("大陆身份证", new AlertChooser.OnItemClickListener() {

            @Override
            public void OnItemClick(AlertChooser chooser) {
                // TODO Auto-generated method stub
                type = 1;
                tvIdCardType.setText("大陆身份证");
                chooser.dismiss();
            }
        });
        alertChooser.addItem("军官证", new AlertChooser.OnItemClickListener() {

            @Override
            public void OnItemClick(AlertChooser chooser) {
                // TODO Auto-generated method stub
                type = 2;
                tvIdCardType.setText("军官证");
                chooser.dismiss();
            }
        });
        alertChooser.show();
    }

    private void initJsonData() {//解析数据

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
    }


    /**
     * 提交数据
     */
    private void subData() {


        String nick_name = etNickName.getText().toString().trim();
        if (TextUtils.isEmpty(nick_name)) {
            ToastMessage("请输入真实姓名");
            return;
        }
        String card_no = etIdCard.getText().toString().trim();
        if (TextUtils.isEmpty(card_no)) {
            ToastMessage("请输入证件号");
            return;
        }
        String birthday = tvBirthday.getText().toString().trim();
        if ("请选择出生年月".equals(birthday)) {
            ToastMessage("请输入出生年月");
            return;
        }
        String address = etAddressDetails.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            ToastMessage("请输入详细地址");
            return;

        }
        String mobile = etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastMessage("请输入联系电话");
            return;
        }
        String code = etMsgCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            ToastMessage("请输入手机验证码");
            return;
        }

        Map<String, String> m = new HashMap<>();
        m.put("nick_name", nick_name);
        m.put("type", type + "");
        m.put("card_no", card_no);
        m.put("sex", rbSexMan.isChecked() ? "1" : "2");
        m.put("birthday", birthday);
        m.put("province", province_id + "");
        m.put("city", city_id + "");
        m.put("area", county_id + "");
        m.put("address", address);
        m.put("mobile", mobile);
        m.put("code", code);
        m.put("default", swIsDefault.isChecked() ? "1" : "0");
        HashMap<String, File> file = new HashMap<>();
        if (testFile != null)
            file.put("avatar_file", testFile);


        HttpUtils.getInstance().postFile(m, file, "user/add_new_file", new AllCallback<AddContactEntity>(AddContactEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(AddContactEntity response) {
                if (response != null) {
                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        Intent intent = new Intent();
                        setResult(5002,intent);
                        finish();
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("保存失败");
                }

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
                    testFile = getFileByUri(resultUri);
                    imgHead.setImageURI(resultUri);

                    break;
            }

        }


    }

    private void displayWindow() {
        //选择头像
        testFile = new File(getCacheDir(), "avatarFile" + System.currentTimeMillis() / 1000 + ".png");
        myUri = Uri.fromFile(testFile);

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
}
