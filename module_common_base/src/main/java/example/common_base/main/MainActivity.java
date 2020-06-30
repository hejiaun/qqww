package example.common_base.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common_base.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ARouterUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  主Activity
 */
@Route(path = ARouterUtil.Main_Activity)
public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {
    CommonTabLayout ctl;

    @Override
    public void initView() {
        super.initView();
        ctl = findViewById(R.id.ctl);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存所有fragment
        getPresenter().saveAllFragment(outState);
        outState.putInt("tagIndex", ctl.getCurrentTab());
    }


    /**
     * 创建Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    /**
     * 回复Fragment状态
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        int currentTab = 1;
        if (savedInstanceState != null) {
            getPresenter().restoreFragment(savedInstanceState);
            currentTab = savedInstanceState.getInt("tagIndex");
        }
        getPresenter().showFragment(currentTab, getSupportFragmentManager().beginTransaction());
        ctl.setCurrentTab(currentTab);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setDarkMode(this);
        ctl.setTabData(getPresenter().getTabEntitys());
        ctl.setCurrentTab(1);
        ctl.showDot(1);
        ctl.showMsg(0, 12);
        ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                getPresenter().showFragment(position, getSupportFragmentManager().beginTransaction());
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 显示红点在底部导航栏图标中
     *
     * @param position
     */
    public void showBottomIconDot(int position) {
        ctl.showDot(position);
    }

    /**
     * 显示数字在底部导航栏图标中
     *
     * @param position
     * @param num
     */
    public void showBottomIconNumber(int position, int num) {
        ctl.showMsg(position, num);
    }

    /**
     * Activity跳转回调
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
            } else {
                Toast.makeText(this, "扫描成功", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * View层向Presenter层提供MainActivity
     *
     * @return MainActivity
     */
    @Override
    public MainActivity getActivity() {
        return this;
    }
}
