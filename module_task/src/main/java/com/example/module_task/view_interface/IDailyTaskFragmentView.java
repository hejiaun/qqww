package com.example.module_task.view_interface;

import com.example.module_task.adapter.TaskAdapter;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:日常任务Fragment的View层接口
 */
public interface IDailyTaskFragmentView extends IBaseView {
    TaskAdapter getAdapter();
}
