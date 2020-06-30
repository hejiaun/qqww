package com.example.module_pk.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.adapter.BarrageDialogRecyclerViewAdapter;
import com.example.module_pk.entity.WordEntity;

import java.util.ArrayList;
import java.util.Collections;

import example.common_base.app.MyApplication;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 弹幕对话框
 */
public class BarrageDialog extends Dialog implements
        BaseQuickAdapter.OnItemClickListener,
        View.OnClickListener {
    private View dialogView;
    private RecyclerView rcvWord;
    private BarrageDialogRecyclerViewAdapter adapter;
    private EditText etInput;
    private TextView tvSend;
    private BarrageDialogClickListener dialogClickListener;

    public BarrageDialog(@NonNull Context context) {
        this(context, R.style.FullWidthDialogStyle);
    }

    public BarrageDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        //----------------------设置对话框布局-----------------------//
        dialogView = View.inflate(getContext(), R.layout.dialog_bottom_barrage, null);
        setContentView(dialogView);
        initView();
        initConfig();
    }

    /**
     * 配置关联、加载数据
     */
    private void initConfig() {
        //----------------------设置窗口位置-----------------------//
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        //获取窗口属性对象
        //给窗口设置属性对象
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        //----------------------配置关联-----------------------//
        adapter = new BarrageDialogRecyclerViewAdapter(R.layout.item_barrage, new ArrayList<WordEntity>());
        rcvWord.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
//        adapter.setUpFetchEnable(true);
//        Collections.reverse(adapter.getData());

        //----------------------加载数据-----------------------//
        for (int i = 0; i < 10; i++) {
            adapter.addData(new WordEntity("666666666666"));
            adapter.addData(new WordEntity("老铁牛逼"));
            adapter.addData(new WordEntity("我们不一样"));
            adapter.addData(new WordEntity("我要为这个选手疯狂打call"));
            adapter.addData(new WordEntity("耳朵都听怀孕了........."));
        }
        //----------------------设置监听器-----------------------//
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //对话框关闭时，清空输入框
                etInput.setText("");
            }
        });
    }

    /**
     * 加载控件
     */
    private void initView() {
        etInput = dialogView.findViewById(R.id.etMessage);
        rcvWord = (RecyclerView) dialogView.findViewById(R.id.rcvWord);
        tvSend = dialogView.findViewById(R.id.tvSend);
        tvSend.setOnClickListener(this);
    }

    /**
     * 列表点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        WordEntity wordEntity = (WordEntity) adapter.getData().get(position);
        etInput.setText(etInput.getText().toString().trim() + wordEntity.getWord());
        etInput.setSelection(etInput.getText().length());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvSend) {
            clickSend();
        }
    }

    /**
     * 点击发送
     */
    private void clickSend() {
        String content = etInput.getText().toString().trim();
        if (!content.isEmpty()) {
            if (dialogClickListener != null) {
                dialogClickListener.clickSend(content);
            }
            dismiss();
        } else {
            Toast.makeText(MyApplication.getApplication(), "输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置点击事件
     *
     * @param barrageDialogClickListener
     */
    public void setBarrageDialogClickListener(BarrageDialogClickListener barrageDialogClickListener) {
        this.dialogClickListener = barrageDialogClickListener;
    }

    /**
     * 点击监听接口
     */
    public interface BarrageDialogClickListener {
        void clickSend(String word);
    }

}
