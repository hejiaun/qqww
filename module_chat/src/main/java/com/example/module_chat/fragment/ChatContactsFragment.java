package com.example.module_chat.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_chat.R;
import com.example.module_chat.adapter.ChatContactAdapter;
import com.example.module_chat.presenter.ChatContactsPresenter;
import com.example.module_chat.view_interface.IChatContactsView;

import java.util.ArrayList;
import java.util.List;

import example.common_base.base.BaseFragment;
import example.common_base.entity.MyMultiplyEntity;
import example.common_base.entity.TitleMultiplyentity;
import example.common_base.util.DensityUtils;
import example.common_base.util.RecyclerViewItemDecorationUtil;
import example.common_base.widget.SlideSortBar;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  聊天通讯录Fragment
 */
public class ChatContactsFragment extends BaseFragment<ChatContactsPresenter> implements
        IChatContactsView,
        BaseQuickAdapter.OnItemClickListener,
        SlideSortBar.OnWordChangeListener {
    private ChatContactAdapter adapter = null;
    private SwipeRefreshLayout srl;
    private RecyclerView rcv;
    private SlideSortBar ssb;
    private TextView tvCenter;

    @Override
    public void initView() {
        super.initView();
        tvCenter = view.findViewById(R.id.tvCenter);
        ssb = view.findViewById(R.id.ssb);
        srl = view.findViewById(R.id.srl);
        rcv = view.findViewById(R.id.rcv);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_chat_contact;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ChatContactsPresenter createPresenter() {
        return new ChatContactsPresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new ChatContactAdapter(new ArrayList<MyMultiplyEntity>());

        srl.setEnabled(false);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(0, 0, DensityUtils.dp2px(getContext(), 2), 0));
        adapter.setOnItemClickListener(this);

        ssb.setWordChangeListener(this);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        getPresenter().requestFirstEntryData();
    }

    /**
     * View层向Presenter层提供ChatContactsFragment
     *
     * @return ChatContactsFragment
     */
    @Override
    public ChatContactsFragment getFragment() {
        return this;
    }

    /**
     * View层向Presenter提供ChatContactAdapter
     *
     * @return ChatContactAdapter
     */
    @Override
    public ChatContactAdapter getAdapter() {
        return adapter;
    }

    /**
     * 联系人列表点击事件
     *
     * @param adapter  联系人列表适配器
     * @param view     Item 布局
     * @param position Item 位置
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    /**
     * 字符排序滑动条的滑动监听
     *
     * @param s
     */
    @Override
    public void wordChange(String s) {
        //--首先判断是否为标题项目，再判断标题字符是否和当前触摸的字符一致---//
        tvCenter.setText(s);
        tvCenter.setVisibility(View.VISIBLE);
        List<MyMultiplyEntity> data = adapter.getData();
        for (int i = 0; i < data.size(); i++) {
            MyMultiplyEntity myMultiplyEntity = data.get(i);
            //--判断是否为标题项---//
            if (myMultiplyEntity.getItemType() == MyMultiplyEntity.TITLE) {
                TitleMultiplyentity titleMultiplyentity = (TitleMultiplyentity) myMultiplyEntity;
                char c = titleMultiplyentity.getTitle().toUpperCase().charAt(0);
                //--当前字符是否和标题字符一致---//
                if (s.equals(c + "")) {
                    //--使标题滚动到列表可视范围的首位置---//
                    LinearLayoutManager layoutManager = (LinearLayoutManager) rcv.getLayoutManager();
                    layoutManager.scrollToPositionWithOffset(i, 0);
                }
            }
        }
    }

    /**
     * 字符排序滑动条手指抬起的触发事件
     */
    @Override
    public void wordUpChang() {
        tvCenter.setVisibility(View.GONE);
    }
}
