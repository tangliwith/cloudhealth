package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.ecuca.cloudhealth.Entity.LoginEntity;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.adapter.FragmentTabAdapter;
import com.ecuca.cloudhealth.fragment.DoctorFragment;
import com.ecuca.cloudhealth.fragment.MeFragment;
import com.ecuca.cloudhealth.fragment.TopicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    private List<Fragment> fragments;
    FragmentTabAdapter tabAdapter;
    RadioGroup mainBottomTabs;

    private LoginEntity.DataBean userBean;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);



        mainBottomTabs = getID(R.id.main_bottom_tabs);
        fragments = new ArrayList<>();

        fragments.add(new DoctorFragment());
        fragments.add(new TopicFragment());
        fragments.add(new MeFragment());

        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.main_content, mainBottomTabs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {

            }
        });
    }

    @Override
    protected void initUI() {


//        Intent intent=new Intent(MainActivity.this,SelectCityActivity.class);
//        MainActivity.this.startActivity(intent);

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
    protected void onResume() {
        super.onResume();

    }


    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                // ToastMessage("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
