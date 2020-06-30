package com.example.module_me.view_interface;


import com.example.module_me.fragment.MyPhotoFragment;

import example.common_base.base.IBaseView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的相册Fragment回调
 */
public interface IMyPhotoFragmentView extends IBaseView {
    MyPhotoFragment getFragment();
}
