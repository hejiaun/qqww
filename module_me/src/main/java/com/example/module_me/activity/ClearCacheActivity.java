package com.example.module_me.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

import com.allen.library.SuperTextView;
import com.daimajia.swipe.SwipeLayout;
import com.example.module_me.R;
import com.example.module_me.presenter.ClearCacheActivityPresenter;
import com.example.module_me.view_interface.IClearCacheActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 清除缓存
 */
public class ClearCacheActivity extends BaseActivity<ClearCacheActivityPresenter> implements IClearCacheActivityView {

    private SuperTextView stvTitleBar;
    private SwipeLayout slAccompaniment;
    private SwipeLayout slBookSongCache;
    private SwipeLayout slImageCache;
    private SwipeLayout slLoalRecordCache;
    private SwipeLayout slPlayCache;
    private AlertDialog dialog;

    @Override
    public int initLayout() {
        return R.layout.activity_clear_cache;
    }

    @Override
    public void initConfig() {
        super.initConfig();
        stvTitleBar.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                ActivityUtil.getInstance().finishActivity(ClearCacheActivity.class);
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        MySwipeLayoutListener mySwipeLayoutListener = new MySwipeLayoutListener();
        stvTitleBar = findViewById(R.id.stvTitleBar);
        slAccompaniment = findViewById(R.id.slAccompaniment);
        slBookSongCache = findViewById(R.id.slBookSongCache);
        slImageCache = findViewById(R.id.slImageCache);
        slLoalRecordCache = findViewById(R.id.slLoalRecordCache);
        slPlayCache = findViewById(R.id.slPlayCache);

        slPlayCache.addSwipeListener(mySwipeLayoutListener);
        slLoalRecordCache.addSwipeListener(mySwipeLayoutListener);
        slImageCache.addSwipeListener(mySwipeLayoutListener);
        slBookSongCache.addSwipeListener(mySwipeLayoutListener);
        slAccompaniment.addSwipeListener(mySwipeLayoutListener);

        findViewById(R.id.tvAccompaniment).setOnClickListener(this);
        findViewById(R.id.tvPlayCache).setOnClickListener(this);
        findViewById(R.id.tvBookSongCache).setOnClickListener(this);
        findViewById(R.id.tvImageCache).setOnClickListener(this);
        findViewById(R.id.tvLocalRecordCache).setOnClickListener(this);
        findViewById(R.id.vBg).setOnClickListener(this);

        findViewById(R.id.stvAccompaniment).setOnClickListener(this);
        findViewById(R.id.stvPlayCache).setOnClickListener(this);
        findViewById(R.id.stvBookSong).setOnClickListener(this);
        findViewById(R.id.stvImageCache).setOnClickListener(this);
        findViewById(R.id.stvLoalRecordCache).setOnClickListener(this);


    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public ClearCacheActivityPresenter createPresenter() {
        return new ClearCacheActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.tvLocalRecordCache) {
            showComfirmDialog();
        } else if (id == R.id.tvImageCache) {
            showComfirmDialog();
        } else if (id == R.id.tvBookSongCache) {
            showComfirmDialog();
        } else if (id == R.id.tvPlayCache) {
            showComfirmDialog();
        } else if (id == R.id.tvAccompaniment) {
            showComfirmDialog();
        } else if (id == R.id.vBg) {
            closeAllItem();
        } else if (id == R.id.stvBookSong) {
            slBookSongCache.open();
        } else if (id == R.id.stvAccompaniment) {
            slAccompaniment.open();
        } else if (id == R.id.stvPlayCache) {
            slPlayCache.open();
        } else if (id == R.id.stvImageCache) {
            slImageCache.open();
        } else if (id == R.id.stvLoalRecordCache) {
            slLoalRecordCache.open();
        } else if (id == R.id.stvClearAll) {
            showComfirmDialog();
        }
    }

    /**
     * 显示询问是否确定删除黄村的对话框
     */
    private void showComfirmDialog() {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("确定要清理吗?");
            MyDialogBottomClickListener myDialogBottomClickListener = new MyDialogBottomClickListener();
            builder.setPositiveButton("确定", myDialogBottomClickListener);
            builder.setNegativeButton("取消", myDialogBottomClickListener);
            dialog = builder.create();
        }
        dialog.show();
    }

    /**
     * 合上所有所有的滑动条但除了指定的滑动条
     *
     * @param layout
     */
    private void closeAllItemExcept(SwipeLayout layout) {
        if (!layout.equals(slAccompaniment)) {
            slAccompaniment.close();
        }
        if (!layout.equals(slBookSongCache)) {
            slBookSongCache.close();
        }
        if (!layout.equals(slImageCache)) {
            slImageCache.close();
        }
        if (!layout.equals(slLoalRecordCache)) {
            slLoalRecordCache.close();
        }
        if (!layout.equals(slPlayCache)) {
            slPlayCache.close();
        }
    }

    /**
     * 合上所有所有的滑动条
     */
    private void closeAllItem() {
        slAccompaniment.close();
        slBookSongCache.close();
        slImageCache.close();
        slLoalRecordCache.close();
        slPlayCache.close();
    }

    /**
     * 滑动监听器
     */
    class MySwipeLayoutListener implements SwipeLayout.SwipeListener {

        @Override
        public void onStartOpen(SwipeLayout layout) {

        }

        @Override
        public void onOpen(SwipeLayout layout) {
            closeAllItemExcept(layout);
        }

        @Override
        public void onStartClose(SwipeLayout layout) {

        }

        @Override
        public void onClose(SwipeLayout layout) {

        }

        @Override
        public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

        }

        @Override
        public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

        }
    }

    /**
     * 对话框
     */
    class MyDialogBottomClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {

            } else if (which == DialogInterface.BUTTON_NEGATIVE) {

            }
            dialog.dismiss();
        }
    }

}
