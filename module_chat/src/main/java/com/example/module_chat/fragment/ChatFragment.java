package com.example.module_chat.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_chat.R;
import com.example.module_chat.activity.AddFriendsActivity;
import com.example.module_chat.activity.ChatSearchActivity;
import com.example.module_chat.presenter.ChatFragmentPresenter;
import com.example.module_chat.view_interface.IChatFragmentView;
import com.example.module_chat.window.ChatPopupWindow;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.util.ARouterUtil;
import example.common_base.widget.ZxingUtil;

/**
 * Created by Administrator on 2018/5/
 * 聊天fragment
 */
@Route(path = ARouterUtil.Chat_Fragment)
public class ChatFragment extends BaseFragment<ChatFragmentPresenter> implements IChatFragmentView, View.OnClickListener {
    ViewPager vp;
    private ArrayList<BaseFragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private ImageView ivAdd;
    private RelativeLayout rlSearchBar;
    private TextView tvSession;
    private TextView tvFriend;
    private ChatPopupWindow chatPopupWindow;

    @Override
    public void initView() {
        super.initView();
        vp = view.findViewById(R.id.vp);
        ivAdd = view.findViewById(R.id.ivAdd);
        rlSearchBar = view.findViewById(R.id.rlSearchBar);
        tvFriend = view.findViewById(R.id.tvFriend);
        tvSession = view.findViewById(R.id.tvSession);

        ivAdd.setOnClickListener(this);
        rlSearchBar.setOnClickListener(this);
        tvFriend.setOnClickListener(this);
        tvSession.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        fragments = new ArrayList<>();
        fragments.add(new ChatSessionFragment());
        fragments.add(new ChatContactsFragment());
        fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };
        onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tvFriend.setTextSize(12);
                    tvSession.setTextSize(20);
                } else {
                    tvFriend.setTextSize(20);
                    tvSession.setTextSize(12);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
        vp.setAdapter(fragmentPagerAdapter);
        vp.addOnPageChangeListener(onPageChangeListener);
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ChatFragmentPresenter createPresenter() {
        return new ChatFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_chat;
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.ivAdd) {
            showMenu();
        } else if (viewId == R.id.rlSearchBar) {
            startActivity(new Intent(getContext(), ChatSearchActivity.class));
        } else if (viewId == R.id.tvSession) {
            tvSession.setTextSize(20);
            tvFriend.setTextSize(12);
            vp.setCurrentItem(0);
        } else if (viewId == R.id.tvFriend) {
            tvSession.setTextSize(12);
            tvFriend.setTextSize(20);
            vp.setCurrentItem(1);
        }
    }

    public void showMenu() {
        if (chatPopupWindow == null) {
            chatPopupWindow = new ChatPopupWindow(getActivity(), ivAdd);
            chatPopupWindow.setClickListener(new ChatPopupWindow.MyClickListener() {
                @Override
                public void clickAddFriend() {
                    startActivity(new Intent(getContext(), AddFriendsActivity.class));
                }

                @Override
                public void clickScan() {
                    ZxingUtil.getInstence().startScanActivity(ChatFragment.this.getActivity());
                }

                @Override
                public void clickGroup() {

                }
            });
        }
        chatPopupWindow.show();
    }
}
