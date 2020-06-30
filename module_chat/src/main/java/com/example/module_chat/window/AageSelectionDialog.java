package com.example.module_chat.window;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.module_chat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 年龄选择
 */
public class AageSelectionDialog extends Dialog implements View.OnClickListener {

    private final View view;
    private WheelPicker wpAge;
    private List<String> data;

    public AageSelectionDialog(@NonNull Context context) {
        super(context);
        view = View.inflate(context, R.layout.dialog_age_selection, null);
        setContentView(view);
        initView();
        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {
        data = new ArrayList<>();
        data.add("不限");
        data.add("00后");
        data.add("90后");
        data.add("80后");
        data.add("70后");
        data.add("60后");
        wpAge.setData(data);
    }

    /**
     * 加载控件
     */
    private void initView() {
        wpAge = view.findViewById(R.id.wpConstellation);
        view.findViewById(R.id.tvBack).setOnClickListener(this);
        view.findViewById(R.id.tvSure).setOnClickListener(this);
    }


    /**
     * 点击事件的监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvSure) {
            String s = data.get(wpAge.getCurrentItemPosition());
            clickListener.clickSure(s);
        }
        dismiss();
    }

    private ClickSuerListener clickListener;

    public void setClickListener(ClickSuerListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickSuerListener {
        void clickSure(String result);
    }
}
