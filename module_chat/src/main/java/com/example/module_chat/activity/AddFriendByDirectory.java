package com.example.module_chat.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_chat.R;
import com.example.module_chat.adapter.AddFriendByDirectoryAdapter;
import com.example.module_chat.entity.DirectoryFriendEntity;
import com.example.module_chat.presenter.AddFriendByDirectoryPresenter;
import com.example.module_chat.view_interface.IAddFriendByDirectoryView;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 通讯录好友Activity
 */
public class AddFriendByDirectory extends BaseActivity<AddFriendByDirectoryPresenter> implements IAddFriendByDirectoryView {

    private TextView tvTitle;
    private RecyclerView rcv;
    private AddFriendByDirectoryAdapter adapter;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        rcv = findViewById(R.id.rcv);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tvFollowAll).setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("通讯录好友");

        //--配置列表---//
        adapter = new AddFriendByDirectoryAdapter(R.layout.item_directory, new ArrayList<DirectoryFriendEntity>());
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new MyItemChildClickListener());
        //--关注列表项更新动画---//
        ((DefaultItemAnimator) rcv.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        adapter.addData(new DirectoryFriendEntity(DirectoryFriendEntity.STATE_FOLLOW));
        adapter.addData(new DirectoryFriendEntity(DirectoryFriendEntity.STATE_FOLLOW_EACHOTHER));
        adapter.addData(new DirectoryFriendEntity(DirectoryFriendEntity.STATE_UNFOLLOW));
    }

    /**
     * 加载布局
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_add_friend_by_directory;
    }

    /**
     * 创建presenter
     * @return
     */
    @Override
    public AddFriendByDirectoryPresenter createPresenter() {
        return new AddFriendByDirectoryPresenter(this);
    }

    /**
     * 点击事件的监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (id == R.id.tvFollowAll) {
            followAll();
        }
    }

    private void followAll() {
        ArrayList<DirectoryFriendEntity> data = (ArrayList<DirectoryFriendEntity>) adapter.getData();
        for (int i = 0; i < data.size(); i++) {
            DirectoryFriendEntity entity = data.get(i);
            if (entity.getFollowState() == DirectoryFriendEntity.STATE_UNFOLLOW) {
                entity.setFollowState(DirectoryFriendEntity.STATE_FOLLOW);
                adapter.notifyItemChanged(i);
            }
        }
    }

    /**
     * 列表项子控件点击事件监听器
     */
    private class MyItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            DirectoryFriendEntity entity = (DirectoryFriendEntity) adapter.getData().get(position);
            if (entity.getFollowState() == DirectoryFriendEntity.STATE_UNFOLLOW) {
                entity.setFollowState(DirectoryFriendEntity.STATE_FOLLOW);
            } else {
                entity.setFollowState(DirectoryFriendEntity.STATE_UNFOLLOW);
            }
            adapter.notifyItemChanged(position);
        }
    }
}
