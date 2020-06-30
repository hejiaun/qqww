package com.example.module_me.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.R;
import com.example.module_me.adapter.WordOfMouthAdapter;
import com.example.module_me.view_interface.IWordOfMouthFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.WordOfMouthEvaluateEntity;
import example.common_base.entity.WordOfMouthSectionEntity;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:口碑服务
 */
public class WordOfMouthFragmentPresenter extends BasePresenter<IWordOfMouthFragmentView> {
    ArrayList<WordOfMouthSectionEntity> data = null;
    WordOfMouthAdapter wordOfMouthAdapter = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iWordOfMouthFragmentView View层接口
     */
    public WordOfMouthFragmentPresenter(IWordOfMouthFragmentView iWordOfMouthFragmentView) {
        super(iWordOfMouthFragmentView);
    }


    public ArrayList getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(new WordOfMouthSectionEntity("待评价的课程(2)", false));
        for (int i = 0; i < 5; i++) {
            data.add(new WordOfMouthSectionEntity(new WordOfMouthEvaluateEntity(false)));
        }
        data.add(new WordOfMouthSectionEntity("已评价的课程(2)", true));
        for (int i = 0; i < 11; i++) {
            data.add(new WordOfMouthSectionEntity(new WordOfMouthEvaluateEntity(true)));
        }
        // TODO: 2018/10/23 获取后台口碑资料
        return data;
    }

    public WordOfMouthAdapter getAdapter() {
        if (wordOfMouthAdapter == null) {
            wordOfMouthAdapter = new WordOfMouthAdapter(R.layout.item_word_of_mouth_not_evaluate, R.layout.item_word_of_mouth_section_head, getData());
            wordOfMouthAdapter.addHeaderView(View.inflate(getView().getFragment().getActivity(), R.layout.headview_word_of_mouth, null));
            wordOfMouthAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View v, int position) {
                    int viewId = v.getId();
                    if (viewId == R.id.btnEvaluate) {//点击评论
                        showBottomCommentDialog(getView().getFragment().getActivity());

                    }

                }
            });
        }
        return wordOfMouthAdapter;
    }

    /**
     * 获取列表Item间隔修饰器
     *
     * @return 列表Item间隔修饰器
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) == 0) return;
                if (parent.getChildAdapterPosition(view) == 1) {
                    outRect.top = DensityUtils.dp2px(view.getContext(), 4);
                    return;
                }
                WordOfMouthAdapter adapter = (WordOfMouthAdapter) parent.getAdapter();
                WordOfMouthSectionEntity wordOfMouthSectionEntity = adapter.getData().get(parent.getChildAdapterPosition(view) - 1);
                if (wordOfMouthSectionEntity.isHeader) {
                    outRect.top = DensityUtils.dp2px(view.getContext(), 12);
                } else {
                    outRect.bottom = DensityUtils.dp2px(view.getContext(), 6);
                }
            }
        };
    }

    /**
     * 显示底部评论对话框
     *
     * @param activity
     * @return
     */
    public Dialog showBottomCommentDialog(Activity activity) {
        View view = View.inflate(activity, R.layout.dialog_comment, null);
        TextView tvSure = view.findViewById(R.id.tvSure);
        TextView tvCancel = view.findViewById(R.id.tvCancel);

        final Dialog dialog = new Dialog(activity, R.style.FullWidthDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                if (viewId==R.id.tvCancel){
                    dialog.dismiss();

                } else if (viewId == R.id.tvSure) {

                }
            }
        };
        tvSure.setOnClickListener(onClickListener);
        tvCancel.setOnClickListener(onClickListener);
        //获取对话框窗口
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //获取窗口属性对象
        //给窗口设置属性对象
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        window.setAttributes(lp);
        dialog.show();
        return dialog;
    }

}
