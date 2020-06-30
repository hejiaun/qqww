package com.example.module_pk.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.adapter.PresenterDialogAdapter;
import com.example.module_pk.entity.PresentEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import example.common_base.entity.TabEntity;
import example.common_base.util.RecyclerViewItemDecorationUtil;
import example.common_base.util.WindowUtil;


/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 礼物对话框
 */
public class PresentDialog extends Dialog implements View.OnClickListener {

    private View dialogView;
    private TextView tvSend;
    private TextView tvCharge;
    private RecyclerView rcvPresent;
    private CommonTabLayout ctl;
    private TextView tvCustomNum;
    private PresenterDialogAdapter adapter;
    private PresentDialogClickListener clickListener = null;
    private AlertDialog customPresentDialog;

    public PresentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        dialogView = View.inflate(context, R.layout.dialog_present, null);
        setContentView(dialogView);
        initView();
        initConfig();
    }

    public PresentDialog(@NonNull Context context) {
        this(context, R.style.FullWidthDialogStyle);
    }


    private void initConfig() {
        //----------------------配置窗口位置、大小-----------------------//
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        //获取窗口属性对象
        //给窗口设置属性对象
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        //----------------------设置数据-----------------------//
        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new TabEntity("1"));
        customTabEntities.add(new TabEntity("10"));
        customTabEntities.add(new TabEntity("66"));
        customTabEntities.add(new TabEntity("99"));
        ctl.setTabData(customTabEntities);

        //----------------------配置关联-----------------------//
        adapter = new PresenterDialogAdapter(R.layout.item_present, new ArrayList<PresentEntity>());
        for (int i = 0; i < 20; i++) {
            adapter.addData(new PresentEntity());
        }
        rcvPresent.setAdapter(adapter);
        ((DefaultItemAnimator) rcvPresent.getItemAnimator()).setSupportsChangeAnimations(false);
        rcvPresent.getLayoutParams().height = WindowUtil.getInstence().getWindowHeight(getContext()) / 4;
        rcvPresent.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(0, 0, 10, 0));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //----------------------给item设置选中框-----------------------//
                ArrayList<PresentEntity> data = (ArrayList<PresentEntity>) adapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isSelect()) {
                        data.get(i).setSelect(false);
                    }
                    adapter.notifyItemChanged(i);
                }
                PresentEntity entity = (PresentEntity) adapter.getData().get(position);
                entity.setSelect(true);
                adapter.notifyItemChanged(position);
            }
        });
    }

    private void initView() {
        //------------------加载控件---------------
        tvSend = dialogView.findViewById(R.id.tvSend);
        tvCharge = dialogView.findViewById(R.id.tvCharge);
        rcvPresent = dialogView.findViewById(R.id.rcvPresent);
        ctl = dialogView.findViewById(R.id.ctl);
        tvCustomNum = dialogView.findViewById(R.id.tvCustomNum);

        tvCharge.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        tvCustomNum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvCharge) {
            if (clickListener != null) {
                clickListener.clickCharge();
            }
        } else if (id == R.id.tvSend) {
            if (clickListener != null) {
                clickListener.clickSend();
            }
        } else if (id == R.id.tvCustomNum) {
            if (clickListener != null) {
                clickListener.clickCustomNun();

            }
            showCustomNumDialog();
        }
    }

    /**
     * 显示输入自定义数量的对话框
     */
    public void showCustomNumDialog() {
        if (customPresentDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog);
            View view = View.inflate(getContext(), R.layout.dialog_custom_present_num, null);
            builder.setTitle("请输入数量");
            builder.setPositiveButton("发送", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("取消", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    customPresentDialog.dismiss();
                }
            });
            builder.setView(view);
            customPresentDialog = builder.create();
        }
        customPresentDialog.show();

    }

    /**
     * 点击事件监听
     */
    public interface PresentDialogClickListener {
        /**
         * 点击充值
         */
        void clickCharge();

        /**
         * 点击发送
         */
        void clickSend();

        /**
         * 点击自定义
         */
        void clickCustomNun();
    }
}
