package com.example.module_me.presenter;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.R;
import com.example.module_me.adapter.MyWorkListAdapter;
import com.example.module_me.view_interface.IMyWorkVideoFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.MyWorkEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyWorkVideoFragmentPresenter extends BasePresenter<IMyWorkVideoFragmentView> {
    ArrayList<MyWorkEntity> workEntities = new ArrayList<>();
    private MyWorkListAdapter adapter;

    /**
     * 构造方法，初始化View层
     *
     * @param iMyWorkVideoFragmentView View层接口
     */
    public MyWorkVideoFragmentPresenter(IMyWorkVideoFragmentView iMyWorkVideoFragmentView) {
        super(iMyWorkVideoFragmentView);
    }


    public MyWorkListAdapter getAdapter() {
        if (adapter == null) {
            adapter = new MyWorkListAdapter(R.layout.item_mywork, getData());
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    getView().getFragment().startActivity(new Intent(getView().getFragment(), WorkDetailsActivity.class));
                    // TODO: 2019/1/13 跳转
                }
            });
        }
        return adapter;
    }

    public ArrayList<MyWorkEntity> getData() {
        for (int i = 0; i < 6; i++) {
            workEntities.add(new MyWorkEntity("", "牛仔不忙" + i, "周杰伦" + i, "3" + i + "M", "05：16  17.03"));
        }
        return workEntities;
    }

    public BaseQuickAdapter.OnItemChildClickListener getOnItemChildClickListener() {
        return new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int viewId = view.getId();
                if (viewId == R.id.ivMore) {
                    PopupMenu menu = new PopupMenu(view.getContext(), view);
                    menu.inflate(R.menu.menu_work_item);
                    menu.setOnMenuItemClickListener(getPopupMenuClickListener(adapter, position));
                    menu.show();
                }
            }
        };
    }

    /**
     * 菜单点击事件监听器
     *
     * @return
     */
    private PopupMenu.OnMenuItemClickListener getPopupMenuClickListener(final BaseQuickAdapter adapter, int position) {
        return new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.edit) {
                    Toast.makeText(getView().getFragment(), "edit", Toast.LENGTH_SHORT).show();

                } else if (itemId == R.id.release) {
                    Toast.makeText(getView().getFragment(), "release", Toast.LENGTH_SHORT).show();

                } else if (itemId == R.id.forword) {
                    Toast.makeText(getView().getFragment(), "forword", Toast.LENGTH_SHORT).show();

                } else if (itemId == R.id.delete) {
                    Toast.makeText(getView().getFragment(), "delete", Toast.LENGTH_SHORT).show();

                }

                return false;
            }
        };
    }
}
