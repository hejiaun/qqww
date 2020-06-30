package com.example.module_public_busniess.presenter;

import android.content.Intent;
import android.graphics.Rect;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import example.common_base.base.BasePresenter;

import com.example.administrator.shengdoushi.R;
import com.example.administrator.shengdoushi.business.public_business.activity.FansListInWorkDetailActivity;
import com.example.administrator.shengdoushi.business.public_business.adapter.WorkDetailCommentAdapter;
import com.example.administrator.shengdoushi.business.public_business.view_interface.IWorkDetailsActivityView;
import example.common_base.entity.CommentEntity;

import java.util.ArrayList;

import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:作品详情Activity的Presenter
 */
public class WorkDetailsActivityPresenter extends BasePresenter<IWorkDetailsActivityView> implements View.OnClickListener {

    private BottomSheetDialog shareDailog;
    private BottomSheetDialog menuDialog;
    private WorkDetailCommentAdapter adapter;
    private View headView;
    private View footView;

    public WorkDetailsActivityPresenter(IWorkDetailsActivityView view) {
        super(view);
    }

    public void getData() {
        if (adapter != null) {
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 0)
                    adapter.addData(new CommentEntity("aaaaaaaasdfasdfasdfjlasdf//n", "比利", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2697602114,3385732471&fm=58&bpow=638&bpoh=640"));
                if (i % 3 == 0)
                    adapter.addData(new CommentEntity("谔谔谔谔谔谔谔谔//n", "比利", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1028511465,1516877804&fm=27&gp=0.jpg"));
                if (i % 5 == 0)
                    adapter.addData(new CommentEntity("谔谔谔谔谔谔谔谔//n", "比利", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3624792395,2046010114&fm=27&gp=0.jpg"));
            }
        }
    }

    public WorkDetailCommentAdapter getAdapter() {
        if (adapter == null) {
            adapter = new WorkDetailCommentAdapter(R.layout.item_comment_songdetail, new ArrayList<CommentEntity>());
            setHeadView();
            setFooterView();
            adapter.getHeaderLayout().findViewById(R.id.ivMoreFans).setOnClickListener(this);
        }
        return adapter;
    }

    /**
     * 显示分享对话框
     */
    public void showShareDialog() {
        if (shareDailog == null) {
            shareDailog = new BottomSheetDialog(getView().getActivity());
            shareDailog.setContentView(R.layout.dialog_share);
        }
        shareDailog.show();
    }

    /**
     * 显示更多选项
     */
    public void showMoreMenuDialog() {
        if (menuDialog == null) {
            menuDialog = new BottomSheetDialog(getView().getActivity());
            menuDialog.setContentView(R.layout.dialog_workdetail_menu);
            menuDialog.findViewById(R.id.llShare).setOnClickListener(this);
        }
        menuDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llShare://点击分享
                showShareDialog();
                break;
            case R.id.ivMoreFans://查看更多的粉丝
                getView().getActivity().startActivity(new Intent(getView().getActivity(), FansListInWorkDetailActivity.class));
                break;
        }
    }

    /**
     * 配置列表头布局内容
     */
    public void setHeadView() {
        headView = View.inflate(getView().getActivity(), R.layout.headview_workdetail, null);
        adapter.addHeaderView(headView);
    }

    /**
     * 配置列表尾布局内容
     */
    public void setFooterView() {
        footView = View.inflate(getView().getActivity(), R.layout.footerview_workdetail, null);
        adapter.addFooterView(footView);
    }

    /**
     * 获取列表项间隔修饰器
     *
     * @return 列表项间隔修饰器
     */
    public RecyclerView.ItemDecoration getItemDecotation() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = DensityUtils.dp2px(view.getContext(), 8);
                outRect.top = DensityUtils.dp2px(view.getContext(), 6);
                outRect.right = DensityUtils.dp2px(view.getContext(), 8);
                outRect.bottom = DensityUtils.dp2px(view.getContext(), 6);

            }
        };
    }

}
