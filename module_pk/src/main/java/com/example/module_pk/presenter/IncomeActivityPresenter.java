package com.example.module_pk.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.module_pk.R;
import com.example.module_pk.fragment.GameOverFragment;
import com.example.module_pk.fragment.RaterIncomeFragment;
import com.example.module_pk.fragment.SingerIncomeFragment;
import com.example.module_pk.view_interface.IIncomeActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class IncomeActivityPresenter extends BasePresenter<IIncomeActivityView> {
    private SingerIncomeFragment singerIncomeFragment;
    private RaterIncomeFragment raterIncomeFragment;
    private GameOverFragment gameOverFragment;
    private final String SINGER_INCOME = "singer_income";
    private final String RATER_INCOME = "rater_income";
    private final String GAME_VOER = "game_over";


    /**
     * 构造方法，初始化View层
     *
     * @param iIncomeActivityView View层接口
     */
    public IncomeActivityPresenter(IIncomeActivityView iIncomeActivityView) {
        super(iIncomeActivityView);
    }

    /**
     * 显示fragment
     *
     * @param page <ul>
     *             <li>0:歌手结算页面</li>
     *             <li>1:评委结算页面</li>
     *             <li>2:游戏结束页面</li>
     *             </ul>
     */
    public void showFragment(int page) {
        FragmentTransaction fragmentTransaction = getView().getActivity().getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (page) {
            case 0:
                if (singerIncomeFragment == null) {
                    singerIncomeFragment = new SingerIncomeFragment();
                    fragmentTransaction
                            .setCustomAnimations(R.anim.activity_fade_in, R.anim.activity_fade_out)
                            .add(R.id.flIncome, singerIncomeFragment);
                } else {
                    fragmentTransaction.show(singerIncomeFragment);
                }
                break;
            case 1:
                if (raterIncomeFragment == null) {
                    raterIncomeFragment = new RaterIncomeFragment();
                    fragmentTransaction
                            .setCustomAnimations(R.anim.activity_fade_in, R.anim.activity_fade_out)
                            .add(R.id.flIncome, raterIncomeFragment);
                } else {
                    fragmentTransaction.show(raterIncomeFragment);
                }
                break;
            case 2:
                if (gameOverFragment == null) {
                    gameOverFragment = new GameOverFragment();
                    fragmentTransaction
                            .setCustomAnimations(R.anim.activity_fade_in, R.anim.activity_fade_out)
                            .add(R.id.flIncome, gameOverFragment);
                } else {
                    fragmentTransaction.show(gameOverFragment);
                }
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
        if (singerIncomeFragment != null) {
            supportFragmentManager.putFragment(bundle, SINGER_INCOME, singerIncomeFragment);
        }
        if (raterIncomeFragment != null) {
            supportFragmentManager.putFragment(bundle, RATER_INCOME, raterIncomeFragment);
        }
        if (gameOverFragment != null) {
            supportFragmentManager.putFragment(bundle, GAME_VOER, gameOverFragment);
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
            singerIncomeFragment = (SingerIncomeFragment) supportFragmentManager.getFragment(bundle, SINGER_INCOME);
            raterIncomeFragment = (RaterIncomeFragment) supportFragmentManager.getFragment(bundle, RATER_INCOME);
            gameOverFragment = (GameOverFragment) supportFragmentManager.getFragment(bundle, GAME_VOER);
        }
    }

    /**
     * 隐藏不为空的fragment
     *
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (singerIncomeFragment != null) {
            fragmentTransaction.hide(singerIncomeFragment);
        }
        if (raterIncomeFragment != null) {
            fragmentTransaction.hide(raterIncomeFragment);
        }
        if (gameOverFragment != null) {
            fragmentTransaction.hide(raterIncomeFragment);
        }
    }
}
