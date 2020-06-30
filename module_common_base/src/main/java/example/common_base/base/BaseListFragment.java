package example.common_base.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.common_base.R;


/**
 * Author: HeJiaJun
 * Date:
 * Description:列表Fragment基础类(Base父类)
 */
public abstract class BaseListFragment<T extends BasePresenter> extends BaseFragment<T> {
    /**
     * 列表控件
     */
    public RecyclerView rcv;

    /**
     * 列表下拉刷新控件
     */
    public SwipeRefreshLayout srl;

    @Override
    public void initView() {
        super.initView();
        rcv = view.findViewById(R.id.rcv);
        srl = view.findViewById(R.id.srl);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_list;
    }


}
