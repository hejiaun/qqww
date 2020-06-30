package com.example.module_me.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_me.adapter.FightRecordVideoAdapter;
import com.example.module_me.view_interface.IMyFightVideoFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.FightRecordVideoEntity;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyFightVideoFragmentPresenter extends BasePresenter<IMyFightVideoFragmentView> {
    private ArrayList<FightRecordVideoEntity> fightRecordVideos;
    private FightRecordVideoAdapter adapter;

    public MyFightVideoFragmentPresenter(IMyFightVideoFragmentView iMyFightVideoFragmentView) {
        super(iMyFightVideoFragmentView);
    }

    public ArrayList<FightRecordVideoEntity> getData() {
        if (fightRecordVideos == null) {
            fightRecordVideos = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                if (i % 3 == 0) {
                    fightRecordVideos.add(new FightRecordVideoEntity(FightRecordVideoEntity.RATER));
                } else {
                    fightRecordVideos.add(new FightRecordVideoEntity(FightRecordVideoEntity.PLAYER));
                }
            }
        }
        return fightRecordVideos;
    }

    public FightRecordVideoAdapter getAdapter() {
        if (adapter == null) {
            adapter = new FightRecordVideoAdapter(getData());
        }
        return adapter;
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
                outRect.bottom = DensityUtils.dp2px(view.getContext(), 10);
            }
        };
    }
}
