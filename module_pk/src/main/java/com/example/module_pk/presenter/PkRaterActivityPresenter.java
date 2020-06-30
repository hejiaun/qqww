package com.example.module_pk.presenter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.module_pk.R;
import com.example.module_pk.adapter.AudienceAdapter;
import com.example.module_pk.entity.AudienceEntiy;
import com.example.module_pk.fragment.RaterGamePart1Fragment;
import com.example.module_pk.fragment.RaterGamePart2Fragment;
import com.example.module_pk.view_interface.IPkRaterActivityView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;


/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class PkRaterActivityPresenter extends BasePresenter<IPkRaterActivityView> {
    ArrayList<String> words = null;
    private View dialogView = null;
    private Dialog dialog = null;
    private RaterGamePart1Fragment fragment1;
    private RaterGamePart2Fragment fragment2;
    private final String RATER_GAME_1 = "rater_game_1";
    private final String RATER_GAME_2 = "rater_game_2";


    /**
     * 构造方法，初始化View层
     *
     * @param iPkRaterActivityView View层接口
     */
    public PkRaterActivityPresenter(IPkRaterActivityView iPkRaterActivityView) {
        super(iPkRaterActivityView);
    }

    /**
     * 显示指定fragment
     *
     * @param position
     * @param fragmentTransaction
     */
    public void showFragment(int position, FragmentTransaction fragmentTransaction) {
        hideAllFragment(fragmentTransaction);
        switch (position) {
            case 1:
                fragment1 = new RaterGamePart1Fragment();
                fragmentTransaction
                        .add(R.id.fl, fragment1);
                break;
            case 2:
                fragment2 = new RaterGamePart2Fragment();
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
            supportFragmentManager.putFragment(bundle, RATER_GAME_1, fragment1);
        }
        if (fragment2 != null) {
            supportFragmentManager.putFragment(bundle, RATER_GAME_2, fragment2);
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
            fragment1 = (RaterGamePart1Fragment) supportFragmentManager.getFragment(bundle, RATER_GAME_1);
            fragment2 = (RaterGamePart2Fragment) supportFragmentManager.getFragment(bundle, RATER_GAME_2);
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

    /**
     * 获取观众列表数据
     */
    public void getAdudienceData() {
        AudienceAdapter adapter = getView().getAdapter();
        for (int i = 0; i < 30; i++) {
            adapter.addData(new AudienceEntiy("LTI-", R.drawable.audience1));
            adapter.addData(new AudienceEntiy("大眼睛长睫毛", R.drawable.audience2));
            adapter.addData(new AudienceEntiy("Sweet Cry", R.drawable.audience3));
            adapter.addData(new AudienceEntiy("小毛毛", R.drawable.audience4));
        }
    }

}
