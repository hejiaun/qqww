package com.example.module_study.presenter;


import com.example.module_study.view_interface.IStudySingPresenter;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.SongListEntity;

/**
 * Author: HeJiaJun
 * Date:
 */
public class StudySingPresenter extends BasePresenter<IStudySingPresenter> {


    /**
     * 构造方法，初始化View层
     *
     * @param iStudySingPresenter View层接口
     */
    public StudySingPresenter(IStudySingPresenter iStudySingPresenter) {
        super(iStudySingPresenter);
    }

    /**
     * 获取大课课程实体
     *
     * @return
     */
    ArrayList<SongListEntity> getBigCourseEntities() {
        return null;
    }

    /**
     * 获取一对一课程实体
     *
     * @return
     */
    ArrayList<SongListEntity> getOneByOneEntities() {
        return null;
    }

    /**
     * 获取一对多课程实体
     *
     * @return
     */
    ArrayList<SongListEntity> getOneByMoreEntitise() {
        return null;
    }
}
