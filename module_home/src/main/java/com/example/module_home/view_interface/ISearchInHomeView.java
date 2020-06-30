package com.example.module_home.view_interface;

import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

import example.common_base.base.IBaseView;
import example.common_base.entity.MyMultiplyEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description: 主页搜索Activity的View层接口
 */
public interface ISearchInHomeView extends IBaseView {
    Activity getActivity();

    void clickTag(TextView tv, ArrayList<MyMultiplyEntity> entities);
}
