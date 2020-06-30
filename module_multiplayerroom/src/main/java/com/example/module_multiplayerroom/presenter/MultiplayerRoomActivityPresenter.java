package com.example.module_multiplayerroom.presenter;
import example.common_base.base.BasePresenter;

import com.example.module_multiplayerroom.view_interface.IMultiplayerRoomActivityView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MultiplayerRoomActivityPresenter extends BasePresenter<IMultiplayerRoomActivityView> {


    /**
     * 构造方法，初始化View层
     *
     * @param iMultiplayerRoomActivityView View层接口
     */
    public MultiplayerRoomActivityPresenter(IMultiplayerRoomActivityView iMultiplayerRoomActivityView) {
        super(iMultiplayerRoomActivityView);
    }
}
