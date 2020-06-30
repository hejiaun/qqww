package com.example.module_me.activity;

import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.module_me.R;
import com.example.module_me.presenter.EditBrithdayActivityPresenter;
import com.example.module_me.view_interface.IEditBrithdayActivityView;
import com.jaeger.library.StatusBarUtil;

import java.util.Calendar;
import java.util.Date;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人生日的Activity
 */
public class EditBrithdayActivity extends BaseActivity<EditBrithdayActivityPresenter> implements
        IEditBrithdayActivityView {
    private TimePickerView timePicker;
    int selectedYear;
    int selectedMonth;
    int selectedDay;
    Date selectDate;
    TextView tv_right;
    TextView tvTitle;
    FunctionItemView fivAge;
    FunctionItemView fivConstellation;

    @Override
    public void initView() {
        super.initView();
        findViewById(R.id.iv_back).setOnClickListener(this);
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);
        fivAge = findViewById(R.id.fiv_age);
        fivConstellation = findViewById(R.id.fiv_constellation);
        fivAge.setOnClickListener(this);
        fivConstellation.setOnClickListener(this);
        tv_right.setOnClickListener(this);

    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tv_right.setText("完成");
        tvTitle.setText("生日");
        fivAge.setTextTitle("年龄:");
        fivConstellation.setTextTitle("星座:");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_brithday;
    }

    @Override
    public EditBrithdayActivityPresenter createPresenter() {
        return new EditBrithdayActivityPresenter(this);
    }

    /**
     * 现实生日选择对话框
     */
    public void showBirthdaySelectDialog() {
        TimePickerBuilder timePickerBuilder = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                selectDate = date;
                Calendar selectCalendar = Calendar.getInstance();
                selectCalendar.setTime(date);
                Calendar currentCalendar = Calendar.getInstance();
                currentCalendar.setTime(new Date());
                selectedYear = selectCalendar.get(Calendar.YEAR);
                selectedMonth = selectCalendar.get(Calendar.MONTH);
                selectedDay = selectCalendar.get(Calendar.DATE);
                int currentYear = currentCalendar.get(Calendar.YEAR);
                if ((currentYear - selectedYear) >= 0) {
                    fivAge.setOnlyRightText(currentYear - selectedYear + "");
                } else {
                    fivAge.setOnlyRightText("0");
                }
                fivConstellation.setOnlyRightText(getPresenter().getConstellation(selectedMonth + 1, selectedDay));
            }
        });
        timePickerBuilder.setCancelText("关闭");
        timePickerBuilder.setSubmitText("确定");
        timePicker = timePickerBuilder.build();
        timePicker.show();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.fiv_age) {//点击年龄
            showBirthdaySelectDialog();

        } else if (viewId == R.id.fiv_constellation) {//点击星座
            showBirthdaySelectDialog();

        } else if (viewId == R.id.tv_right) {//点击完成
            if (selectDate != null) {
                getPresenter().commitBirthday(100000001L, selectDate);
            }
        } else if (viewId == R.id.iv_back) {//点击返回按钮
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
