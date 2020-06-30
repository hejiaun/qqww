package com.example.module_home.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_home.R;
import com.example.module_home.adapter.SearchSectionAdapter;
import com.example.module_home.presenter.SearchInHomePresenter;
import com.example.module_home.view_interface.ISearchInHomeView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import example.common_base.adapter.AutoCompleteTextViewAdapter;
import example.common_base.base.BaseActivity;
import example.common_base.entity.MyMultiplyEntity;
import example.common_base.entity.TitleMultiplyentity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.WindowUtil;
import example.common_base.widget.MyFlowViewGroup;

/**
 * Author: HeJiaJun
 * Date:
 * Description:主页搜索功能Activity
 */
public class SearchInHomeActivity extends BaseActivity<SearchInHomePresenter> implements
        ISearchInHomeView {
    private SearchSectionAdapter adapter;
    private String tagText = "全部";
    private AutoCompleteTextViewAdapter autoCompleteTextViewAdapter;
    AutoCompleteTextView atv;
    TextView tvAll;
    TextView tvAccompaniment;
    TextView tvUser;
    TextView tvMatch;
    TextView tvWork;
    MyFlowViewGroup flowViewGroup;
    RecyclerView rcv;
    private TextView tvCancel;

    @Override
    public void initView() {
        super.initView();
        atv = findViewById(R.id.atv);
        tvAll = findViewById(R.id.tv_all);
        tvAccompaniment = findViewById(R.id.tv_accompaniment);
        tvUser = findViewById(R.id.tv_user);
        tvMatch = findViewById(R.id.tv_match);
        tvWork = findViewById(R.id.tv_work);
        flowViewGroup = findViewById(R.id.flowViewGroup);
        rcv = findViewById(R.id.rcv);
        tvCancel = findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvAccompaniment.setOnClickListener(this);
        tvAll.setOnClickListener(this);
        tvMatch.setOnClickListener(this);
        tvWork.setOnClickListener(this);
        tvUser.setOnClickListener(this);
    }
    @Override
    public void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        StatusBarUtil.setLightMode(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onPause() {
        WindowUtil.getInstence().closeSoftInput(this);
        super.onPause();
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_search_home;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SearchInHomePresenter createPresenter() {
        return new SearchInHomePresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        autoCompleteTextViewAdapter = new AutoCompleteTextViewAdapter(this);

        atv.setThreshold(1);
        atv.setAdapter(autoCompleteTextViewAdapter);
        flowViewGroup.setHorizontalSpace(6);
        flowViewGroup.setVerticalSpace(6);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoration());

        rcv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                WindowUtil.getInstence().closeSoftInput(SearchInHomeActivity.this);
                return false;
            }
        });
        atv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                autoCompleteTextViewAdapter.setInputContent(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        atv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //删除"搜索："
                    atv.setText(atv.getText().delete(0, 3));
                    //光标移到文本内容末尾
                    atv.setSelection(atv.getText().length());
                    getPresenter().search(atv.getText().toString());
                }
            }
        });

        getPresenter().getAdapter().setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyMultiplyEntity searchInHomeMultiEntity = (MyMultiplyEntity) adapter.getData().get(position);
                switch (searchInHomeMultiEntity.getItemType()) {//判断实体类型
                    case MyMultiplyEntity.TITLE://点击标题
                        int viewId = view.getId();
                        if (viewId == R.id.tvMore) {//判断布局子控件Id
                            String title = ((TitleMultiplyentity) searchInHomeMultiEntity).getTitle();
                            if (title == "伴奏") {
                                clickTag(tvAccompaniment, getPresenter().getAccompanimentEntities());
                            } else if (title == "用户") {
                                clickTag(tvUser, getPresenter().getUserEntities());
                            } else if (title == "作品") {
                                clickTag(tvWork, getPresenter().getWorkEntities());
                            } else if (title == "比赛") {
                                clickTag(tvMatch, getPresenter().getMatchEntities());
                            }
                        }
                        break;
                    case MyMultiplyEntity.ACCOMPANIENT://点击合唱
                        Toast.makeText(SearchInHomeActivity.this, "ACCOMPANIENT", Toast.LENGTH_SHORT).show();
                        break;
                    case MyMultiplyEntity.USER://点击用户
                        Toast.makeText(SearchInHomeActivity.this, "USER", Toast.LENGTH_SHORT).show();
                        break;
                    case MyMultiplyEntity.MATCH://点击比赛
                        Toast.makeText(SearchInHomeActivity.this, "Title", Toast.LENGTH_SHORT).show();
                        break;
                    case MyMultiplyEntity.WORK://点击作品
                        Toast.makeText(SearchInHomeActivity.this, "WORK", Toast.LENGTH_SHORT).show();
                        break;
                    case MyMultiplyEntity.CHORUS://点击课程
                        Toast.makeText(SearchInHomeActivity.this, "CHORUS", Toast.LENGTH_SHORT).show();
                        break;
                    case MyMultiplyEntity.COURSE://点击课程
                        Toast.makeText(SearchInHomeActivity.this, "COURSE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    /**
     * 点击筛选标签
     *
     * @param tv
     */
    public void clickTag(TextView tv, ArrayList<MyMultiplyEntity> entities) {
        if (!tv.getText().equals(tagText)) {
            resetAllState();
            tv.setTextColor(Color.WHITE);
            tv.setBackground(getResources().getDrawable(R.drawable.shape_blue_round_10dp));
            getPresenter().getAdapter().setNewData(entities);
            tagText = tv.getText().toString();
        }
    }


    /**
     * 重置标签点击状态
     */
    public void resetAllState() {
        tvAll.setBackground(getResources().getDrawable(R.drawable.shape_gray_round_10dp));
        tvAccompaniment.setBackground(getResources().getDrawable(R.drawable.shape_gray_round_10dp));
        tvMatch.setBackground(getResources().getDrawable(R.drawable.shape_gray_round_10dp));
        tvUser.setBackground(getResources().getDrawable(R.drawable.shape_gray_round_10dp));
        tvWork.setBackground(getResources().getDrawable(R.drawable.shape_gray_round_10dp));

        tvAll.setTextColor(getResources().getColor(R.color.black));
        tvAccompaniment.setTextColor(getResources().getColor(R.color.black));
        tvMatch.setTextColor(getResources().getColor(R.color.black));
        tvUser.setTextColor(getResources().getColor(R.color.black));
        tvWork.setTextColor(getResources().getColor(R.color.black));
    }

    /**
     * View层向Presenter提供SearchInHomeActivity
     *
     * @return SearchInHomeActivity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_cancel) {//点击取消
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.tv_all) {//点击全部
            clickTag(tvAll, getPresenter().getAllEntities());
        } else if (viewId == R.id.tv_accompaniment) {//点击伴奏
            clickTag(tvAccompaniment, getPresenter().getAccompanimentEntities());
        } else if (viewId == R.id.tv_user) {//点击用户
            clickTag(tvUser, getPresenter().getUserEntities());
        } else if (viewId == R.id.tv_match) {//点击比赛
            clickTag(tvMatch, getPresenter().getMatchEntities());
        } else if (viewId == R.id.tv_work) {//点击作品
            clickTag(tvWork, getPresenter().getWorkEntities());
        }
    }
}
