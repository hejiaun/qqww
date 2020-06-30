package com.example.module_home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_home.R;
import com.example.module_home.activity.HotFightFightActivity;
import com.example.module_home.activity.SearchInHomeActivity;
import com.example.module_home.adapter.RankRecyclerViewAdapter;
import com.example.module_home.presenter.HomePresenter;
import com.example.module_home.view_interface.IHomeView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.entity.RankingListEntity;
import example.common_base.entity.TabEntity;
import example.common_base.eventbusevent.DefaultBusEvent;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ConstantValuesUtil;


/**
 * Author: HeJiaJun
 * Date:
 * Description:主页Fragment
 */
@Route(path = ARouterUtil.Home_fragment)
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView,
        BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {
    LinearLayout llMenu;
    CommonTabLayout ctl;
    RecyclerView rcv;
    private RecyclerView.OnScrollListener onScrollListener;
    private RankRecyclerViewAdapter adapter;
    private LinearLayout llSearch;
    private TextView tvStrongest;
    private TextView tvHot;
    private ImageView ivPractice;
    private ImageView ivTask;
    private ImageView ivPk;
    private ImageView ivRater;

    @Override
    public void initView() {
        super.initView();
        llMenu = view.findViewById(R.id.ll_menu);
        ctl = view.findViewById(R.id.ctl);
        rcv = view.findViewById(R.id.rcv);
        ivRater = view.findViewById(R.id.ivRater);
        ivPk = view.findViewById(R.id.ivPk);
        ivTask = view.findViewById(R.id.ivTask);
        ivPractice = view.findViewById(R.id.ivPractice);
        tvHot = view.findViewById(R.id.tv_hot);
        tvStrongest = view.findViewById(R.id.tv_strongest);
        llSearch = view.findViewById(R.id.ll_search);

        ivPk.setOnClickListener(this);
        ivRater.setOnClickListener(this);
        ivTask.setOnClickListener(this);
        ivPractice.setOnClickListener(this);
        tvHot.setOnClickListener(this);
        tvStrongest.setOnClickListener(this);
        llSearch.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_home;
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new RankRecyclerViewAdapter(R.layout.item_famous, new ArrayList<RankingListEntity>());
        adapter.setOnItemChildClickListener(this);

        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("全国"));
        tabEntities.add(new TabEntity("全省"));
        tabEntities.add(new TabEntity("本地"));
        tabEntities.add(new TabEntity("好友"));
        ctl.setTabData(tabEntities);

        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(getPresenter().getItemDecoration());

        initOnScrollListener();
        rcv.addOnScrollListener(onScrollListener);
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    /**
     * 设置底部菜单可见性
     *
     * @param anim       更换可见性时的动画
     * @param visibility 可见性
     */
    public void setButtomItemVisibility(int anim, int visibility) {
        llMenu.startAnimation(AnimationUtils.loadAnimation(getActivity(), anim));
        llMenu.setVisibility(visibility);
    }

    /**
     * 加载滚动监听器
     */
    private void initOnScrollListener() {
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 10 || !recyclerView.canScrollVertically(1)) {//下滚
                    if (llMenu.getVisibility() == View.GONE) {
                        return;
                    }
                    setButtomItemVisibility(R.anim.home_hide_menu, View.GONE);
                } else if (dy < -10 || !recyclerView.canScrollVertically(-1)) {  //上滚
                    if (llMenu.getVisibility() == View.VISIBLE) {
                        return;
                    }
                    setButtomItemVisibility(R.anim.home_show_menu, View.VISIBLE);
                }
            }
        };
    }

    /**
     * 列表Item子控件点击事件监听
     *
     * @param adapter  列表适配器
     * @param view     Item 布局
     * @param position Item 位置
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        DefaultBusEvent defaultBusEvent = new DefaultBusEvent(DefaultBusEvent.USER_INFORMATION);
        int viewId = view.getId();
        if (viewId == R.id.iv_rank) {//排位
            defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getRankUserHead()));
        } else if (viewId == R.id.iv_judge) {//评委
            defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getRaterUserHead()));
        } else if (viewId == R.id.iv_popularity) {//人气
            defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getPropularity()));
        } else if (viewId == R.id.iv_song) {//歌曲
            defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getSongUserHead()));
        }
        EventBus.getDefault().postSticky(defaultBusEvent);
        ARouter.getInstance().build(ARouterUtil.OthersInformation_Activity).navigation();
//        // TODO: 2018/10/8 显示用户信息
//        switch (view.getId()) {
//            case R.id.iv_rank://排位
//                defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getRankUserHead()));
//                break;
////            case R.id.iv_guide://名师
////                defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getTeacherUserHead()));
////                break;
//            case R.id.iv_judge://评委
//                defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getRaterUserHead()));
//                break;
//            case R.id.iv_popularity://人气
//                defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getPropularity()));
//                break;
//            case R.id.iv_song://歌曲
//                defaultBusEvent.setMsgString((((RankingListEntity) adapter.getData().get(position)).getSongUserHead()));
//                break;
//        }
//        EventBus.getDefault().postSticky(defaultBusEvent);
//        startActivity(new Intent(getActivity(), OthersInformationActivity.class));
    }

    /**
     * View层向Presenter层提供RankRecyclerViewAdapter
     *
     * @return RankRecyclerViewAdapter
     */
    @Override
    public RankRecyclerViewAdapter getAdapter() {
        return adapter;
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
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.ivRater) {//评委模式
            ARouter.getInstance().
                    build(ARouterUtil.PkMode_Activity)
                    .withString("game_role", ConstantValuesUtil.GAME_ROLE_RATER)
                    .navigation();
        } else if (viewId == R.id.ivPk) {//pk模式
            ARouter.getInstance().
                    build(ARouterUtil.PkMode_Activity)
                    .withString("game_role", ConstantValuesUtil.GAME_ROLE_SINGER)
                    .navigation();
        } else if (viewId == R.id.ivTask) {//任务
            ARouter.getInstance().
                    build(ARouterUtil.Task_Activity)
                    .navigation();
        } else if (viewId == R.id.ivPractice) {//练歌房
            ARouter.getInstance()
                    .build(ARouterUtil.Practice_Activity)
                    .navigation();
        } else if (viewId == R.id.tv_hot) {//热门
            getActivity().
                    startActivity(new Intent(getContext(), HotFightFightActivity.class));
        } else if (viewId == R.id.tv_strongest) {

        } else if (viewId == R.id.ll_search) {
            startActivity(new Intent(getContext(), SearchInHomeActivity.class));
        }
    }
}
