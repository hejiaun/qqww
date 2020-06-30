package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 标题加标签
 */
public class Tab extends LinearLayout {
    Context context;
    private TextView tvTitle;

    public Tab(Context context) {
        this(context, null);
    }

    public Tab(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Tab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        tvTitle = new TextView(context);
        tvTitle.setText("标题");
    }

    /**
     * 添加标签
     *
     * @param textContent 标签内容
     */
    public void addTab(String textContent, int background) {
        if (context == null) return;
        TextView textView = new TextView(context);
        textView.setText(textContent);
        textView.setPadding(6, 6, 6, 6);
        textView.setBackground(getResources().getDrawable(background));
        addView(textView);
    }

    /**
     * 设置标签标题文字大小
     *
     * @param size 文字大小
     */
    public void setTitleTextSize(int size) {
        tvTitle.setTextSize(size);
    }

    /**
     * 设置标签标题文字内容
     *
     * @param text 文字内容
     */
    public void setTitleText(String text) {
        tvTitle.setText(text);
    }

}
