package com.example.module_account.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.example.module_account.R;
import com.example.module_account.presenter.ArtificialFormActivityPresenter;
import com.example.module_account.view_interface.IArtificialFormActivityView;
import com.jaeger.library.StatusBarUtil;

import java.util.Date;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.FormatUtil;

/**
 * Created by Administrator on 2018/5/21.
 */
public class ArtificialFormActivity extends FadeTransitionBaseActivity<ArtificialFormActivityPresenter> implements
        View.OnClickListener,
        IArtificialFormActivityView {
    TextView tvTime1;
    TextView tvTime2;
    TextView tvTitle;
    private ImageView ivBack;
    private RelativeLayout rlPwd;
    private Button btnNext;
    private RelativeLayout rlPhone;
    private RelativeLayout rlRegistDate;
    private RelativeLayout rlLoginDate;
    private AlertDialog dialog;


    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTime1 = findViewById(R.id.tv_time1);
        tvTime2 = findViewById(R.id.tv_time2);
        tvTitle = findViewById(R.id.tv_title);
        rlRegistDate = findViewById(R.id.rl_registDate);
        rlLoginDate = findViewById(R.id.rl_loginDate);
        rlPhone = findViewById(R.id.rl_phone);
        rlPwd = findViewById(R.id.rl_pwd);
        btnNext = findViewById(R.id.btn_next);
        ivBack = findViewById(R.id.iv_back);

        rlLoginDate.setOnClickListener(this);
        rlRegistDate.setOnClickListener(this);
        rlPhone.setOnClickListener(this);
        rlPwd.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        ivBack.setOnClickListener(this);


    }

    /**
     * 加载配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        //配置工具、第三方插件
        //配置控件
        tvTitle.setText("申诉表格");
    }


    /**
     * 跳转下一个Activity界面
     */
    public void startActivity() {
        startActivity(new Intent(this, ArtificialResultActivity.class));
        ActivityUtil.getInstance().finishActivity(ArtificialQuestion1Activity.class);
        ActivityUtil.getInstance().finishActivity(ArtificialEmailActivity.class);
        ActivityUtil.getInstance().finishActivity(ForgetPwdActivity.class);
        ActivityUtil.getInstance().finishActivity(this);
    }

    /**
     * 加载布局
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_artificial_form;
    }

    /**
     * 创建presenter
     * @return
     */
    @Override
    public ArtificialFormActivityPresenter createPresenter() {
        return new ArtificialFormActivityPresenter(this);
    }


    /**
     * 显示时间选择器
     *
     * @param tv
     */
    public void showTimePicker(final TextView tv) {
        TimePickerBuilder timePickerBuilder = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv.setText(FormatUtil.getInstence().date2YMD(date));
            }
        });
        timePickerBuilder.setCancelText("关闭");
        timePickerBuilder.setSubmitText("确定");
        timePickerBuilder.build().show();
    }

    /**
     * 点击事件的监听
     * @param view
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) { //返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.rl_registDate) {  //显示注册日期选择器
            showTimePicker(tvTime1);
        } else if (viewId == R.id.rl_loginDate) {//显示登录日期选择器
            showTimePicker(tvTime2);
        } else if (viewId == R.id.rl_phone) {//手机号

        } else if (viewId == R.id.btn_next) {
            showCommitDialog();
        }
    }

    /**
     * 显示提交对话框
     */
    public void showCommitDialog() {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("消息");
            builder.setMessage("我们已经收到您提交的信息，请您耐心等待我们即将给您的反馈！");
            builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    ActivityUtil.getInstance().finishAllActivityExcept(LoginActivity.class);
                }
            });
            dialog = builder.create();
        }
        dialog.show();
    }
}
