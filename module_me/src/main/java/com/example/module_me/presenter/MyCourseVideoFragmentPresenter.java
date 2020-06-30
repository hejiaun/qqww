package com.example.module_me.presenter;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.R;
import com.example.module_me.activity.CourseActivity;
import com.example.module_me.adapter.CourseVideoPersonAdapter;
import com.example.module_me.view_interface.IMyCourseVideoFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.CourseVideoHeadEntity;
import example.common_base.entity.MyCourseVideoSectionHeadEntity;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyCourseVideoFragmentPresenter extends BasePresenter<IMyCourseVideoFragmentView> {
    private ArrayList<MyCourseVideoSectionHeadEntity> headEntities = null;
    private CourseVideoPersonAdapter courseVideoPersonAdapter = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iMyCourseVideoFragmentView View层接口
     */
    public MyCourseVideoFragmentPresenter(IMyCourseVideoFragmentView iMyCourseVideoFragmentView) {
        super(iMyCourseVideoFragmentView);
    }


    public CourseVideoPersonAdapter getAdapter() {
        if (courseVideoPersonAdapter == null) {
            courseVideoPersonAdapter = new CourseVideoPersonAdapter(R.layout.item_video_course_head, R.layout.item_course_video_section_head, getData());
            courseVideoPersonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View v, int position) {
                    MyCourseVideoSectionHeadEntity entity = (MyCourseVideoSectionHeadEntity) adapter.getData().get(position);
                    if (!entity.isHeader) {
                        getView().getFragment().startActivity(new Intent(getView().getFragment().getContext(), CourseActivity.class));
                    }
                }
            });
        }
        return courseVideoPersonAdapter;
    }

    public ArrayList getData() {
        if (headEntities == null) {
            headEntities = new ArrayList<>();
            headEntities.add(new MyCourseVideoSectionHeadEntity(true, "老师"));
            for (int i = 0; i < 4; i++) {
                headEntities.add(new MyCourseVideoSectionHeadEntity(new CourseVideoHeadEntity()));
            }
            headEntities.add(new MyCourseVideoSectionHeadEntity(true, "学生"));
            for (int i = 0; i < 22; i++) {
                headEntities.add(new MyCourseVideoSectionHeadEntity(new CourseVideoHeadEntity()));
            }
        }
        return headEntities;
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
                MyCourseVideoSectionHeadEntity entity = ((CourseVideoPersonAdapter) parent.getAdapter()).getData().get(parent.getChildAdapterPosition(view));
                if (entity.isHeader) {
                    outRect.top = DensityUtils.dp2px(view.getContext(), 10);
                    outRect.bottom = DensityUtils.dp2px(view.getContext(), 10);
                    if (entity.header.equals("学生")) {
                        outRect.top = DensityUtils.dp2px(view.getContext(), 20);
                    }
                } else {
                    outRect.bottom = DensityUtils.dp2px(view.getContext(), 18);
                }
            }
        };
    }
}
