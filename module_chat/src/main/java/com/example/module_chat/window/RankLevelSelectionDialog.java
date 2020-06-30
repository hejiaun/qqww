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
 * @Description: 段位选择
 */
public class RankLevelSelectionDialog extends Dialog implements View.OnClickListener {

    private final View view;
    private WheelPicker wpRankLevel;
    private List<String> data;

    public RankLevelSelectionDialog(@NonNull Context context) {
        super(context);
        view = View.inflate(context, R.layout.dialog_rank_level_selection, null);
        setContentView(view);
        initView();
        initData();
    }

    private void initView() {
        wpRankLevel = view.findViewById(R.id.wpConstellation);
        view.findViewById(R.id.tvBack).setOnClickListener(this);
        view.findViewById(R.id.tvSure).setOnClickListener(this);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add("不限");
        data.add("青铜");
        data.add("白银");
        data.add("黄金");
        data.add("白金");
        wpRankLevel.setData(data);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvSure) {
            String s = data.get(wpRankLevel.getCurrentItemPosition());
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
