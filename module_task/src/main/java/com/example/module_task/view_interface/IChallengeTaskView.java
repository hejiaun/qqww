package com.example.module_task.view_interface;

import com.example.module_task.adapter.TaskAdapter;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:挑战任务Fragment的View层接口
 */
public interface IChallengeTaskView extends IBaseView {
    TaskAdapter getAdapter();
}
