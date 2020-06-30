package com.example.module_pk.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.module_pk.R;
import com.example.module_pk.adapter.SearchSongInPkActivityListAdapter;
import com.example.module_pk.presenter.SearchSongInPkActivityPresenter;
import com.example.module_pk.view_interface.ISearchSongInPkActivityView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import cn.vfighter.songlib.bean.Accompaniment;
import example.common_base.adapter.AutoCompleteTextViewAdapter;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  匹配选择歌曲Activity
 */
public class SearchSongInPkActivity extends PkBaseActivity<SearchSongInPkActivityPresenter> implements
        ISearchSongInPkActivityView,
        AdapterView.OnItemClickListener,
        TextWatcher {
    AutoCompleteTextView atv;
    TextView tvCancel;
    TextView tvFound;
    private SearchSongInPkActivityListAdapter adapter = null;
    private AutoCompleteTextViewAdapter autoCompleteTextViewAdapter;
    private RecyclerView rcv;

    @Override
    public void initView() {
        super.initView();
        tvCancel = findViewById(R.id.tv_cancel);
        rcv = findViewById(R.id.rcv);
        tvFound = findViewById(R.id.tvFound);
        atv = findViewById(R.id.atv);
        tvCancel.setOnClickListener(this);

    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        adapter = new SearchSongInPkActivityListAdapter(R.layout.item_song_practice, new ArrayList<Accompaniment>());

        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        autoCompleteTextViewAdapter = new AutoCompleteTextViewAdapter(this);

        tvFound.setText("共发现" + adapter.getData().size() + "个结果");
        atv.setAdapter(autoCompleteTextViewAdapter);
        atv.setThreshold(1);

        atv.addTextChangedListener(this);
        atv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //删除"搜索："
                    atv.setText(atv.getText().delete(0, 3));
                    //光标移到文本内容末尾
                    atv.setSelection(atv.getText().length());
                }
                getPresenter().search(atv.getText().toString().trim());
            }
        });
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_search_song;
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public SearchSongInPkActivityPresenter createPresenter() {
        return new SearchSongInPkActivityPresenter(this);
    }

    /**
     * 列表Item点击事件监听
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        //回传歌曲
        intent.putExtra("songName", adapter.getItem(position).getAccompanimentName());
        setResult(2, intent);
        finish();
    }

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

    /**
     * 点击事件控件
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_cancel) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }

    @Override
    public SearchSongInPkActivityListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtil.getInstance().finishActivity(this);
    }
}
