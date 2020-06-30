package com.example.module_me.presenter;

import android.app.AlertDialog;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_me.R;
import com.example.module_me.adapter.ApprenticeFragmentAdapter;
import com.example.module_me.view_interface.IApprenticeFragmentView;

import java.util.ArrayList;
import java.util.List;

import example.common_base.base.BasePresenter;
import example.common_base.entity.CourseEntity;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:拜师Fragment的Presenter
 */
public class ApprenticeFragmentPresenter extends BasePresenter<IApprenticeFragmentView> {
    ApprenticeFragmentAdapter adapter = null;
    int currentPrice = 0;
    private View dialogView;
    /**
     * 确认课程对话框课程列表
     */
    private RecyclerView recyclerView;
    /**
     * 已选中课程集合
     */
    private ArrayList<CourseEntity> selectCoursies = new ArrayList<>();
    //    private DialogListAdapter dialogListAdapter;
    private AlertDialog alertDialog;
    private DialogAdapter dialogAdapter;

    /**
     * 构造方法，初始化View层
     *
     * @param iApprenticeFragmentView View层接口
     */
    public ApprenticeFragmentPresenter(IApprenticeFragmentView iApprenticeFragmentView) {
        super(iApprenticeFragmentView);
    }

    public ApprenticeFragmentAdapter getAdapter() {
        if (adapter == null) {
            adapter = new ApprenticeFragmentAdapter(R.layout.item_apprentice, new ArrayList<CourseEntity>());
            for (int i = 0; i < 20; i++) adapter.getData().add(new CourseEntity(100, 30, "声乐1对1"));
        }
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View v, int position) {
                CourseEntity courseEntity = (CourseEntity) adapter.getData().get(position);
                //添加已经选中课程
                if (!selectCoursies.contains(courseEntity)) {
                    selectCoursies.add(courseEntity);
                }
                int viewId=v.getId();
                if (viewId == R.id.tvAdd) {
                    courseEntity.setNum(courseEntity.getNum() + 1);
                    currentPrice = currentPrice + courseEntity.getPrice();
                } else if (viewId==R.id.tvReduce) {
                    if (courseEntity.getNum() > 0) {
                        courseEntity.setNum(courseEntity.getNum() - 1);
                        currentPrice = currentPrice - courseEntity.getPrice();
                        if (courseEntity.getNum() == 0) {
                            selectCoursies.remove(courseEntity);
                        }
                    }
                }
                getView().getCurrentPriceTextView().setText(currentPrice + "");
                adapter.notifyItemChanged(position);
            }

        });
        return adapter;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = DensityUtils.dp2px(view.getContext(), 10);
                outRect.top = DensityUtils.dp2px(view.getContext(), 10);
            }
        };
    }

    /**
     * 显示确认课程对话框
     */
    public void showDialog() {
        if (alertDialog == null) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getView().getFragment().getActivity());
            dialogAdapter = new DialogAdapter(R.layout.item_dialog_comfirm_course, selectCoursies);
            dialogView = View.inflate(getView().getFragment().getContext(), R.layout.dialog_confirm_course, null);
            dialogBuilder.setView(dialogView);
            recyclerView = dialogView.findViewById(R.id.rcv);
            recyclerView.setAdapter(dialogAdapter);
            dialogView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            //设置好参数之后，创建对话框
            alertDialog = dialogBuilder.create();
        }
        //设置新数据并显示对话框
        dialogAdapter.setNewData(selectCoursies);
        alertDialog.show();
    }

    class DialogAdapter extends BaseQuickAdapter<CourseEntity, BaseViewHolder> {

        public DialogAdapter(int layoutResId, @Nullable List<CourseEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CourseEntity item) {
            helper.setText(R.id.tvCourseName, item.getCourseName());
            helper.setText(R.id.tvDuration, item.getDuration() + "分");
            helper.setText(R.id.tvNum, item.getNum() + "节");
        }
    }
    /**
     * 对话框列表适配器
     */

}
