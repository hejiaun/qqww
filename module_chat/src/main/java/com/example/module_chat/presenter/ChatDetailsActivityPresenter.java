package com.example.module_chat.presenter;

import android.app.AlertDialog;
import android.graphics.Rect;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.adapter.ChatDetailsRecyclerViewAdapter;
import com.example.module_chat.view_interface.IChatDetailsActivityView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.UserEntity;
import example.common_base.util.DensityUtils;
import example.common_base.util.WindowUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class ChatDetailsActivityPresenter extends BasePresenter<IChatDetailsActivityView> {
    private ChatDetailsRecyclerViewAdapter chatDetailsRecyclerViewAdapter;

    /**
     * 构造方法，初始化View层
     *
     * @param iChatDetailsActivityView View层接口
     */
    public ChatDetailsActivityPresenter(IChatDetailsActivityView iChatDetailsActivityView) {
        super(iChatDetailsActivityView);
    }

    public ChatDetailsRecyclerViewAdapter getAdapter() {
        if (chatDetailsRecyclerViewAdapter == null) {
            chatDetailsRecyclerViewAdapter = new ChatDetailsRecyclerViewAdapter(R.layout.item_chat_details_headview, new ArrayList<UserEntity>());
            for (int i = 0; i < 1; i++) {
                getAdapter().getData().add(new UserEntity());
            }
        }
        return chatDetailsRecyclerViewAdapter;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = DensityUtils.dp2px(view.getContext(), 12);
                outRect.right = DensityUtils.dp2px(view.getContext(), 12);
                outRect.top = DensityUtils.dp2px(view.getContext(), 6);
                outRect.bottom = DensityUtils.dp2px(view.getContext(), 6);
//                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//                layoutParams.height = WindowUtil.getInstence().getWindowWidth(view.getContext()) / 5;
                view.findViewById(R.id.ivHead).getLayoutParams().height
                        = WindowUtil.getInstence().getWindowWidth(view.getContext()) / 5 - outRect.left - outRect.right;
            }
        };
    }

        /**
         * 显示底部清除
         */
        public void showComfirmCleanRecordDailog() {
            final BottomSheetDialog dialog;
            dialog = new BottomSheetDialog(getView().getActivity());
            View view = View.inflate(getView().getActivity(), R.layout.dialog_bottom_confirm, null);
            TextView tvSure = view.findViewById(R.id.tvSure);
            tvSure.setText("确定删除");
            tvSure.setTextColor(getView().getActivity().getResources().getColor(R.color.red_select));
            TextView tvCancel = view.findViewById(R.id.tvCancel);
            tvCancel.setText("取消");
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int viewId = v.getId();
                    if (viewId == R.id.tvSure) {

                    }
                    dialog.dismiss();
                }
            };
            tvCancel.setOnClickListener(onClickListener);
            tvSure.setOnClickListener(onClickListener);
            dialog.setContentView(view);
            dialog.show();

        }

    /**
     * 显示更新群名称对话框
     */
    public void showUpdateGroupNameDialog() {
        final View view = View.inflate(getView().getActivity(), R.layout.dialog_input, null);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("请输入群名称");
        AlertDialog.Builder builder = new AlertDialog.Builder(getView().getActivity());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                if (viewId == R.id.tvCancel) {
                    dialog.dismiss();
                } else if (viewId == R.id.tvSure) {
                    dialog.dismiss();
                }
            }
        };
        view.findViewById(R.id.tvCancel).setOnClickListener(onClickListener);
        view.findViewById(R.id.tvSure).setOnClickListener(onClickListener);
        dialog.show();
    }

    /**
     * 显示更新我的群昵称
     */
    public void showUpdateMyGroupNickNameDialog() {

    }
}
