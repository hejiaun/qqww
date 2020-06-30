package example.common_base.activity;

import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.example.common_base.R;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 举报activity
 */
public class ReportActivity extends BaseActivity implements SuperTextView.OnSuperTextViewClickListener {

    private SuperTextView stv2;
    private SuperTextView stv1;
    private boolean checkStv1 = false;
    private boolean checkStv2 = false;

    /**
     * 加载布局文件
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_report;
    }

    /**
     * 从布局中加载控件
     */
    @Override
    public void initView() {
        super.initView();
        stv1 = findViewById(R.id.stv1);
        stv2 = findViewById(R.id.stv2);

        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText("举报");

        stv1.setOnSuperTextViewClickListener(this);
        stv2.setOnSuperTextViewClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }

    @Override
    public void onClickListener(SuperTextView superTextView) {
        int id = superTextView.getId();
        if (id == R.id.stv1) {
            if (checkStv1) {
                stv1.setRightIcon(R.drawable.chat_icon_chazhao);
            } else {
                stv1.setRightIcon(R.drawable.chat_icon_chenggong);
            }
            checkStv1 = !checkStv1;
        } else if (id == R.id.stv2) {
            if (checkStv2) {
                stv2.setRightIcon(R.drawable.chat_icon_chazhao);
            } else {
                stv2.setRightIcon(R.drawable.chat_icon_chenggong);
            }
            checkStv2 = !checkStv2;
        }
    }
}
