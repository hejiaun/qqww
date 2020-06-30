package com.example.module_find.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_find.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.vfighter.usercenter.bean.UserInfo;
import example.common_base.eventbusevent.MusicCircleCommentEvent;
import example.common_base.util.CustomBaseViewHolder;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:音乐圈Item控件
 */
public class MusicCircleItemView extends LinearLayout implements View.OnClickListener {
    private int mType = -1;
    /**
     * 头像Url
     */
    private String mHeadUrl = null;
    /**
     * 发布人名称
     */
    private String mName = null;
    /**
     * 描述
     */
    private String mDes = null;

    /**
     * 文本类型
     */
    int ONLY_TEXT_TYPE = 0xFFF101;

    /**
     * 视频类型
     */
    int VIDEO_TYPE = 0xFFF102;

    /**
     * 音乐类型
     */
    int MUSIC_TYPE = 0xFFF103;

    /**
     * 图片类型
     */
    int IMAGE_TYPE = 0xFFF104;
    private View view;
    private ImageView ivHead;
    private TextView tvForward;
    private TextView tvDes;
    private ImageView ivDelete;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvLike;
    private TextView tvDislike;
    private TextView tvCare;
    private RecyclerView rcv;
    private TextView tvComment;
    /**
     * 是否点赞了
     */
    private boolean isLike = false;
    private boolean isPoupuShowing = false;
    private boolean isCommentDialogShowing = false;
    private PopupWindow popupWindow;
    private View popupView;
    private Dialog commentDialog;
    private TextView tvSend;
    private EditText etInput;
    private ImageView ivEmoji;


    public MusicCircleItemView(Context context) {
        this(context, null);
    }

    public MusicCircleItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicCircleItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (view == null) {
            view = View.inflate(context, R.layout.view_musiccircle, this);
            initView();
        }
    }

    private void initView() {
        rcv = view.findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        ivHead = view.findViewById(R.id.ivHead);
        tvComment = view.findViewById(R.id.tvComment);
        tvForward = view.findViewById(R.id.tvForward);
        tvLike = view.findViewById(R.id.tvLike);
        tvDes = view.findViewById(R.id.tvDes);
        ivDelete = view.findViewById(R.id.ivDelete);
        tvName = view.findViewById(R.id.tvName);
        tvTime = view.findViewById(R.id.tvTime);
        tvLike.setOnClickListener(this);
        tvComment.setOnClickListener(this);
    }

    public MusicCircleItemView setmHeadUrl(String headUrl) {
        mHeadUrl = headUrl;
        Glide.with(this)
                .load(headUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivHead);
        return this;
    }

    public MusicCircleItemView getDes(String des) {
        mDes = des;
        return this;
    }

    public MusicCircleItemView setName(String name) {
        mName = name;
        return this;
    }

    public MusicCircleItemView setTime(Date date) {
        return this;
    }

    /**
     * 评论
     *
     * @param comment
     */
    public void comment(String comment) {

    }

    /**
     * 点赞
     */
    public void like() {

    }

    /**
     * 加载评论
     */
    public void initComment(ArrayList data) {
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.item_comment_musiccircle, new ArrayList<UserInfo>());
        adapter.addData(new UserInfo());
        adapter.addData(new UserInfo());
        adapter.addData(new UserInfo());
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = DensityUtils.dp2px(getContext(), 3);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvLike) {
            showPopupWindow();
        } else if (id == R.id.tvLikePop) {
            Toast.makeText(getContext(), "00", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tvDislikePop) {
            Toast.makeText(getContext(), "00", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tvCarePop) {
            Toast.makeText(getContext(), "00", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tvComment) {
            EventBus.getDefault().post(new MusicCircleCommentEvent(2));
        } else if (id == R.id.ivEmoji) {

        } else if (id == R.id.tvSend) {
            if (etInput.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
            } else {
                commentDialog.dismiss();
            }
        }
    }

    /**
     * 点赞窗口
     */
    public void showPopupWindow() {
        if (popupWindow == null) {
            popupView = View.inflate(getContext(),
                    R.layout.popupwindow_emtion, null);
            popupWindow = new PopupWindow(popupView,
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.getContentView().findViewById(R.id.tvLikePop).setOnClickListener(this);
            popupWindow.getContentView().findViewById(R.id.tvCarePop).setOnClickListener(this);
            popupWindow.getContentView().findViewById(R.id.tvDislikePop).setOnClickListener(this);
        }
        if (!isPoupuShowing) {
            isPoupuShowing = true;
            popupWindow.showAsDropDown(tvLike);
        } else {
            isPoupuShowing = false;
            popupWindow.dismiss();
        }
    }

    class MyRecyclerViewAdapter extends BaseQuickAdapter<UserInfo, CustomBaseViewHolder> {

        public MyRecyclerViewAdapter(int layoutResId, @Nullable List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(CustomBaseViewHolder helper, UserInfo item) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendComment() {

    }


}