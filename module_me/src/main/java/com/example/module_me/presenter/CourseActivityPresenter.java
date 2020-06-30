package com.example.module_me.presenter;

import com.example.module_me.R;
import com.example.module_me.adapter.CourseVideoAdapter;
import com.example.module_me.view_interface.ICourseActivityView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.CourseEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:课程列表Activity服务
 */
public class CourseActivityPresenter extends BasePresenter<ICourseActivityView> {
    CourseVideoAdapter adapter = null;
    ArrayList<CourseEntity> data;

    /**
     * 构造方法，初始化View层
     *
     * @param iCourseActivityView View层接口
     */
    public CourseActivityPresenter(ICourseActivityView iCourseActivityView) {
        super(iCourseActivityView);
    }


    public ArrayList getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        for (int i = 0; i < 10; i++) {
            data.add(new CourseEntity());
        }
        return data;
    }

    public CourseVideoAdapter getAdapter() {
        if (adapter == null) {
            adapter = new CourseVideoAdapter(R.layout.item_video_course, getData());
        }
        return adapter;
    }

}
