package example.common_base.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billy.android.loading.Gloading;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  Fragment基础父类(Base父类)
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    private T presenter;

    public View view = null;

    /**
     * Fragment的View加载完毕的标记
     */
    private boolean isViewCreated;

    /**
     * Fragment对用户是否可见的标记
     */
    private boolean isUIVisible;

    /**
     * Fragment是否第一次可见
     */
    private boolean isFirstVisible = true;

    /**
     * 是否要懒加载
     */
    public boolean isViewpagerFragment = true;
    public Gloading.Holder loadingHolder;

    /**
     * 创建Fragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (view == null) {
            view = View.inflate(getActivity().getApplicationContext(), initLayout(), null);
            loadingHolder = Gloading.getDefault().wrap(view);
            loadingHolder.showLoading();
            initView();
        }
    }

    /**
     * 创建Fragment视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isViewpagerFragment) {
            initStart();
        }
        return loadingHolder.getWrapper();
    }

    /**
     * Fragment视图创建完成
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    /**
     * 获取presenter
     *
     * @return
     */
    public T getPresenter() {
        return presenter;
    }

    /**
     * 设置对用户是否可见
     *
     * @param isVisibleToUser 界面是否对用户可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    /**
     * Fragment懒加载
     */
    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible && isFirstVisible) {
            initStart();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
            isFirstVisible = false;
        }
    }

    /**
     * 开始配置，加载数据
     */
    public void initStart() {
        presenter = createPresenter();
        initConfig();
        initData();
    }

    /**
     * 基础配置
     */
    public void initConfig() {

    }

    /**
     * 加载数据
     */
    public void initData() {
        loadingHolder.showLoadSuccess();
    }

    /**
     * 创建presenter
     *
     * @return
     */
    public abstract T createPresenter();

    /**
     * 加载布局
     *
     * @return 布局id
     */
    public abstract int initLayout();

    /**
     * 加载控件
     */
    public void initView() {

    }

}
