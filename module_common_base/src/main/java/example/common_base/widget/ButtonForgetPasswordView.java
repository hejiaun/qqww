package example.common_base.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;

import example.common_base.util.DensityUtils;


public class ButtonForgetPasswordView extends LinearLayout {

    private Context context;
    private Button btn;
    private TextView tv;
    private LayoutParams linearLayoutParams;
    private ViewGroup.LayoutParams tvLayoutParams;
    private ViewGroup.LayoutParams btnLayoutParams;

    public ButtonForgetPasswordView(Context context) {
        this(context, null);
    }

    public ButtonForgetPasswordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonForgetPasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initLayout();
    }

    private void initLayout() {
        btn = new Button(context);
        tv = new TextView(context);
        initLinearLayoutConfig();
        initButtonConfig();
        initTextViewConfig();

        addView(tv);
        addView(btn);
    }

    private void initTextViewConfig() {
        ViewGroup.LayoutParams tvLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setTextColor(getResources().getColor(R.color.black));
        tv.setTextSize(16);
        tv.setLayoutParams(tvLayoutParams);
    }

    private void initButtonConfig() {
        btn.setBackgroundResource(R.drawable.card_round_purple);
        MarginLayoutParams btnLayoutParams = (MarginLayoutParams) new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnLayoutParams.setMargins(0, DensityUtils.dp2px(context, 25), 0, 0);
        btn.setLayoutParams(btnLayoutParams);
        btn.setTextColor(getResources().getColor(R.color.white));

    }

    private void initLinearLayoutConfig() {
        setPadding(16, 16, 16, 16);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.shape_card_round_white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(2);
        }
    }

    public void setText(String text) {
        tv.setText(text);
    }

    public void setBtnText(String text) {
        btn.setText(text);
    }

    public void setBtnOnClickListener(final DoClickInterface doClickInterface) {
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                doClickInterface.doClick(tv.getText().toString().trim());
            }
        });
    }

    /**
     * 点击时间接口
     */
    public interface DoClickInterface {
        void doClick(String text);
    }
}
