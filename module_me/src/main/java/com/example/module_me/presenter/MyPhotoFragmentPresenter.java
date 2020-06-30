package com.example.module_me.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_me.R;
import com.example.module_me.adapter.MyPhotoAdapter;
import com.example.module_me.view_interface.IMyPhotoFragmentView;

import java.util.ArrayList;
import java.util.Date;

import example.common_base.base.BasePresenter;
import example.common_base.entity.MyPhotoEntity;
import example.common_base.util.DensityUtils;

/**
 * Author: HeJiaJun
 * Date:
 * Description: 我的相册Fragment服务
 */
public class MyPhotoFragmentPresenter extends BasePresenter<IMyPhotoFragmentView> {
    private MyPhotoAdapter adapter = null;
    private ArrayList<MyPhotoEntity> data = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iMyPhotoFragmentView View层接口
     */
    public MyPhotoFragmentPresenter(IMyPhotoFragmentView iMyPhotoFragmentView) {
        super(iMyPhotoFragmentView);
    }


    /**
     * 获取相册列表适配器
     */
    public MyPhotoAdapter getRecyclerViewAdapter() {
        if (adapter == null) {
            adapter = new MyPhotoAdapter(getData());
            adapter.addHeaderView(View.inflate(getView().getFragment().getContext(), R.layout.headview_photo, null));
        }
        return adapter;
    }

    public ArrayList<MyPhotoEntity> getData() {
        if (data == null) {
            data = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 0) data.add(new MyPhotoEntity(MyPhotoEntity.FORWORD_WORK, new Date()));
                if (i % 3 == 0)
                    data.add(new MyPhotoEntity(MyPhotoEntity.ONLY_TEXT, new Date(new Date().getTime() - 1000 * 24 * 60 * 60 * 5)));
                if (i % 5 == 0)
                    data.add(new MyPhotoEntity(MyPhotoEntity.TEXT_IMAGE, new Date(new Date().getTime() - 1000 * 24 * 60 * 60)));
                if (i % 7 == 0) data.add(new MyPhotoEntity(MyPhotoEntity.TEXT_VIDEO, new Date()));
            }
        }
        return data;
    }

    /**
     * 获取列表Item间隔修饰器
     *
     * @return 列表Item间隔修饰器
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = DensityUtils.dp2px(view.getContext(), 30);
            }
        };
    }

}
