package example.common_base.base;

import android.graphics.Color;
import android.os.Bundle;

import com.example.common_base.R;
import com.jaeger.library.StatusBarUtil;

import example.common_base.util.WindowUtil;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  人工申诉Activity基础父类(Base父类)
 */
public abstract class FadeTransitionBaseActivity<T extends BasePresenter> extends BaseActivity<T> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

}
