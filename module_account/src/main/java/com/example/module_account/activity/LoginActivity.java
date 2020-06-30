package com.example.module_account.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_account.R;
import com.example.module_account.presenter.LoginPresenter;
import com.example.module_account.view_interface.ILoginView;
import com.jaeger.library.StatusBarUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import example.common_base.base.BaseActivity;
import example.common_base.eventbusevent.LoginTypeBusEvent;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;
import util.ShareUtil;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  登陆Activity
 */
@Route(path = ARouterUtil.Login_Activity)
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {
    private EditText etUserName;
    private EditText etPassword;
    private View llValidateCodeLogin;
    private View llPasswordLogin;
    private Button btnVisitor;
    private ImageView ivValidateCodeLogin;
    private ImageView ivWeixin;
    private Button btnLogin;
    private TextView tvForgetPwd;
    private EditText etValidateCode;
    private TextView tvGetValidateCode;
    private final int MODE_VALIDATE_CODE = 0;
    private final int MODE_PASSWORD = 1;

    /**
     * 登陆方式
     * <ul>
     * <li>0：代表账号密码登陆</li>
     * <li>1:代表验证码登陆</li>
     * </ul>
     */
    private int loginMode = MODE_PASSWORD;
    private TimerTask timerTask;
    private CheckBox cbAgreement;
    private AlertDialog dialog;


    /**
     * onCreate之前的处理了
     */
    @Override
    public void onCreateBefore() {
        super.onCreateBefore();
        overridePendingTransition(com.example.common_base.R.anim.activity_fade_in, com.example.common_base.R.anim.activity_fade_out);
    }

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        cbAgreement = findViewById(R.id.cbAgreement);
        etValidateCode = findViewById(R.id.et_validateCode);
        tvGetValidateCode = findViewById(R.id.tv_getValidateCode);
        llPasswordLogin = findViewById(R.id.llPasswordLogin);
        llValidateCodeLogin = findViewById(R.id.llValidateCodeLogin);
        etUserName = findViewById(R.id.et_userName);
        etPassword = findViewById(R.id.et_password);
        tvForgetPwd = findViewById(R.id.tv_forgetPwd);
        btnLogin = findViewById(R.id.btn_login);
        btnVisitor = findViewById(R.id.btn_visitor);
        ivValidateCodeLogin = findViewById(R.id.iv_change_login_mode);
        ivWeixin = findViewById(R.id.iv_weixin);

        tvForgetPwd.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnVisitor.setOnClickListener(this);
        ivValidateCodeLogin.setOnClickListener(this);
        tvGetValidateCode.setOnClickListener(this);
        ivWeixin.setOnClickListener(this);
        cbAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnLogin.setBackground(getResources().getDrawable(R.drawable.shape_round_login_y));
                } else {
                    btnLogin.setBackground(getResources().getDrawable(R.drawable.shape_round_login_n));
                }
            }
        });
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        EventBus.getDefault().register(this);
        StatusBarUtil.setLightMode(this);
    }

    /**
     * View层向Presenter层提供上下文context
     *
     * @return 上下文 context
     */
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerTask != null) {
            timerTask.cancel(true);
        }
        EventBus.getDefault().unregister(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_forgetPwd) {//忘记密码
            startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
        } else if (viewId == R.id.btn_login) {//使用账号密码登陆
            login();
        } else if (viewId == R.id.btn_visitor) {//游客模式
            ActivityUtil.getInstance().finishAllActivity();
            //游客模式登录
            //跳转到主界面
            // TODO: 2019/1/13 跳转
            ARouter.getInstance().build(ARouterUtil.Main_Activity).navigation();
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else if (viewId == R.id.iv_change_login_mode) {
            changeLoginMode();
        } else if (viewId == R.id.iv_weixin) {
            ShareUtil.getInstence().shareWXTextMessage(this, SendMessageToWX.Req.WXSceneFavorite);
        } else if (viewId == R.id.tv_getValidateCode) {
            showComfirmDialog();
        }

    }

    /**
     * 登陆
     */
    private void login() {
        if (cbAgreement.isChecked()) {
            getPresenter().login(etUserName.getText().toString().trim(), etPassword.getText().toString().trim(), null);
        } else {
            Toast.makeText(this, "请勾选同意协议", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 切换登陆方式
     */
    private void changeLoginMode() {
        if (loginMode == MODE_PASSWORD) {
            loginMode = MODE_VALIDATE_CODE;
            llPasswordLogin.setVisibility(View.GONE);
            llValidateCodeLogin.setVisibility(View.VISIBLE);
            tvForgetPwd.setVisibility(View.INVISIBLE);
            tvForgetPwd.setClickable(false);
        } else {
            tvForgetPwd.setClickable(true);
            tvForgetPwd.setVisibility(View.VISIBLE);
            loginMode = MODE_PASSWORD;
            llPasswordLogin.setVisibility(View.VISIBLE);
            llValidateCodeLogin.setVisibility(View.GONE);
        }
    }

    /**
     * 显示确认对话框
     */
    public void showComfirmDialog() {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("手机号");
            builder.setMessage("\t\t\t\t" + "+125656");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    getValidateCode();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
        }
        dialog.show();

    }

    /**
     * 获取验证码
     */
    private void getValidateCode() {
        timerTask = new TimerTask();
        timerTask.execute();
    }

    class TimerTask extends AsyncTask<String, Integer, String> {
        private int second;

        @Override
        protected void onPreExecute() {
            second = 60;
            tvGetValidateCode.setClickable(false);
            ivValidateCodeLogin.setClickable(false);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            while (second > 0) {
                try {
                    publishProgress(second--);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvGetValidateCode.setText(second + "秒");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvGetValidateCode.setText("获取验证码");
            second = 60;
            tvGetValidateCode.setClickable(true);
            ivValidateCodeLogin.setClickable(true);
        }
    }

    /**
     * 执行用验证码登陆的EventBus消息
     *
     * @param event
     */
    @Subscribe
    public void validateLoginBusMetho(LoginTypeBusEvent event) {
        loginMode = MODE_PASSWORD;
        changeLoginMode();
        Toast.makeText(this, "请使用验证码登陆", Toast.LENGTH_LONG).show();
    }

}
