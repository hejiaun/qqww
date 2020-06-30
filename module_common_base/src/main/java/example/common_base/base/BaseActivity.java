package example.common_base.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.billy.android.loading.Gloading;

import example.common_base.util.ActivityUtil;
import example.common_base.util.WindowUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  Activity基础父类(Base父类)
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView, View.OnClickListener {

    T presenter;
    public Gloading.Holder loadingHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onCreateBefore();
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        loadingHolder = Gloading.getDefault().wrap(this);
        this.presenter = createPresenter();
        setStatusBar();
        ActivityUtil.getInstance().addActivity(this);
        startInit();
    }

    /**
     * 在onCreate方法前的操作
     */
    public void onCreateBefore() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取presenter
     *
     * @return
     */
    public T getPresenter() {
        return presenter;
    }

    /**
     * 加载布局
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * 创建presenter
     *
     * @return
     */
    public abstract T createPresenter();

    /**
     * 开始配置、加载数据
     */
    public void startInit() {
        initView();
        initConfig();
        initData();
    }

    public void initView() {

    }

    /**
     * 加载数据
     */
    public void initData() {
    }

    /**
     * 基础配置
     */
    public void initConfig() {
    }

    /**
     * 设置状态栏
     */
    public void setStatusBar() {
        WindowUtil.getInstence().setTransparentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtil.getInstance().finishActivity(this);
    }
}
