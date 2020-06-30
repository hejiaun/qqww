package example.common_base.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.common_base.R;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.entity.TabEntity;
import example.common_base.util.ARouterUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:主Activity的Presenter层
 */
public class MainPresenter extends BasePresenter<IMainView> {
    private ArrayList<CustomTabEntity> tabEntities;
    private BaseFragment chatFragment = null;
    private BaseFragment homeFragment = null;
    private BaseFragment findFragment = null;
    private BaseFragment meFragment = null;
    final static private String HOME_FRAGMENT_KEY = "homefragmentkey";
    final static private String CHAT_FRAGMENT_KEY = "chatfragmentkey";
    final static private String ME_FRAGMENT_KEY = "mefragmentkey";
    final static private String FIND_FRAGMENT_KEY = "findfragmentkey";

    /**
     * 构造方法，初始化View层
     *
     * @param iMainView View层接口
     */
    public MainPresenter(IMainView iMainView) {
        super(iMainView);
    }

    public ArrayList<CustomTabEntity> getTabEntitys() {
        if (tabEntities == null) {
            tabEntities = new ArrayList<>();
            tabEntities.add(new TabEntity("聊天", R.drawable.common_tab_chat_s, R.drawable.common_tab_chat_n));
            tabEntities.add(new TabEntity("主页", R.drawable.common_tab_home_s, R.drawable.common_tab_home_n));
            tabEntities.add(new TabEntity("发现", R.drawable.common_tab_find_s, R.drawable.common_tab_find_n));
            tabEntities.add(new TabEntity("我的", R.drawable.common_tab_mine_s, R.drawable.common_tab_mine_n));
        }
        return tabEntities;
    }

    /**
     * 隐藏所有fragment
     *
     * @param fragmentTransaction
     */
    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (chatFragment != null) {
            fragmentTransaction.hide(chatFragment);
        }

        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (findFragment != null) {
            fragmentTransaction.hide(findFragment);
        }

        if (meFragment != null) {
            fragmentTransaction.hide(meFragment);
        }
    }

    /**
     * @param fragmentTransaction
     */
    public void showFragment(int position, FragmentTransaction fragmentTransaction) {
        hideAllFragment(fragmentTransaction);
        switch (position) {
            case 0://聊天
                if (chatFragment == null) {
                    chatFragment = ARouterUtil.getFragment(ARouterUtil.Chat_Fragment);
                    if (chatFragment != null) {
                        fragmentTransaction.add(R.id.fv_fragment_main, chatFragment);
                    }
                } else {
                    fragmentTransaction.show(chatFragment);
                }
                break;
            case 1://主页
                if (homeFragment == null) {
                    homeFragment = ARouterUtil.getFragment(ARouterUtil.Home_fragment);
                    if (homeFragment != null) {
                        fragmentTransaction.add(R.id.fv_fragment_main, homeFragment);
                    }
                }
                fragmentTransaction.show(homeFragment);
                break;
            case 2://发现
                if (findFragment == null) {
                    findFragment = ARouterUtil.getFragment(ARouterUtil.Find_Fragment);
                    if (findFragment != null) {
                        fragmentTransaction.add(R.id.fv_fragment_main, findFragment);
                    }
                } else {
                    fragmentTransaction.show(findFragment);
                }
                break;
            case 3://我的
                if (meFragment == null) {
                    meFragment = ARouterUtil.getFragment(ARouterUtil.Me_Fragment);
                    if (meFragment != null) {
                        fragmentTransaction.add(R.id.fv_fragment_main, meFragment);
                    }
                } else {
                    fragmentTransaction.show(meFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 使用Bundle 保存所有的fragment
     *
     * @param bundle
     */
    public void saveAllFragment(Bundle bundle) {
        FragmentManager supportFragmentManager = getView().getActivity().getSupportFragmentManager();
        if (homeFragment != null) {
            supportFragmentManager.putFragment(bundle, HOME_FRAGMENT_KEY, homeFragment);
        }
        if (chatFragment != null) {
            supportFragmentManager.putFragment(bundle, CHAT_FRAGMENT_KEY, chatFragment);
        }
        if (findFragment != null) {
            supportFragmentManager.putFragment(bundle, FIND_FRAGMENT_KEY, findFragment);
        }
        if (meFragment != null) {
            supportFragmentManager.putFragment(bundle, ME_FRAGMENT_KEY, meFragment);
        }
    }

    /**
     * 使用Bundle 恢复所有的fragment
     *
     * @param bundle
     */
    public void restoreFragment(Bundle bundle) {
        if (bundle != null) {
            FragmentManager supportFragmentManager = getView().getActivity().getSupportFragmentManager();
            BaseFragment saveHomeFragment = (BaseFragment) supportFragmentManager.getFragment(bundle, HOME_FRAGMENT_KEY);
            if (saveHomeFragment != null) {
                homeFragment = saveHomeFragment;
            }
            BaseFragment saveFindFragment = (BaseFragment) supportFragmentManager.getFragment(bundle, FIND_FRAGMENT_KEY);
            if (saveHomeFragment != null) {
                findFragment = saveFindFragment;
            }

            BaseFragment saveMeFragment = (BaseFragment) supportFragmentManager.getFragment(bundle, ME_FRAGMENT_KEY);
            if (saveMeFragment != null) {
                meFragment = saveMeFragment;
            }

            BaseFragment saveChatFragment = (BaseFragment) supportFragmentManager.getFragment(bundle, CHAT_FRAGMENT_KEY);
            if (saveChatFragment != null) {
                chatFragment = saveChatFragment;
            }

        }
    }



}
