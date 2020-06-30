package com.example.module_study.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_study.model.StudyCourseListFragmentModel;
import com.example.module_study.view_interface.IStudyCourseListFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.StudyCourseEntity;
import example.common_base.util.DensityUtils;
import rx.functions.Action1;

/**
 * PorjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 课程列表Fragment的Presenter
 */
public class StudyCourseListFragmentPresenter extends BasePresenter<IStudyCourseListFragmentView> {

    StudyCourseListFragmentModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iStudyCourseListFragmentView View层接口
     */
    public StudyCourseListFragmentPresenter(IStudyCourseListFragmentView iStudyCourseListFragmentView) {
        super(iStudyCourseListFragmentView);
        model = new StudyCourseListFragmentModel();
    }


    /**
     * 请求刷新数据
     */
    public void requestRefreshData() {
        getView().getSwipeRefreshLayout().setRefreshing(true);
        model.getFristEntryData(new Action1<ArrayList<StudyCourseEntity>>() {
            @Override
            public void call(ArrayList<StudyCourseEntity> studyCourseEntities) {
                getView().getAdapter().setNewData(studyCourseEntities);
                getView().getSwipeRefreshLayout().setRefreshing(false);
            }
        });
    }

    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntryData() {
        getView().getSwipeRefreshLayout().setRefreshing(true);
        model.getFristEntryData(new Action1<ArrayList<StudyCourseEntity>>() {
            @Override
            public void call(ArrayList<StudyCourseEntity> studyCourseEntities) {
                getView().getAdapter().addData(studyCourseEntities);
                getView().getSwipeRefreshLayout().setRefreshing(false);
            }
        });
    }

    /**
     * 请求更多数据
     */
    public void requestMoreData() {
        model.getMoreCourseListData(new Action1<ArrayList<StudyCourseEntity>>() {
            @Override
            public void call(ArrayList<StudyCourseEntity> studyCourseEntities) {
                if (getView().getAdapter().getData().size() <= 60) {
                    getView().getAdapter().addData(studyCourseEntities);
                    getView().getAdapter().loadMoreComplete();
                } else {
                    getView().getAdapter().loadMoreEnd();
                }
            }
        });
    }

    /**
     * 获取RecyclerView的Item间隔修饰器
     *
     * @return RecyclerView的Item间隔修饰器
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = DensityUtils.dp2px(view.getContext(), 4);
                outRect.bottom = DensityUtils.dp2px(view.getContext(), 4);
                outRect.left = DensityUtils.dp2px(view.getContext(), 8);
                outRect.right = DensityUtils.dp2px(view.getContext(), 8);
            }
        };
    }
}
