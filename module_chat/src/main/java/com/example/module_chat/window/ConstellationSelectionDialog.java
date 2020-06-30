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
 * @Description:
 */
public class ConstellationSelectionDialog extends Dialog implements View.OnClickListener {
    private WheelPicker wpConstellation;

    private final View view;
    private List<String> data;

    public ConstellationSelectionDialog(@NonNull Context context) {
        super(context);
        view = View.inflate(context, R.layout.dialog_constellation_selection, null);
        setContentView(view);
        initView();
        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add("不限");
        data.add("白羊座");
        data.add("金牛座");
        data.add("双子座");
        data.add("巨蟹座");
        data.add("狮子座");
        data.add("处女座");
        data.add("天秤座");
        data.add("天蝎座");
        data.add("射手座");
        data.add("摩羯座");
        data.add("水瓶座");
        data.add("双鱼座");
        wpConstellation.setData(data);
    }

    private void initView() {
        wpConstellation = view.findViewById(R.id.wpConstellation);
        view.findViewById(R.id.tvBack).setOnClickListener(this);
        view.findViewById(R.id.tvSure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvSure) {
            String s = data.get(wpConstellation.getCurrentItemPosition());
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
