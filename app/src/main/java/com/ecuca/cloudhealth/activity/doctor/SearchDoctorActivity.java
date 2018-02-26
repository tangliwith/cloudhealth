package com.ecuca.cloudhealth.activity.doctor;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/24.
 */

public class SearchDoctorActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.otv_do_search)
    TextView otvDoSearch;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.list_view_hot)
    RecyclerView listViewHot;
    @BindView(R.id.lin_search_hot)
    LinearLayout linSearchHot;
    @BindView(R.id.list_view_history)
    RecyclerView listViewHistory;
    @BindView(R.id.lin_search_history)
    LinearLayout linSearchHistory;
    @BindView(R.id.lin_top)
    LinearLayout linTop;


    List<String> hotList = new ArrayList<>();
    HotAdapter hotAdapter;


    List<String> hisList = new ArrayList<>();
    HisAdapter hisAdapter;


    SearchAdapter searchAdapter;
    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_search_doctor);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("搜索");
    }

    @Override
    protected void initUI() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false);
        listViewHot.setLayoutManager(gridLayoutManager);


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false);
        listViewHistory.setLayoutManager(gridLayoutManager2);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);
        listView.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {

        hotList.add("感冒");
        hotList.add("肠炎");
        hotList.add("胃疼");
        hotList.add("风湿");
        hotAdapter = new HotAdapter(hotList);
        listViewHot.setAdapter(hotAdapter);

        hisList.add("李医生");
        hisList.add("张医生");
        hisList.add("孙医生");
        hisList.add("赵医生");
        hisAdapter = new HisAdapter(hisList);
        listViewHistory.setAdapter(hisAdapter);


        searchAdapter=new SearchAdapter();
        listView.setAdapter(searchAdapter);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                Log.e("Test", "search:" + editable.toString());
                if (TextUtils.isEmpty(editable.toString())) {
                    linTop.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);


                } else {
                    linTop.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    /**
     * 热门
     */
    class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {


        List<String> hotList;

        public HotAdapter(List<String> hotList) {
            this.hotList = hotList;
        }

        @Override
        public HotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_str, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final HotAdapter.ViewHolder holder, int position) {

            holder.tvText.setText(hotList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    etSearch.setText(holder.tvText.getText().toString().trim());

                }
            });
        }

        @Override
        public int getItemCount() {
            return hotList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_text)
            TextView tvText;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 热门
     */
    class HisAdapter extends RecyclerView.Adapter<HisAdapter.ViewHolder> {


        List<String> hotList;

        public HisAdapter(List<String> hotList) {
            this.hotList = hotList;
        }

        @Override
        public HisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_str, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(HisAdapter.ViewHolder holder, int position) {

            holder.tvText.setText(hotList.get(position));
        }

        @Override
        public int getItemCount() {
            return hotList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_text)
            TextView tvText;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    class SearchAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultation, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 11;
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.img_head)
            CircleImageView imgHead;
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_level)
            TextView tvLevel;
            @BindView(R.id.tv_content)
            TextView tvContent;
            @BindView(R.id.tv_skill)
            TextView tvSkill;
            @BindView(R.id.tv_counselling_num)
            TextView tvCounsellingNum;
            @BindView(R.id.tv_evaluate_num)
            TextView tvEvaluateNum;
            @BindView(R.id.img_btn_1)
            ImageView imgBtn1;
            @BindView(R.id.tv_btn_1)
            TextView tvBtn1;
            @BindView(R.id.lin_btn1)
            LinearLayout linBtn1;
            @BindView(R.id.img_btn_2)
            ImageView imgBtn2;
            @BindView(R.id.tv_btn_2)
            TextView tvBtn2;
            @BindView(R.id.lin_btn2)
            LinearLayout linBtn2;
            @BindView(R.id.img_btn_3)
            ImageView imgBtn3;
            @BindView(R.id.tv_btn_3)
            TextView tvBtn3;
            @BindView(R.id.lin_btn_3)
            LinearLayout linBtn3;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
