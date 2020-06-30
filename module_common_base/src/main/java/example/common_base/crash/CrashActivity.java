package example.common_base.crash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.common_base.R;
import com.jaeger.library.StatusBarUtil;

import example.common_base.util.WindowUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class CrashActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView messageView;
    private String errorMessage;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        WindowUtil.getInstence().setTransparentStatusBar(this);
        StatusBarUtil.setLightMode(this);
        initView();
        initErrorMessage();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.tvError).setOnClickListener(this);
        lottieAnimationView = findViewById(R.id.lottiteView);
//
//        //----------------------开启动画-----------------------//
        lottieAnimationView.setAnimation("lottie_crash.json");
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.playAnimation();
    }

    private void initErrorMessage() {
        messageView = findViewById(R.id.tvErrorMessage);
        Intent intent = getIntent();
        errorMessage = intent.getStringExtra("errorMessage");
        messageView.setText(errorMessage);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn) {
            //点击关闭
            System.exit(0);
        }
    }
}
