package com.example.module_practice.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


import com.example.module_practice.R;
import com.example.module_practice.presenter.DownloadAccompanimentPresenter;
import com.example.module_practice.view_interface.IDownloadAccompanimentView;

import example.common_base.base.BaseFragment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  下载本地伴奏
 */
public class DownloadAccompanimentFragment extends BaseFragment<DownloadAccompanimentPresenter> implements IDownloadAccompanimentView ,View.OnClickListener{

    ImageView ivMenu;
    RecyclerView rcv;
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
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_down_accompaniment;
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public DownloadAccompanimentPresenter createPresenter() {
        return new DownloadAccompanimentPresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        popupMenu = new PopupMenu(getContext(), ivMenu);

        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(getPresenter().getAdapter());
        popupMenu.inflate(R.menu.menu_loacl_accompaniment);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.scan) {//扫描本地文件
                    Toast.makeText(getContext(), "scan", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.sort) {//排序
                    Toast.makeText(getContext(), "sort", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        int viewId=view.getId();
        if (viewId == R.id.ivMenu) {
            popupMenu.show();
        }
    }
}
