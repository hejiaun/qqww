package com.example.module_practice.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.module_practice.R;
import com.example.module_practice.adapter.UploadAccompanimentAdapter;
import com.example.module_practice.presenter.UploadAccompanimentPresenter;
import com.example.module_practice.view_interface.IUploadAccompanimentView;

import example.common_base.base.BaseFragment;
import example.common_base.entity.UserEntity;

import java.util.ArrayList;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  上传伴奏到声斗士
 */
public class UploadAccompanimentFragment extends BaseFragment<UploadAccompanimentPresenter> implements IUploadAccompanimentView,View.OnClickListener {
    ImageView ivMenu;
    RecyclerView rcv;
    private ArrayList entities;
    private UploadAccompanimentAdapter uploadAccompanimentAdapter;
    private PopupMenu popupMenu;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        ivMenu = view.findViewById(R.id.ivMenu);
        rcv = view.findViewById(R.id.rcv);
        ivMenu.setOnClickListener(this);
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public UploadAccompanimentPresenter createPresenter() {
        return new UploadAccompanimentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_down_accompaniment;
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        entities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entities.add(new UserEntity());
        }
        uploadAccompanimentAdapter = new UploadAccompanimentAdapter(R.layout.item_upload_song, entities);
        popupMenu = new PopupMenu(getContext(), ivMenu);

        popupMenu.inflate(R.menu.menu_loacl_accompaniment);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(uploadAccompanimentAdapter);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int viewId=item.getItemId();
                if (viewId == R.id.scan) {//扫描本地文件
                    Toast.makeText(getContext(), "scan", Toast.LENGTH_SHORT).show();
                } else if (viewId == R.id.sort) {//排序
                    Toast.makeText(getContext(), "sort", Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });
    }


    /**
     * 点击事件的监听
     * @param v 被点击的控件
     */
    @Override
    public void onClick(View v) {
        int viewId=view.getId();
        if (viewId == R.id.ivMenu) {
            popupMenu.show();

        }
    }
}
