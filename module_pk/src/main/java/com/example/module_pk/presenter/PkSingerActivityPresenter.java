package com.example.module_pk.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.module_pk.R;
import com.example.module_pk.fragment.SingerGamePart1Fragment;
import com.example.module_pk.fragment.SingerGamePart2Fragment;
import com.example.module_pk.view_interface.IPkSingerActivityView;
import com.example.module_pk.widget.BarrageDialog;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class PkSingerActivityPresenter extends BasePresenter<IPkSingerActivityView> {
    private SingerGamePart1Fragment fragment1;
    private SingerGamePart2Fragment fragment2;
    private final String SINGER_GAME_1 = "singer_game1";
    private final String SINGER_GAME_2 = "singer_game2";
    private BarrageDialog barrageDialog;

    /**
     * 构造方法，初始化View层
     */
    public PkSingerActivityPresenter(IPkSingerActivityView iPkSingerActivityView) {
        super(iPkSingerActivityView);
    }

    /**
     * 显示发送弹幕对话框
     */
    public void showBarrageDialog() {
        if (barrageDialog == null) {
            barrageDialog = new BarrageDialog(getView().getActivity());
        }
        barrageDialog.show();
    }

    /**
     * 显示fragment
     *
     * @param position
     * @param fragmentTransaction
     */
    public void showFragment(int position, FragmentTransaction fragmentTransaction) {
        hideAllFragment(fragmentTransaction);
        switch (position) {
            case 1:
                fragment1 = new SingerGamePart1Fragment();
                fragmentTransaction
                        .add(R.id.fl, fragment1);
                break;
            case 2:
                fragment2 = new SingerGamePart2Fragment();
                fragmentTransaction
                        .setCustomAnimations(R.anim.activity_fade_in, R.anim.activity_fade_out)
                        .add(R.id.fl, fragment2);
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 备份所有的fragment
     *
     * @param bundle
     */
    public void saveAllFragment(Bundle bundle) {
        FragmentManager supportFragmentManager = getView().getActivity().getSupportFragmentManager();
        if (fragment1 != null) {
            supportFragmentManager.putFragment(bundle, SINGER_GAME_1, fragment1);
        }
        if (fragment2 != null) {
            supportFragmentManager.putFragment(bundle, SINGER_GAME_2, fragment2);
        }

    }

    /**
     * 恢复所有fragment
     *
     * @param bundle
     */
    public void restoreFragment(Bundle bundle) {
        if (bundle != null) {
            FragmentManager supportFragmentManager = getView().getActivity().getSupportFragmentManager();
            fragment1 = (SingerGamePart1Fragment) supportFragmentManager.getFragment(bundle, SINGER_GAME_1);
            fragment2 = (SingerGamePart2Fragment) supportFragmentManager.getFragment(bundle, SINGER_GAME_2);
        }
    }

    /**
     * 隐藏所有不为空的Fragment
     *
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }
    }

}
